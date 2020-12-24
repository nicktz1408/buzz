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

        ArrayList<String []> questions = questionRepository.getRandomQuestionBatch(14);
        ArrayList<Question> myQuestions = new ArrayList<Question>();

        for(int i = 0; i < questions.size(); i++) {
            String []currQuestion = questions.get(i);

            myQuestions.add(
                    questionBuilder
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
                        .addQuestion(myQuestions.get(0))
                        .addQuestion(myQuestions.get(1))
                        .addQuestion(myQuestions.get(2))
                        .addQuestion(myQuestions.get(3))
                        .addQuestion(myQuestions.get(4))
                        .build())
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
                        .addQuestion(myQuestions.get(0))
                        .addQuestion(myQuestions.get(1))
                        .addQuestion(myQuestions.get(2))
                        .addQuestion(myQuestions.get(3))
                        .addQuestion(myQuestions.get(4))
                        .build())
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
                        .addQuestion(myQuestions.get(0))
                        .addQuestion(myQuestions.get(1))
                        .addQuestion(myQuestions.get(2))
                        .addQuestion(myQuestions.get(3))
                        .addQuestion(myQuestions.get(4))
                        .build())
                .build();
    }
}
