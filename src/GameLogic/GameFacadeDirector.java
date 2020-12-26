package GameLogic;

import java.io.IOException;
import java.util.ArrayList;

public class GameFacadeDirector {
    public GameFacadeDirector() {

    }

    public GameFacade buildGame() throws IOException {
        QuestionRepository questionRepository = new QuestionRepository();
        QuestionBuilder questionBuilder = new QuestionBuilder();
        FixedQuestionsRoundBuilder roundBuilder = new FixedQuestionsRoundBuilder();
        RoundFactory roundFactory = new RoundFactory();

        ArrayList<String []> questions = questionRepository.getRandomQuestionBatch(30);
        ArrayList<Question> myQuestions = new ArrayList<Question>();

        for(int i = 0; i < questions.size(); i++) {
            String []currQuestion = questions.get(i);

            myQuestions.add(
                    questionBuilder
                            .setCategory(currQuestion[0])
                            .setQuestionText(currQuestion[1])
                            .addAnswer(currQuestion[2])
                            .addAnswer(currQuestion[3])
                            .addAnswer(currQuestion[4])
                            .addAnswer(currQuestion[5])
                            .setRightAnswerIndex(Integer.parseInt(currQuestion[6]))
                            .build()
            );
        }

        GameFacadeBuilder gameFacadeBuilder = new GameFacadeBuilder();

        return gameFacadeBuilder
                .addRound(roundBuilder
                        .type(roundFactory.getRightQuestionAnswerRound())
                        .addQuestion(myQuestions.get(0))
                        .addQuestion(myQuestions.get(1))
                        .addQuestion(myQuestions.get(2))
                        .addQuestion(myQuestions.get(3))
                        .addQuestion(myQuestions.get(4))
                        .build())
                .addRound(roundBuilder
                        .type(roundFactory.getRightQuestionAnswerRound())
                        .addQuestion(myQuestions.get(5))
                        .addQuestion(myQuestions.get(6))
                        .addQuestion(myQuestions.get(7))
                        .addQuestion(myQuestions.get(8))
                        .addQuestion(myQuestions.get(9))
                        .build())
                .addRound(roundBuilder
                        .type(roundFactory.getRightQuestionAnswerRound())
                        .addQuestion(myQuestions.get(10))
                        .addQuestion(myQuestions.get(11))
                        .addQuestion(myQuestions.get(12))
                        .addQuestion(myQuestions.get(13))
                        .addQuestion(myQuestions.get(14))
                        .build())
                .addRound(roundBuilder
                        .type(roundFactory.getRightQuestionAnswerRound())
                        .addQuestion(myQuestions.get(15))
                        .addQuestion(myQuestions.get(16))
                        .addQuestion(myQuestions.get(17))
                        .addQuestion(myQuestions.get(18))
                        .addQuestion(myQuestions.get(19))
                        .build())
                .addRound(roundBuilder
                        .type(roundFactory.getRightQuestionAnswerRound())
                        .addQuestion(myQuestions.get(20))
                        .addQuestion(myQuestions.get(21))
                        .addQuestion(myQuestions.get(22))
                        .addQuestion(myQuestions.get(23))
                        .addQuestion(myQuestions.get(24))
                        .build())
                .addRound(roundBuilder
                        .type(roundFactory.getRightQuestionAnswerRound())
                        .addQuestion(myQuestions.get(25))
                        .addQuestion(myQuestions.get(26))
                        .addQuestion(myQuestions.get(27))
                        .addQuestion(myQuestions.get(28))
                        .addQuestion(myQuestions.get(29))
                        .build())
                .build();
    }
}
