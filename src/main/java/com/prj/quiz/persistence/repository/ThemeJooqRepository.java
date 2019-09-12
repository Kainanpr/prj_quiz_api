package com.prj.quiz.persistence.repository;

import com.prj.quiz.model.Theme;
import com.prj.quiz.persistence.jooq.tables.records.ThemeRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.prj.quiz.persistence.jooq.tables.Theme.THEME;

@Repository
@Transactional
public class ThemeJooqRepository implements ThemeRepository {
    private final DSLContext dslContext;

    public ThemeJooqRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Theme getById(Integer id) {
        final Record record = dslContext.select()
                .from(THEME)
                .where(THEME.ID.eq(id))
                .fetchOne();

        return record == null ? null : toTheme(record);
    }

    private Theme toTheme(Record record) {
        final ThemeRecord themeRecord = record.into(ThemeRecord.class);

        return new Theme.Builder()
                .setId(themeRecord.getId())
                .setName(themeRecord.getName())
                .build();
    }

    @Override
    public List<Theme> getAll() {
        final Result<Record> records = dslContext.select()
                .from(THEME)
                .fetch();

        return records.map(record -> toTheme(record));
    }

    @Override
    public int save(Theme theme) {
        return dslContext.insertInto(THEME)
                .columns(THEME.ID,
                        THEME.NAME)
                .values(null,
                        theme.getName())
                .returning(THEME.ID)
                .fetchOne()
                .getId();
    }

    @Override
    public int update(Theme theme) {
        return dslContext.update(THEME)
                .set(THEME.NAME, theme.getName())
                .where(THEME.ID.eq(theme.getId()))
                .execute();
    }

    @Override
    public int delete(Integer id) {
        return dslContext.deleteFrom(THEME)
                .where(THEME.ID.eq(id))
                .execute();
    }
}
