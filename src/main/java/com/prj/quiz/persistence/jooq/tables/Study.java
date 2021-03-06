/*
 * This file is generated by jOOQ.
 */
package com.prj.quiz.persistence.jooq.tables;


import com.prj.quiz.persistence.jooq.DefaultSchema;
import com.prj.quiz.persistence.jooq.Indexes;
import com.prj.quiz.persistence.jooq.tables.records.StudyRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Study extends TableImpl<StudyRecord> {

    private static final long serialVersionUID = -853652806;

    /**
     * The reference instance of <code>study</code>
     */
    public static final Study STUDY = new Study();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<StudyRecord> getRecordType() {
        return StudyRecord.class;
    }

    /**
     * The column <code>study.id</code>.
     */
    public final TableField<StudyRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>study.word</code>.
     */
    public final TableField<StudyRecord, String> WORD = createField("word", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>study.translation</code>.
     */
    public final TableField<StudyRecord, String> TRANSLATION = createField("translation", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>study.content_id</code>.
     */
    public final TableField<StudyRecord, Integer> CONTENT_ID = createField("content_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>study.level_id</code>.
     */
    public final TableField<StudyRecord, Integer> LEVEL_ID = createField("level_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>study</code> table reference
     */
    public Study() {
        this(DSL.name("study"), null);
    }

    /**
     * Create an aliased <code>study</code> table reference
     */
    public Study(String alias) {
        this(DSL.name(alias), STUDY);
    }

    /**
     * Create an aliased <code>study</code> table reference
     */
    public Study(Name alias) {
        this(alias, STUDY);
    }

    private Study(Name alias, Table<StudyRecord> aliased) {
        this(alias, aliased, null);
    }

    private Study(Name alias, Table<StudyRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.STUDY_PRIMARY, Indexes.STUDY_STUDY_FK_CONTENT, Indexes.STUDY_STUDY_FK_LEVEL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<StudyRecord, Integer> getIdentity() {
        return Internal.createIdentity(com.prj.quiz.persistence.jooq.tables.Study.STUDY, com.prj.quiz.persistence.jooq.tables.Study.STUDY.ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<StudyRecord> getPrimaryKey() {
        return Internal.createUniqueKey(com.prj.quiz.persistence.jooq.tables.Study.STUDY, "KEY_study_PRIMARY", com.prj.quiz.persistence.jooq.tables.Study.STUDY.ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<StudyRecord>> getKeys() {
        return Arrays.<UniqueKey<StudyRecord>>asList(
              Internal.createUniqueKey(com.prj.quiz.persistence.jooq.tables.Study.STUDY, "KEY_study_PRIMARY", com.prj.quiz.persistence.jooq.tables.Study.STUDY.ID)
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Study as(String alias) {
        return new Study(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Study as(Name alias) {
        return new Study(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Study rename(String name) {
        return new Study(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Study rename(Name name) {
        return new Study(name, null);
    }
}
