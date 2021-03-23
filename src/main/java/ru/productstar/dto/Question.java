package ru.productstar.dto;

import java.util.List;

public class Question {
    private QuestionType questionType;
    private String text;
    private String correctAnswer;
    private List<String> answerOptions;

    public Question(QuestionType questionType, String text, String correctAnswer, List<String> answerOptions) {
        this.questionType = questionType;
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.answerOptions = answerOptions;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public String getText() {
        return text;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getAnswerOptions() {
        return answerOptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (questionType != question.questionType) return false;
        if (!text.equals(question.text)) return false;
        if (!correctAnswer.equals(question.correctAnswer)) return false;
        return answerOptions.equals(question.answerOptions);
    }

    @Override
    public int hashCode() {
        int result = questionType.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + correctAnswer.hashCode();
        result = 31 * result + answerOptions.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionType=" + questionType +
                ", text='" + text + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", answerOptions=" + answerOptions +
                '}';
    }
}
