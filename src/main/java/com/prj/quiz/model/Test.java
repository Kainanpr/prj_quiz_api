package com.prj.quiz.model;

import com.prj.quiz.util.ObjectBuilder;
import org.springframework.util.Assert;

import java.util.Objects;

public final class Test {
    private final Integer id;
    private final String question;
    private final String option1;
    private final String option2;
    private final String option3;
    private final String option4;
    private final String option5;
    private final String answer;
    private final Integer contentId;
    private final Integer levelId;

    private Test(Integer id, String question, String option1, String option2, String option3,
                 String option4, String option5, String answer, Integer contentId, Integer levelId) {
        this.id = id;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.option5 = option5;
        this.answer = answer;
        this.contentId = contentId;
        this.levelId = levelId;
    }

    public Integer getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getOption5() {
        return option5;
    }

    public String getAnswer() {
        return answer;
    }

    public Integer getContentId() {
        return contentId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(id, test.id) &&
                Objects.equals(question, test.question) &&
                Objects.equals(option1, test.option1) &&
                Objects.equals(option2, test.option2) &&
                Objects.equals(option3, test.option3) &&
                Objects.equals(option4, test.option4) &&
                Objects.equals(option5, test.option5) &&
                Objects.equals(answer, test.answer) &&
                Objects.equals(contentId, test.contentId) &&
                Objects.equals(levelId, test.levelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, option1, option2, option3, option4, option5, answer, contentId, levelId);
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                ", option5='" + option5 + '\'' +
                ", answer='" + answer + '\'' +
                ", contentId=" + contentId +
                ", levelId=" + levelId +
                '}';
    }

    public static final class Builder implements ObjectBuilder {
        private Integer id;
        private String question;
        private String option1;
        private String option2;
        private String option3;
        private String option4;
        private String option5;
        private String answer;
        private Integer contentId;
        private Integer levelId;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setQuestion(String question) {
            this.question = question;
            return this;
        }

        public Builder setOption1(String option1) {
            this.option1 = option1;
            return this;
        }

        public Builder setOption2(String option2) {
            this.option2 = option2;
            return this;
        }

        public Builder setOption3(String option3) {
            this.option3 = option3;
            return this;
        }

        public Builder setOption4(String option4) {
            this.option4 = option4;
            return this;
        }

        public Builder setOption5(String option5) {
            this.option5 = option5;
            return this;
        }

        public Builder setAnswer(String answer) {
            this.answer = answer;
            return this;
        }

        public Builder setContentId(Integer contentId) {
            this.contentId = contentId;
            return this;
        }

        public Builder setLevelId(Integer levelId) {
            this.levelId = levelId;
            return this;
        }

        @Override
        public Test build() {
            if (id != null) {
                Assert.isTrue(id > 0, "ID must be greater than 0");
            }

            Assert.notNull(question, "Question must not be null");
            Assert.notNull(option1, "Option1 must not be null");
            Assert.notNull(option2, "Option2 must not be null");
            Assert.notNull(option3, "Option3 must not be null");
            Assert.notNull(option4, "Option4 must not be null");
            Assert.notNull(option5, "Option5 must not be null");
            Assert.notNull(answer, "Answer must not be null");
            Assert.notNull(contentId, "ContentId must not be null");
            Assert.notNull(levelId, "LevelId must not be null");

            return new Test(id, question, option1, option2, option3, option4, option5, answer, contentId, levelId);
        }
    }
}
