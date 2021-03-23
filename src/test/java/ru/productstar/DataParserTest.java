package ru.productstar;

import org.junit.jupiter.api.Test;
import ru.productstar.dto.Question;
import ru.productstar.dto.QuestionType;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

class DataParserTest {
    private final DataParser dataParser = new DataParser();

    @Test
    public void correctlyParsesEmptyData() {
        assertThat(dataParser.parseQuestions(emptyList())).isEmpty();
    }

    @Test
    public void correctlyParsesQuestionWithAnswer() throws IOException, URISyntaxException {
        FileReader fileReader = new FileReader("questionWithAnswers.txt");
        List<Question> questions = dataParser.parseQuestions(fileReader.readFile());

        assertThat(questions)
                .hasSize(1)
                .containsExactly(
                    new Question(
                            QuestionType.QUESTION_WITH_ANSWERS,
                            "Это тестовый вопрос с вариантами ответа",
                            "2",
                            asList("Ответ 1", "Ответ 2", "Ответ 3")
                    )
                );
    }

    @Test
    public void correctlyParsesOpenQuestion() throws IOException, URISyntaxException {
        FileReader fileReader = new FileReader("openQuestion.txt");
        List<Question> questions = dataParser.parseQuestions(fileReader.readFile());

        assertThat(questions)
                .hasSize(1)
                .containsExactly(
                        new Question(
                                QuestionType.OPEN_QUESTION,
                                "Это тестовый открытый вопрос",
                                "int",
                                emptyList()
                        )
                );
    }
}