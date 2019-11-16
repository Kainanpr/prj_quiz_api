package com.prj.quiz.persistence.repository;

import com.prj.quiz.model.Content;
import com.prj.quiz.model.Theme;
import com.prj.quiz.persistence.jooq.tables.records.ContentRecord;
import com.prj.quiz.persistence.jooq.tables.records.ThemeRecord;
import com.prj.quiz.rest.filter.CommonFilter;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.prj.quiz.persistence.jooq.tables.Content.CONTENT;
import static com.prj.quiz.persistence.jooq.tables.Theme.THEME;
import static org.jooq.impl.DSL.trueCondition;

@Repository
@Transactional
public class ContentJooqRepository implements ContentRepository {
    private final DSLContext dslContext;

    public ContentJooqRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Content getById(Integer id) {
        final Record record = dslContext.select()
                .from(CONTENT)
                .where(CONTENT.ID.eq(id))
                .fetchOne();

        return record == null ? null : toContent(record);
    }

    private Content toContent(Record record) {
        final ContentRecord contentRecord = record.into(ContentRecord.class);
        final ThemeRecord themeRecord = record.into(ThemeRecord.class);

        Theme theme = new Theme.Builder()
                .setId(themeRecord.getId())
                .setName(themeRecord.getName())
                .build();

        return new Content.Builder()
                .setId(contentRecord.getId())
                .setName(contentRecord.getName())
                .setTheme(theme)
                .build();
    }

    @Override
    public List<Content> getAll(Integer themeId, CommonFilter commonFilter) {
        final Condition queryCondition = buildCondition(themeId, commonFilter);

        final Result<Record> records = dslContext.select()
                .from(CONTENT.join(THEME)
                        .on(CONTENT.THEME_ID.eq(THEME.ID)))
                .where(queryCondition)
                .fetch();

        return records.map(record -> toContent(record));
    }

    private Condition buildCondition(Integer themeId, CommonFilter filter) {
        Condition result = trueCondition();

        if (themeId != null) {
            result = result.and(CONTENT.THEME_ID.eq(themeId));
        }
        if (filter != null && filter.getContentName() != null) {
            String contentName = "%" + filter.getContentName() + "%";
            result = result.and(CONTENT.NAME.likeIgnoreCase(contentName));
        }

        return result;
    }

    @Override
    public int save(Content content) {
        return dslContext.insertInto(CONTENT)
                .columns(CONTENT.ID,
                        CONTENT.NAME,
                        CONTENT.THEME_ID)
                .values(null,
                        content.getName(),
                        content.getTheme().getId())
                .returning(CONTENT.ID)
                .fetchOne()
                .getId();
    }

    @Override
    public int update(Content content) {
        return dslContext.update(CONTENT)
                .set(CONTENT.NAME, content.getName())
                .set(CONTENT.THEME_ID, content.getTheme().getId())
                .where(CONTENT.ID.eq(content.getId()))
                .execute();
    }

    @Override
    public int delete(Integer id) {
        return dslContext.deleteFrom(CONTENT)
                .where(CONTENT.ID.eq(id))
                .execute();
    }
}
