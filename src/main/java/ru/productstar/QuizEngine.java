package ru.productstar;

import com.sun.source.tree.IfTree;
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
    private Question currentQuestion;

    public QuizEngine(FileReader fileReader, DataParser dataParser, AnswerChecker answerChecker, Terminal terminal) {
        this.fileReader = fileReader;
        this.dataParser = dataParser;
        this.answerChecker = answerChecker;
        this.terminal = terminal;
    }

    public void run() throws Exception {
        questions = loadQuestions();

        terminal.printIntroduction();
        startQuiz();
    }

    void startQuiz() {

        var questionsIterator = questions.iterator();

        if (questionsIterator.hasNext()) {
           currentQuestion = questionsIterator.next();
        }

        while (questionsIterator.hasNext()) {
            terminal.printQuestion(currentQuestion);
            String input = terminal.readLine();
            if (QUIT_INPUT.equalsIgnoreCase(input)) {
                return;
            }
            if (answerChecker.isUserAnswerCorrect(input, currentQuestion)) {
                terminal.congratulateUserWithCorrectAnswer();
            } else {
                terminal.printRetry();
            }
            currentQuestion = questionsIterator.next();
        }
        terminal.congratulateUserWithQuizFinish();


        /*for (Question question : questions) {
            terminal.printQuestion(question);
            String userAnswer = terminal.readLine();

            if (userAnswer.equalsIgnoreCase(QUIT_INPUT)) {
                return;
            }

            while (!answerChecker.isUserAnswerCorrect(userAnswer, question)) {
                terminal.printRetry();
                userAnswer = terminal.readLine();
            }

            terminal.congratulateUserWithCorrectAnswer();
        }
        terminal.congratulateUserWithQuizFinish();*/
    }

    List<Question> loadQuestions() throws Exception {
        List<String> data = fileReader.readFile();
        return dataParser.parseQuestions(data);
    }
}
