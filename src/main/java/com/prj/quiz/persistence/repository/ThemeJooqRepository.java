package com.prj.quiz.persistence.repository;

import com.prj.quiz.model.Theme;
import com.prj.quiz.persistence.jooq.tables.records.ThemeRecord;
import com.prj.quiz.rest.filter.CommonFilter;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.prj.quiz.persistence.jooq.tables.Theme.THEME;
import static org.jooq.impl.DSL.trueCondition;

@Repository
@Transactional
public class ThemeJooqRepository implements ThemeRepository {
    private final DSLContext dslContext;
    private final ContentRepository contentRepository;

    public ThemeJooqRepository(DSLContext dslContext, ContentRepository contentRepository) {
        this.dslContext = dslContext;
        this.contentRepository = contentRepository;
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
                .setContents(contentRepository.getAll(themeRecord.getId(), null))
                .build();
    }

    @Override
    public List<Theme> getAll(CommonFilter commonFilter) {
        final Condition queryCondition = buildCondition(commonFilter);

        final Result<Record> records = dslContext.select()
                .from(THEME)
                .where(queryCondition)
                .fetch();

        return records.map(record -> toTheme(record));
    }

    private Condition buildCondition(CommonFilter filter) {
        Condition result = trueCondition();

        if (filter.getThemeName() != null) {
            String themeName = "%" + filter.getThemeName() + "%";
            result = result.and(THEME.NAME.likeIgnoreCase(themeName));
        }

        return result;
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
