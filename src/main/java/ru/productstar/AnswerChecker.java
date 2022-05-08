package ru.productstar;

import ru.productstar.dto.Question;

import java.util.Locale;

public class AnswerChecker {
    public boolean isUserAnswerCorrect(String userAnswer, Question question) {
        var userAnswerToLowerCase = userAnswer.trim().toLowerCase(Locale.ROOT);
        if (userAnswerToLowerCase.equals("первый ответ")) {
            userAnswerToLowerCase = "1";
        }
        if (userAnswerToLowerCase.equals("второй ответ")) {
            userAnswerToLowerCase = "2";
        }
        if (userAnswerToLowerCase.equals("третий ответ")) {
            userAnswerToLowerCase = "3";
        }
        var correctAnswer = question.getCorrectAnswer().trim().toLowerCase(Locale.ROOT);
        if (userAnswerToLowerCase.equals(correctAnswer)) {
            return true;
        } else {
            return false;
        }

    }
}
