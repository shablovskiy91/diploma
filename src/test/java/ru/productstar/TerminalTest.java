package ru.productstar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.productstar.dto.Question;
import ru.productstar.dto.QuestionType;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TerminalTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private final Terminal terminal = new Terminal();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void printIntroduction() {
        terminal.printIntroduction();
        assertThat(outContent.toString()).contains("Добро пожаловать!");
    }

    @Test
    void printOpenQuestion() {
        terminal.printQuestion(new Question(QuestionType.OPEN_QUESTION, "вопрос 1", "int", null));

        assertThat(outContent.toString()).contains("вопрос 1");
    }

    @Test
    void printQuestionWithAnswers() {
        terminal.printQuestion(new Question(QuestionType.QUESTION_WITH_ANSWERS, "вопрос 2", "2", List.of("ответ 1", "ответ 2")));

        assertThat(outContent.toString()).contains("вопрос 2", "ответ 1", "ответ 2");
    }

    @Test
    void congratulateUserWithCorrectAnswer() {
        terminal.congratulateUserWithCorrectAnswer();
        assertThat(outContent.toString()).contains("Поздравляем");
    }

    @Test
    void congratulateUserWithQuizFinish() {
        terminal.congratulateUserWithQuizFinish();
        assertThat(outContent.toString()).contains("Тест завершен");
    }

    @Test
    void printRetry() {
        terminal.printRetry();
        assertThat(outContent.toString()).contains("попробуйте еще раз");
    }
}