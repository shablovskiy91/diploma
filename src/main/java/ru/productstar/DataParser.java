package ru.productstar;

import ru.productstar.dto.Question;
import ru.productstar.dto.QuestionType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataParser {

    private String questionMarker = "type:";

    public List<Question> parseQuestions(List<String> data) throws Exception {

        var result = new ArrayList<Question>();
        var allLines = data.iterator();
        var dataToParseOneQuestion = new ArrayList<String>();
        var currentLine = "";
        int stringReadLimit = 0;

        while (allLines.hasNext()) {
            currentLine = allLines.next();
            if (currentLine.contains(questionMarker)) {
                if (currentLine.contains(QuestionType.QUESTION_WITH_ANSWERS.toString())) {
                    stringReadLimit = 4;
                    for (int i = 1; i <= stringReadLimit; i++) {
                        dataToParseOneQuestion.add(currentLine);
                        if (allLines.hasNext() && i < stringReadLimit) {
                            currentLine = allLines.next().trim();
                        }
                    }
                }
                if (currentLine.contains(QuestionType.OPEN_QUESTION.toString())) {
                    stringReadLimit = 3;
                    for (int i = 1; i <= stringReadLimit; i++) {
                        dataToParseOneQuestion.add(currentLine);
                        if (allLines.hasNext() && i < stringReadLimit) {
                            currentLine = allLines.next().trim();
                        }
                    }
                }
                result.add(parseOneQuestion(dataToParseOneQuestion));
                dataToParseOneQuestion.clear();
            } else {
                throw new Exception("Failed to read question type marker in this line: " + currentLine);
            }
        }
        return result;
    }

    public Question parseOneQuestion(List<String> data) {
        var lines = data.iterator();
        String questionType = lines.next();
        String text = lines.next();
        if (questionType.contains(questionMarker + QuestionType.QUESTION_WITH_ANSWERS.toString())) {
            var answersLine = lines.next();
            var answerOptions = List.of(answersLine.split(";", 3));
            var correctAnswer = lines.next();
            return new Question(QuestionType.QUESTION_WITH_ANSWERS, text, correctAnswer, answerOptions);
        }
        if (questionType.contains(questionMarker + QuestionType.OPEN_QUESTION.toString())) {
            var correctAnswer = lines.next();
            return new Question(QuestionType.OPEN_QUESTION, text, correctAnswer, Collections.emptyList());
        }
        else {
            return null;
        }
    }
}
