package com.prj.quiz.persistence.repository;

import com.prj.quiz.model.Study;
import com.prj.quiz.persistence.jooq.tables.records.StudyRecord;
import com.prj.quiz.rest.filter.CommonFilter;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.prj.quiz.persistence.jooq.tables.Study.STUDY;
import static org.jooq.impl.DSL.trueCondition;

@Repository
@Transactional
public class StudyJooqRepository implements StudyRepository {
    private final DSLContext dslContext;

    public StudyJooqRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Study getById(Integer id) {
        final Record record = dslContext.select()
                .from(STUDY)
                .where(STUDY.ID.eq(id))
                .fetchOne();

        return record == null ? null : toStudy(record);
    }

    private Study toStudy(Record record) {
        final StudyRecord studyRecord = record.into(StudyRecord.class);

        return new Study.Builder()
                .setId(studyRecord.getId())
                .setWord(studyRecord.getWord())
                .setTranslation(studyRecord.getTranslation())
                .setContentId(studyRecord.getContentId())
                .setLevelId(studyRecord.getLevelId())
                .build();
    }

    @Override
    public List<Study> getAll(CommonFilter commonFilter) {
        final Condition queryCondition = buildCondition(commonFilter);

        final Result<Record> records = dslContext.select()
                .from(STUDY)
                .where(queryCondition)
                .fetch();

        return records.map(record -> toStudy(record));
    }

    private Condition buildCondition(CommonFilter filter) {
        Condition result = trueCondition();

        if (filter.getContentId() != null) {
            result = result.and(STUDY.CONTENT_ID.eq(filter.getContentId()));
        }

        if (filter.getLevelId() != null) {
            result = result.and(STUDY.LEVEL_ID.eq(filter.getLevelId()));
        }

        return result;
    }

    @Override
    public int save(Study study) {
        return dslContext.insertInto(STUDY)
                .columns(STUDY.ID,
                        STUDY.WORD,
                        STUDY.TRANSLATION,
                        STUDY.CONTENT_ID,
                        STUDY.LEVEL_ID)
                .values(null,
                        study.getWord(),
                        study.getTranslation(),
                        study.getContentId(),
                        study.getLevelId())
                .returning(STUDY.ID)
                .fetchOne()
                .getId();
    }

    @Override
    public int update(Study study) {
        return dslContext.update(STUDY)
                .set(STUDY.WORD, study.getWord())
                .set(STUDY.TRANSLATION, study.getTranslation())
                .set(STUDY.CONTENT_ID, study.getContentId())
                .set(STUDY.LEVEL_ID, study.getLevelId())
                .where(STUDY.ID.eq(study.getId()))
                .execute();
    }

    @Override
    public int delete(Integer id) {
        return dslContext.deleteFrom(STUDY)
                .where(STUDY.ID.eq(id))
                .execute();
    }
}
