package com.prj.quiz.persistence.repository;

import com.prj.quiz.model.Game;
import com.prj.quiz.persistence.jooq.tables.records.GameRecord;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.prj.quiz.persistence.jooq.tables.Game.GAME;
import static com.prj.quiz.persistence.jooq.tables.Study.STUDY;
import static com.prj.quiz.persistence.jooq.tables.Test.TEST;
import static org.jooq.impl.DSL.trueCondition;

@Repository
@Transactional
public class GameJooqRepository implements GameRepository {
    private final DSLContext dslContext;

    public GameJooqRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Game getById(Integer id) {
        final Record record = dslContext.select()
                .from(GAME)
                .where(GAME.ID.eq(id))
                .fetchOne();

        return record == null ? null : toGame(record);
    }

    private Game toGame(Record record) {
        final GameRecord gameRecord = record.into(GameRecord.class);

        return new Game.Builder()
                .setId(gameRecord.getId())
                .setUserId(gameRecord.getUserId())
                .setContentId(gameRecord.getContentId())
                .setLevelId(gameRecord.getLevelId())
                .setHasPractice(checkForPractices(gameRecord.getContentId(), gameRecord.getLevelId()))
                .build();
    }

    private boolean checkForPractices(Integer contentId, Integer levelId) {
        final List<Record> studyRecords = dslContext.select()
                .from(STUDY)
                .where(STUDY.CONTENT_ID.eq(contentId))
                .and(STUDY.LEVEL_ID.eq(levelId))
                .fetch();

        if (!studyRecords.isEmpty()) {
            return true;
        }

        final List<Record> testRecords = dslContext.select()
                .from(TEST)
                .where(TEST.CONTENT_ID.eq(contentId))
                .and(TEST.LEVEL_ID.eq(levelId))
                .fetch();

        if (!testRecords.isEmpty()) {
            return true;
        }

        return false;
    }

    @Override
    public List<Game> getAll(Integer userId, Integer contentId) {
        final Condition queryCondition = buildCondition(userId, contentId);

        final Result<Record> records = dslContext.select()
                .from(GAME)
                .where(queryCondition)
                .fetch();

        return records.map(record -> toGame(record));
    }

    private Condition buildCondition(Integer userId, Integer contentId) {
        Condition result = trueCondition();

        if (userId != null) {
            result = result.and(GAME.USER_ID.eq(userId));
        }

        if (contentId != null) {
            result = result.and(GAME.CONTENT_ID.eq(contentId));
        }

        return result;
    }

    @Override
    public int save(Game game) {
        return dslContext.insertInto(GAME)
                .columns(GAME.ID,
                        GAME.USER_ID,
                        GAME.CONTENT_ID,
                        GAME.LEVEL_ID)
                .values(null,
                        game.getUserId(),
                        game.getContentId(),
                        game.getLevelId())
                .returning(GAME.ID)
                .fetchOne()
                .getId();
    }

    @Override
    public int update(Game game) {
        return dslContext.update(GAME)
                .set(GAME.LEVEL_ID, game.getLevelId())
                .where(GAME.ID.eq(game.getId()))
                .execute();
    }

    @Override
    public int delete(Integer contentId) {
        return dslContext.deleteFrom(GAME)
                .where(GAME.CONTENT_ID.eq(contentId))
                .execute();
    }
}
