/*
 * This file is generated by jOOQ.
 */
package com.prj.quiz.persistence.jooq.tables;


import com.prj.quiz.persistence.jooq.DefaultSchema;
import com.prj.quiz.persistence.jooq.Indexes;
import com.prj.quiz.persistence.jooq.tables.records.LevelRecord;

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
public class Level extends TableImpl<LevelRecord> {

    private static final long serialVersionUID = -166676884;

    /**
     * The reference instance of <code>level</code>
     */
    public static final Level LEVEL = new Level();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<LevelRecord> getRecordType() {
        return LevelRecord.class;
    }

    /**
     * The column <code>level.id</code>.
     */
    public final TableField<LevelRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>level.name</code>.
     */
    public final TableField<LevelRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * Create a <code>level</code> table reference
     */
    public Level() {
        this(DSL.name("level"), null);
    }

    /**
     * Create an aliased <code>level</code> table reference
     */
    public Level(String alias) {
        this(DSL.name(alias), LEVEL);
    }

    /**
     * Create an aliased <code>level</code> table reference
     */
    public Level(Name alias) {
        this(alias, LEVEL);
    }

    private Level(Name alias, Table<LevelRecord> aliased) {
        this(alias, aliased, null);
    }

    private Level(Name alias, Table<LevelRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.LEVEL_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<LevelRecord, Integer> getIdentity() {
        return Internal.createIdentity(com.prj.quiz.persistence.jooq.tables.Level.LEVEL, com.prj.quiz.persistence.jooq.tables.Level.LEVEL.ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<LevelRecord> getPrimaryKey() {
        return Internal.createUniqueKey(com.prj.quiz.persistence.jooq.tables.Level.LEVEL, "KEY_level_PRIMARY", com.prj.quiz.persistence.jooq.tables.Level.LEVEL.ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<LevelRecord>> getKeys() {
        return Arrays.<UniqueKey<LevelRecord>>asList(
              Internal.createUniqueKey(com.prj.quiz.persistence.jooq.tables.Level.LEVEL, "KEY_level_PRIMARY", com.prj.quiz.persistence.jooq.tables.Level.LEVEL.ID)
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Level as(String alias) {
        return new Level(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Level as(Name alias) {
        return new Level(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Level rename(String name) {
        return new Level(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Level rename(Name name) {
        return new Level(name, null);
    }
}
