package com.prj.quiz.persistence.repository;


import com.prj.quiz.model.User;
import com.prj.quiz.persistence.jooq.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
                .where(USER.ID.eq(id))
                .fetchOne();

        return record == null ? null : toUser(record);
    }

    private User toUser(Record record) {
        final UserRecord userRecord = record.into(UserRecord.class);

        return new User.Builder()
                .setId(userRecord.getId())
                .setName(userRecord.getName())
                .setEmail(userRecord.getEmail())
                .setSenha(userRecord.getSenha())
                .build();
    }

    @Override
    public List<User> getAll() {
        final Result<Record> records = dslContext.select()
                .from(USER)
                .fetch();

        return records.map(record -> toUser(record));
    }

    @Override
    public int save(User user) {
        return dslContext.insertInto(USER)
                .columns(USER.ID,
                        USER.NAME,
                        USER.EMAIL,
                        USER.SENHA)
                .values(null,
                        user.getName(),
                        user.getEmail(),
                        user.getSenha())
                .returning(USER.ID)
                .fetchOne()
                .getId();
    }

    @Override
    public int update(User user) {
        return dslContext.update(USER)
                .set(USER.NAME, user.getName())
                .set(USER.EMAIL, user.getEmail())
                .set(USER.SENHA, user.getSenha())
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
