/*
 * This file is generated by jOOQ.
 */
package com.prj.quiz.persistence.jooq.tables;


import com.prj.quiz.persistence.jooq.DefaultSchema;
import com.prj.quiz.persistence.jooq.Indexes;
import com.prj.quiz.persistence.jooq.tables.records.ContentRecord;

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
public class Content extends TableImpl<ContentRecord> {

    private static final long serialVersionUID = -603397132;

    /**
     * The reference instance of <code>content</code>
     */
    public static final Content CONTENT = new Content();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ContentRecord> getRecordType() {
        return ContentRecord.class;
    }

    /**
     * The column <code>content.id</code>.
     */
    public final TableField<ContentRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>content.name</code>.
     */
    public final TableField<ContentRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>content.theme_id</code>.
     */
    public final TableField<ContentRecord, Integer> THEME_ID = createField("theme_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>content</code> table reference
     */
    public Content() {
        this(DSL.name("content"), null);
    }

    /**
     * Create an aliased <code>content</code> table reference
     */
    public Content(String alias) {
        this(DSL.name(alias), CONTENT);
    }

    /**
     * Create an aliased <code>content</code> table reference
     */
    public Content(Name alias) {
        this(alias, CONTENT);
    }

    private Content(Name alias, Table<ContentRecord> aliased) {
        this(alias, aliased, null);
    }

    private Content(Name alias, Table<ContentRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.CONTENT_CONTENT_FK_THEME, Indexes.CONTENT_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<ContentRecord, Integer> getIdentity() {
        return Internal.createIdentity(com.prj.quiz.persistence.jooq.tables.Content.CONTENT, com.prj.quiz.persistence.jooq.tables.Content.CONTENT.ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ContentRecord> getPrimaryKey() {
        return Internal.createUniqueKey(com.prj.quiz.persistence.jooq.tables.Content.CONTENT, "KEY_content_PRIMARY", com.prj.quiz.persistence.jooq.tables.Content.CONTENT.ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ContentRecord>> getKeys() {
        return Arrays.<UniqueKey<ContentRecord>>asList(
              Internal.createUniqueKey(com.prj.quiz.persistence.jooq.tables.Content.CONTENT, "KEY_content_PRIMARY", com.prj.quiz.persistence.jooq.tables.Content.CONTENT.ID)
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Content as(String alias) {
        return new Content(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Content as(Name alias) {
        return new Content(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Content rename(String name) {
        return new Content(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Content rename(Name name) {
        return new Content(name, null);
    }
}
