package ru.productstar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.productstar.dto.Question;
import ru.productstar.dto.QuestionType;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.productstar.QuizEngine.QUIT_INPUT;

class QuizEngineTest {
    private final DataParser dataParserMock = mock(DataParser.class);
    private final Terminal terminalMock = mock(Terminal.class);
    private final FileReader fileReader = mock(FileReader.class);
    private final AnswerChecker answerCheckerMock = mock(AnswerChecker.class);
    private final QuizEngine quizEngine = new QuizEngine(fileReader, dataParserMock, answerCheckerMock, terminalMock);

    @BeforeEach
    void beforeEach() throws IOException, URISyntaxException {
        Mockito.reset(dataParserMock, terminalMock);

        when(fileReader.readFile()).thenReturn(emptyList());
        when(answerCheckerMock.isUserAnswerCorrect(any(), any())).thenReturn(true);
        when(terminalMock.readLine()).thenReturn("any answer");
    }

    @Test
    void correctlyPrintsIntroduction() throws IOException, URISyntaxException {
        quizEngine.run();

        verify(terminalMock, times(1)).printIntroduction();
    }

    @Test
    void correctlyCallsAnswerChecker() throws IOException, URISyntaxException {
        Question question1 = new Question(QuestionType.OPEN_QUESTION, "вопрос 1", "int", null);
        Question question2 = new Question(QuestionType.QUESTION_WITH_ANSWERS, "вопрос 2", "2", List.of("ответ 1", "ответ 2"));
        when(dataParserMock.parseQuestions(any())).thenReturn(List.of(question1, question2));
        when(terminalMock.readLine()).thenReturn("первый ответ", "второй ответ");

        quizEngine.run();
        verify(answerCheckerMock, times(1)).isUserAnswerCorrect("первый ответ", question1);
        verify(answerCheckerMock, times(1)).isUserAnswerCorrect("второй ответ", question2);
    }

    @Test
    void printsRetryUntilCorrectAnswerIsGiven() throws IOException, URISyntaxException {
        Question question1 = new Question(QuestionType.OPEN_QUESTION, "вопрос 1", "int", null);
        when(dataParserMock.parseQuestions(any())).thenReturn(List.of(question1));
        when(answerCheckerMock.isUserAnswerCorrect(any(), any())).thenReturn(false, true);

        quizEngine.run();

        verify(terminalMock, times(1)).printRetry();
        verify(terminalMock, times(2)).readLine();
    }

    @Test
    void congratulatesUserWithCorrectAnswerAfterEachCorrectAnswer() throws IOException, URISyntaxException {
        Question question1 = new Question(QuestionType.OPEN_QUESTION, "вопрос 1", "int", null);
        Question question2 = new Question(QuestionType.QUESTION_WITH_ANSWERS, "вопрос 2", "2", List.of("ответ 1", "ответ 2"));
        when(dataParserMock.parseQuestions(any())).thenReturn(List.of(question1, question2));

        quizEngine.run();
        verify(terminalMock, times(2)).congratulateUserWithCorrectAnswer();
    }

    @Test
    void congratulatesUserWithQuizFinish() throws IOException, URISyntaxException {
        Question question1 = new Question(QuestionType.OPEN_QUESTION, "вопрос 1", "int", null);
        when(dataParserMock.parseQuestions(any())).thenReturn(List.of(question1));

        quizEngine.run();
        verify(terminalMock, times(1)).congratulateUserWithQuizFinish();
    }

    @Test
    void callsPrintQuestionForEachQuestion() throws IOException, URISyntaxException {
        Question question1 = new Question(QuestionType.OPEN_QUESTION, "вопрос 1", "int", null);
        Question question2 = new Question(QuestionType.QUESTION_WITH_ANSWERS, "вопрос 2", "2", List.of("ответ 1", "ответ 2"));
        when(dataParserMock.parseQuestions(any())).thenReturn(List.of(question1, question2));

        quizEngine.run();

        verify(terminalMock, times(1)).printQuestion(question1);
        verify(terminalMock, times(1)).printQuestion(question2);
    }

    @Test
    void correctlyExitsWhenUserInputsQ() throws IOException, URISyntaxException {
        Question question1 = new Question(QuestionType.OPEN_QUESTION, "вопрос 1", "int", null);
        Question question2 = new Question(QuestionType.QUESTION_WITH_ANSWERS, "вопрос 2", "2", List.of("ответ 1", "ответ 2"));
        Question question3 = new Question(QuestionType.QUESTION_WITH_ANSWERS, "вопрос 3", "1", List.of("ответ 1", "ответ 2"));
        when(dataParserMock.parseQuestions(any())).thenReturn(List.of(question1, question2, question2, question3));
        when(terminalMock.readLine()).thenReturn("первый ответ", QUIT_INPUT);

        quizEngine.run();

        verify(terminalMock, times(1)).printQuestion(question1);
        verify(terminalMock, times(1)).printQuestion(question2);
        verify(terminalMock, times(1)).congratulateUserWithCorrectAnswer();
        verify(terminalMock, never()).congratulateUserWithQuizFinish();
    }

}