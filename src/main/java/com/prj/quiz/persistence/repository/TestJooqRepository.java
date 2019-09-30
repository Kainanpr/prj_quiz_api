package com.prj.quiz.persistence.repository;

import com.prj.quiz.model.Test;
import com.prj.quiz.rest.filter.CommonFilter;
import com.prj.quiz.persistence.jooq.tables.records.TestRecord;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.prj.quiz.persistence.jooq.tables.Test.TEST;
import static org.jooq.impl.DSL.trueCondition;

@Repository
@Transactional
public class TestJooqRepository implements TestRepository {
    private final DSLContext dslContext;

    public TestJooqRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Test getById(Integer id) {
        final Record record = dslContext.select()
                .from(TEST)
                .where(TEST.ID.eq(id))
                .fetchOne();

        return record == null ? null : toTest(record);
    }

    private Test toTest(Record record) {
        final TestRecord testRecord = record.into(TestRecord.class);

        return new Test.Builder()
                .setId(testRecord.getId())
                .setQuestion(testRecord.getQuestion())
                .setOption1(testRecord.getOption_1())
                .setOption2(testRecord.getOption_2())
                .setOption3(testRecord.getOption_3())
                .setOption4(testRecord.getOption_4())
                .setOption5(testRecord.getOption_5())
                .setAnswer(testRecord.getAnswer())
                .setContentId(testRecord.getContentId())
                .setLevelId(testRecord.getLevelId())
                .build();
    }

    @Override
    public List<Test> getAll(CommonFilter commonFilter) {
        final Condition queryCondition = buildCondition(commonFilter);

        final Result<Record> records = dslContext.select()
                .from(TEST)
                .where(queryCondition)
                .fetch();

        return records.map(record -> toTest(record));
    }

    private Condition buildCondition(CommonFilter filter) {
        Condition result = trueCondition();

        if (filter.getContentId() != null) {
            result = result.and(TEST.CONTENT_ID.eq(filter.getContentId()));
        }

        if (filter.getLevelId() != null) {
            result = result.and(TEST.LEVEL_ID.eq(filter.getLevelId()));
        }

        return result;
    }

    @Override
    public int save(Test test) {
        return dslContext.insertInto(TEST)
                .columns(TEST.ID,
                        TEST.QUESTION,
                        TEST.OPTION_1,
                        TEST.OPTION_2,
                        TEST.OPTION_3,
                        TEST.OPTION_4,
                        TEST.OPTION_5,
                        TEST.ANSWER,
                        TEST.CONTENT_ID,
                        TEST.LEVEL_ID)
                .values(null,
                        test.getQuestion(),
                        test.getOption1(),
                        test.getOption2(),
                        test.getOption3(),
                        test.getOption4(),
                        test.getOption5(),
                        test.getAnswer(),
                        test.getContentId(),
                        test.getLevelId())
                .returning(TEST.ID)
                .fetchOne()
                .getId();
    }

    @Override
    public int update(Test test) {
        return dslContext.update(TEST)
                .set(TEST.QUESTION, test.getQuestion())
                .set(TEST.OPTION_1, test.getOption1())
                .set(TEST.OPTION_2, test.getOption2())
                .set(TEST.OPTION_3, test.getOption3())
                .set(TEST.OPTION_4, test.getOption4())
                .set(TEST.OPTION_5, test.getOption5())
                .set(TEST.ANSWER, test.getAnswer())
                .set(TEST.CONTENT_ID, test.getContentId())
                .set(TEST.LEVEL_ID, test.getLevelId())
                .where(TEST.ID.eq(test.getId()))
                .execute();
    }

    @Override
    public int delete(Integer id) {
        return dslContext.deleteFrom(TEST)
                .where(TEST.ID.eq(id))
                .execute();
    }
}
