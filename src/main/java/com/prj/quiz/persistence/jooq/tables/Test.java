/*
 * This file is generated by jOOQ.
 */
package com.prj.quiz.persistence.jooq.tables;


import com.prj.quiz.persistence.jooq.DefaultSchema;
import com.prj.quiz.persistence.jooq.Indexes;
import com.prj.quiz.persistence.jooq.tables.records.TestRecord;

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
public class Test extends TableImpl<TestRecord> {

    private static final long serialVersionUID = 1579877825;

    /**
     * The reference instance of <code>test</code>
     */
    public static final Test TEST = new Test();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TestRecord> getRecordType() {
        return TestRecord.class;
    }

    /**
     * The column <code>test.id</code>.
     */
    public final TableField<TestRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>test.question</code>.
     */
    public final TableField<TestRecord, String> QUESTION = createField("question", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>test.option_1</code>.
     */
    public final TableField<TestRecord, String> OPTION_1 = createField("option_1", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>test.option_2</code>.
     */
    public final TableField<TestRecord, String> OPTION_2 = createField("option_2", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>test.option_3</code>.
     */
    public final TableField<TestRecord, String> OPTION_3 = createField("option_3", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>test.option_4</code>.
     */
    public final TableField<TestRecord, String> OPTION_4 = createField("option_4", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>test.option_5</code>.
     */
    public final TableField<TestRecord, String> OPTION_5 = createField("option_5", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>test.answer</code>.
     */
    public final TableField<TestRecord, String> ANSWER = createField("answer", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>test.content_id</code>.
     */
    public final TableField<TestRecord, Integer> CONTENT_ID = createField("content_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>test.level_id</code>.
     */
    public final TableField<TestRecord, Integer> LEVEL_ID = createField("level_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>test</code> table reference
     */
    public Test() {
        this(DSL.name("test"), null);
    }

    /**
     * Create an aliased <code>test</code> table reference
     */
    public Test(String alias) {
        this(DSL.name(alias), TEST);
    }

    /**
     * Create an aliased <code>test</code> table reference
     */
    public Test(Name alias) {
        this(alias, TEST);
    }

    private Test(Name alias, Table<TestRecord> aliased) {
        this(alias, aliased, null);
    }

    private Test(Name alias, Table<TestRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.TEST_PRIMARY, Indexes.TEST_TEST_FK_CONTENT, Indexes.TEST_TEST_FK_LEVEL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<TestRecord, Integer> getIdentity() {
        return Internal.createIdentity(com.prj.quiz.persistence.jooq.tables.Test.TEST, com.prj.quiz.persistence.jooq.tables.Test.TEST.ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TestRecord> getPrimaryKey() {
        return Internal.createUniqueKey(com.prj.quiz.persistence.jooq.tables.Test.TEST, "KEY_test_PRIMARY", com.prj.quiz.persistence.jooq.tables.Test.TEST.ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TestRecord>> getKeys() {
        return Arrays.<UniqueKey<TestRecord>>asList(
              Internal.createUniqueKey(com.prj.quiz.persistence.jooq.tables.Test.TEST, "KEY_test_PRIMARY", com.prj.quiz.persistence.jooq.tables.Test.TEST.ID)
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Test as(String alias) {
        return new Test(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Test as(Name alias) {
        return new Test(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Test rename(String name) {
        return new Test(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Test rename(Name name) {
        return new Test(name, null);
    }
}