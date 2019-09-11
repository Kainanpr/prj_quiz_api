/*
 * This file is generated by jOOQ.
 */
package com.prj.quiz.persistence.jooq.tables.records;


import com.prj.quiz.persistence.jooq.tables.User;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


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
public class UserRecord extends UpdatableRecordImpl<UserRecord> implements Record5<Integer, String, String, String, Integer> {

    private static final long serialVersionUID = 547146679;

    /**
     * Setter for <code>user.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>user.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>user.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>user.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>user.email</code>.
     */
    public void setEmail(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>user.email</code>.
     */
    public String getEmail() {
        return (String) get(2);
    }

    /**
     * Setter for <code>user.password</code>.
     */
    public void setPassword(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>user.password</code>.
     */
    public String getPassword() {
        return (String) get(3);
    }

    /**
     * Setter for <code>user.level_id</code>.
     */
    public void setLevelId(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>user.level_id</code>.
     */
    public Integer getLevelId() {
        return (Integer) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, String, String, String, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, String, String, String, Integer> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return User.USER.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return User.USER.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return User.USER.EMAIL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return User.USER.PASSWORD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return User.USER.LEVEL_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getEmail();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component5() {
        return getLevelId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getEmail();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getLevelId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value3(String value) {
        setEmail(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value4(String value) {
        setPassword(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value5(Integer value) {
        setLevelId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord values(Integer value1, String value2, String value3, String value4, Integer value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserRecord
     */
    public UserRecord() {
        super(User.USER);
    }

    /**
     * Create a detached, initialised UserRecord
     */
    public UserRecord(Integer id, String name, String email, String password, Integer levelId) {
        super(User.USER);

        set(0, id);
        set(1, name);
        set(2, email);
        set(3, password);
        set(4, levelId);
    }
}
