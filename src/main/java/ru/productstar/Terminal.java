package ru.productstar;

import ru.productstar.dto.Question;

import java.util.Scanner;

public class Terminal {
    private Scanner scanner = new Scanner(System.in);
    public String readLine() {
        return scanner.nextLine();
    }

    public void printIntroduction() {
    }

    public void printQuestion(Question question) {
    }

    public void congratulateUserWithCorrectAnswer() {

    }

    public void congratulateUserWithQuizFinish() {
    }

    public void printRetry() {

    }

    private void writeLine(String output) {
        System.out.println(output);
    }
}
