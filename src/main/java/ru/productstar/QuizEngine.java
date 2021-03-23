package ru.productstar;

import ru.productstar.dto.Question;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

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

    public void run() throws IOException, URISyntaxException {
        questions = loadQuestions();
        terminal.printIntroduction();

        startQuiz();
    }

    void startQuiz() {

    }

    List<Question> loadQuestions() throws IOException, URISyntaxException {
        List<String> data = fileReader.readFile();
        return dataParser.parseQuestions(data);
    }
}
