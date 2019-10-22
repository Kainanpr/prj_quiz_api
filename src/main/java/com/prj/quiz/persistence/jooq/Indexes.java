/*
 * This file is generated by jOOQ.
 */
package com.prj.quiz.persistence.jooq;


import com.prj.quiz.persistence.jooq.tables.Content;
import com.prj.quiz.persistence.jooq.tables.Game;
import com.prj.quiz.persistence.jooq.tables.Level;
import com.prj.quiz.persistence.jooq.tables.Study;
import com.prj.quiz.persistence.jooq.tables.Test;
import com.prj.quiz.persistence.jooq.tables.Theme;
import com.prj.quiz.persistence.jooq.tables.User;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code></code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index CONTENT_CONTENT_FK_THEME = Indexes0.CONTENT_CONTENT_FK_THEME;
    public static final Index CONTENT_PRIMARY = Indexes0.CONTENT_PRIMARY;
    public static final Index GAME_GAME_FK_CONTENT = Indexes0.GAME_GAME_FK_CONTENT;
    public static final Index GAME_GAME_FK_LEVEL = Indexes0.GAME_GAME_FK_LEVEL;
    public static final Index GAME_GAME_FK_USER = Indexes0.GAME_GAME_FK_USER;
    public static final Index GAME_PRIMARY = Indexes0.GAME_PRIMARY;
    public static final Index LEVEL_PRIMARY = Indexes0.LEVEL_PRIMARY;
    public static final Index STUDY_PRIMARY = Indexes0.STUDY_PRIMARY;
    public static final Index STUDY_STUDY_FK_CONTENT = Indexes0.STUDY_STUDY_FK_CONTENT;
    public static final Index STUDY_STUDY_FK_LEVEL = Indexes0.STUDY_STUDY_FK_LEVEL;
    public static final Index TEST_PRIMARY = Indexes0.TEST_PRIMARY;
    public static final Index TEST_TEST_FK_CONTENT = Indexes0.TEST_TEST_FK_CONTENT;
    public static final Index TEST_TEST_FK_LEVEL = Indexes0.TEST_TEST_FK_LEVEL;
    public static final Index THEME_PRIMARY = Indexes0.THEME_PRIMARY;
    public static final Index USER_PRIMARY = Indexes0.USER_PRIMARY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index CONTENT_CONTENT_FK_THEME = Internal.createIndex("content_fk_theme", Content.CONTENT, new OrderField[] { Content.CONTENT.THEME_ID }, false);
        public static Index CONTENT_PRIMARY = Internal.createIndex("PRIMARY", Content.CONTENT, new OrderField[] { Content.CONTENT.ID }, true);
        public static Index GAME_GAME_FK_CONTENT = Internal.createIndex("game_fk_content", Game.GAME, new OrderField[] { Game.GAME.CONTENT_ID }, false);
        public static Index GAME_GAME_FK_LEVEL = Internal.createIndex("game_fk_level", Game.GAME, new OrderField[] { Game.GAME.LEVEL_ID }, false);
        public static Index GAME_GAME_FK_USER = Internal.createIndex("game_fk_user", Game.GAME, new OrderField[] { Game.GAME.USER_ID }, false);
        public static Index GAME_PRIMARY = Internal.createIndex("PRIMARY", Game.GAME, new OrderField[] { Game.GAME.ID }, true);
        public static Index LEVEL_PRIMARY = Internal.createIndex("PRIMARY", Level.LEVEL, new OrderField[] { Level.LEVEL.ID }, true);
        public static Index STUDY_PRIMARY = Internal.createIndex("PRIMARY", Study.STUDY, new OrderField[] { Study.STUDY.ID }, true);
        public static Index STUDY_STUDY_FK_CONTENT = Internal.createIndex("study_fk_content", Study.STUDY, new OrderField[] { Study.STUDY.CONTENT_ID }, false);
        public static Index STUDY_STUDY_FK_LEVEL = Internal.createIndex("study_fk_level", Study.STUDY, new OrderField[] { Study.STUDY.LEVEL_ID }, false);
        public static Index TEST_PRIMARY = Internal.createIndex("PRIMARY", Test.TEST, new OrderField[] { Test.TEST.ID }, true);
        public static Index TEST_TEST_FK_CONTENT = Internal.createIndex("test_fk_content", Test.TEST, new OrderField[] { Test.TEST.CONTENT_ID }, false);
        public static Index TEST_TEST_FK_LEVEL = Internal.createIndex("test_fk_level", Test.TEST, new OrderField[] { Test.TEST.LEVEL_ID }, false);
        public static Index THEME_PRIMARY = Internal.createIndex("PRIMARY", Theme.THEME, new OrderField[] { Theme.THEME.ID }, true);
        public static Index USER_PRIMARY = Internal.createIndex("PRIMARY", User.USER, new OrderField[] { User.USER.ID }, true);
    }
}
