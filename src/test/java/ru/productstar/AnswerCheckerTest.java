package ru.productstar;

import org.junit.jupiter.api.Test;
import ru.productstar.dto.Question;
import ru.productstar.dto.QuestionType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnswerCheckerTest {
    private final AnswerChecker answerChecker = new AnswerChecker();
    @Test
    void correctlyChecksAnswerForOpenQuestion() {
        assertTrue(answerChecker.isUserAnswerCorrect("int",
                new Question(QuestionType.OPEN_QUESTION, "вопрос 1", "int", null))
        );
        assertFalse(answerChecker.isUserAnswerCorrect("float",
                new Question(QuestionType.OPEN_QUESTION, "вопрос 1", "int", null))
        );
    }

    @Test
    void checksAnswerInDifferentRegistry() {
        assertTrue(answerChecker.isUserAnswerCorrect("InT",
                new Question(QuestionType.OPEN_QUESTION, "вопрос 1", "int", null))
        );
        assertFalse(answerChecker.isUserAnswerCorrect("FloAT",
                new Question(QuestionType.OPEN_QUESTION, "вопрос 1", "int", null))
        );
    }

    @Test
    void correctlyChecksAnswerForQuestionWithAnswers() {
        assertTrue(answerChecker.isUserAnswerCorrect("2",
                new Question(QuestionType.QUESTION_WITH_ANSWERS, "вопрос 2", "2", List.of("ответ 1", "ответ 2")))
        );
        assertFalse(answerChecker.isUserAnswerCorrect("1",
                new Question(QuestionType.QUESTION_WITH_ANSWERS, "вопрос 2", "2", List.of("ответ 1", "ответ 2")))
        );
    }

    @Test
    void correctlyChecksAnswerOutOfRangeForQuestionWithAnswers() {
        assertFalse(answerChecker.isUserAnswerCorrect("10",
                new Question(QuestionType.QUESTION_WITH_ANSWERS, "вопрос 2", "2", List.of("ответ 1", "ответ 2")))
        );
    }

}