package com.prj.quiz.persistence.repository;

import com.prj.quiz.model.Level;
import com.prj.quiz.persistence.jooq.tables.records.LevelRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.prj.quiz.persistence.jooq.tables.Level.LEVEL;

@Repository
@Transactional
public class LevelJooqRepository implements LevelRepository {
    private final DSLContext dslContext;

    public LevelJooqRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public List<Level> getAll() {
        final Result<Record> records = dslContext.select()
                .from(LEVEL)
                .fetch();

        return records.map(record -> toLevel(record));
    }

    private Level toLevel(Record record) {
        final LevelRecord levelRecord = record.into(LevelRecord.class);

        return new Level.Builder()
                .setId(levelRecord.getId())
                .setName(levelRecord.getName())
                .build();
    }
}
