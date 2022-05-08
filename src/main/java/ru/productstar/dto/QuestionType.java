package ru.productstar.dto;

public enum QuestionType {
    QUESTION_WITH_ANSWERS ("questionWithAnswers"),
    OPEN_QUESTION ("openQuestion");

    private final String name;

    QuestionType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
