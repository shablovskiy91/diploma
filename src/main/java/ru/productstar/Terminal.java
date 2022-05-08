package ru.productstar;

import ru.productstar.dto.Question;

import java.util.Scanner;

public class Terminal {
    private Scanner scanner = new Scanner(System.in);
    public String readLine() {
        return scanner.nextLine();
    }

    public void printIntroduction() {
        writeLine("Добро пожаловать!");
    }

    public void printQuestion(Question question) {
        writeLine(question.toString());
    }

    public void congratulateUserWithCorrectAnswer() {
        writeLine("Поздравляем");
    }

    public void congratulateUserWithQuizFinish() {
        writeLine("Тест завершен");
    }

    public void printRetry() {
        writeLine("попробуйте еще раз");
    }

    private void writeLine(String output) {
        System.out.println(output);
    }
}
