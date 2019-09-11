package com.prj.quiz.persistence.repository;


import com.prj.quiz.model.Level;
import com.prj.quiz.model.User;
import com.prj.quiz.persistence.jooq.tables.records.LevelRecord;
import com.prj.quiz.persistence.jooq.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.prj.quiz.persistence.jooq.tables.Level.LEVEL;
import static com.prj.quiz.persistence.jooq.tables.User.USER;

@Repository
@Transactional
public class UserJooqRepository implements UserRepository {
    private final DSLContext dslContext;

    public UserJooqRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public User getById(Integer id) {
        final Record record = dslContext.select()
                .from(USER)
                .join(LEVEL).on(USER.LEVEL_ID.eq(LEVEL.ID))
                .where(USER.ID.eq(id))
                .fetchOne();

        return record == null ? null : toUser(record);
    }

    private User toUser(Record record) {
        final UserRecord userRecord = record.into(UserRecord.class);
        final LevelRecord levelRecord = record.into(LevelRecord.class);

        final Level level = new Level.Builder()
                .setId(levelRecord.getId())
                .setName(levelRecord.getName())
                .build();

        return new User.Builder()
                .setId(userRecord.getId())
                .setName(userRecord.getName())
                .setEmail(userRecord.getEmail())
                .setPassword(userRecord.getPassword())
                .setLevel(level)
                .build();
    }

    @Override
    public List<User> getAll() {
        final Result<Record> records = dslContext.select()
                .from(USER)
                .join(LEVEL).on(USER.LEVEL_ID.eq(LEVEL.ID))
                .fetch();

        return records.map(record -> toUser(record));
    }

    @Override
    public int save(User user) {
        return dslContext.insertInto(USER)
                .columns(USER.ID,
                        USER.NAME,
                        USER.EMAIL,
                        USER.PASSWORD)
                .values(null,
                        user.getName(),
                        user.getEmail(),
                        user.getPassword())
                .returning(USER.ID)
                .fetchOne()
                .getId();
    }

    @Override
    public int update(User user) {
        return dslContext.update(USER)
                .set(USER.NAME, user.getName())
                .set(USER.EMAIL, user.getEmail())
                .set(USER.PASSWORD, user.getPassword())
                .where(USER.ID.eq(user.getId()))
                .execute();
    }

    @Override
    public int delete(Integer id) {
        return dslContext.deleteFrom(USER)
                .where(USER.ID.eq(id))
                .execute();
    }
}
