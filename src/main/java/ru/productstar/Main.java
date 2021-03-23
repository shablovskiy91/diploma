package ru.productstar;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        QuizEngine quizEngine = new QuizEngine(new FileReader("quiz.txt"), new DataParser(), new AnswerChecker(), new Terminal());
        quizEngine.run();
    }
}
