package ru.productstar;

import ru.productstar.dto.Question;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;

public class QuizEngine {
    public static final String QUIT_INPUT = "q";
    private final FileReader fileReader;
    private final DataParser dataParser;
    private final AnswerChecker answerChecker;
    private final Terminal terminal;

    private List<Question> questions;

    public QuizEngine(FileReader fileReader, DataParser dataParser, AnswerChecker answerChecker, Terminal terminal) {
        this.fileReader = fileReader;
        this.dataParser = dataParser;
        this.answerChecker = answerChecker;
        this.terminal = terminal;
    }

    public void run() throws Exception {
        questions = loadQuestions();
        startQuiz();
    }

    void startQuiz() {
        var questionsIterator = questions.iterator();
        terminal.printIntroduction();
        var currentQuestion = questionsIterator.next();
        while (questionsIterator.hasNext()) {
            terminal.printQuestion(currentQuestion);
            String input = terminal.readLine();
            var correctFlag = answerChecker.isUserAnswerCorrect(input, currentQuestion);
            if (correctFlag) {
                terminal.congratulateUserWithCorrectAnswer();
                currentQuestion = questionsIterator.next();
            } else {
                terminal.printRetry();
            }
        }
        terminal.congratulateUserWithQuizFinish();
    }

    List<Question> loadQuestions() throws Exception {
        List<String> data = fileReader.readFile();
        return dataParser.parseQuestions(data);
    }
}
