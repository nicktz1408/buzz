package GameLogic;

import java.util.ArrayList;

/**
 * Responsible to build the entire GameFacade structure from scratch
 */
public class GameFacadeDirector {
    /**
     * Builds the GameFacade for a single Player
     * @return
     */
    public GameFacade buildSoloGame() {
        QuestionRepository questionRepository = QuestionRepository.getInstance();
        FixedQuestionsRoundBuilder roundBuilder = new FixedQuestionsRoundBuilder();
        RoundFactory roundFactory = new RoundFactory();

        ArrayList<Question> myQuestions = questionRepository.getRandomQuestionBatch(30);
        int questionIndex = 0;

        GameFacadeBuilder gameFacadeBuilder = new GameFacadeBuilder();

        return gameFacadeBuilder
                .addRound(roundBuilder
                        .type(roundFactory.getRightQuestionRound())
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .build())
                .addRound(roundBuilder
                        .type(roundFactory.getBettingRound())
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .build())
                .addRound(roundBuilder
                        .type(roundFactory.getStopClockRound())
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .build())
                .addRound(roundBuilder
                        .type(roundFactory.getBettingRound())
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .build())
                .addRound(roundBuilder
                        .type(roundFactory.getStopClockRound())
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .build())
                .addRound(roundBuilder
                        .type(roundFactory.getRightQuestionRound())
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex))
                        .build())
                .build();
    }

    /**
     * Builds the GameFacade for a 2 Players
     * @return
     */
    public GameFacade buildTwoPlayersGame() {
        QuestionRepository questionRepository = QuestionRepository.getInstance();
        FixedQuestionsRoundBuilder roundBuilder = new FixedQuestionsRoundBuilder();
        RoundFactory roundFactory = new RoundFactory();

        ArrayList<Question> myQuestions = questionRepository.getRandomQuestionBatch(25);
        int questionIndex = 0;

        GameFacadeBuilder gameFacadeBuilder = new GameFacadeBuilder();

        return gameFacadeBuilder
                .addRound(roundBuilder
                        .type(roundFactory.getRightQuestionRound())
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .build())
                .addRound(roundBuilder
                        .type(roundFactory.getBettingRound())
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .build())
                .addRound(roundBuilder
                        .type(roundFactory.getStopClockRound())
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .build())
                .addRound(roundFactory.getThermometerRound())
                .addRound(roundBuilder
                        .type(roundFactory.getQuickAnswerRound())
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .build())
                .addRound(roundBuilder
                        .type(roundFactory.getRightQuestionRound())
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex++))
                        .addQuestion(myQuestions.get(questionIndex))
                        .build())
                .build();
    }


    /**
     *
     * @return
     */
    public GameFacade buildGame(){
        QuestionRepository questionRepository = QuestionRepository.getInstance();
        FixedQuestionsRoundBuilder roundBuilder = new FixedQuestionsRoundBuilder();
        RoundFactory roundFactory = new RoundFactory();

        ArrayList<Question> myQuestions = questionRepository.getRandomQuestionBatch(35);

        GameFacadeBuilder gameFacadeBuilder = new GameFacadeBuilder();

        return gameFacadeBuilder
                .addRound(roundBuilder
                        .type(roundFactory.getRightQuestionRound())
                        .addQuestion(myQuestions.get(0))
                        .addQuestion(myQuestions.get(1))
                        .addQuestion(myQuestions.get(2))
                        .addQuestion(myQuestions.get(3))
                        .addQuestion(myQuestions.get(4))
                        .build())
                /*.addRound(roundBuilder
                        .type(roundFactory.getStopClockRound())
                        .addQuestion(myQuestions.get(5))
                        .addQuestion(myQuestions.get(6))
                        .addQuestion(myQuestions.get(7))
                        .addQuestion(myQuestions.get(8))
                        .addQuestion(myQuestions.get(9))
                        .build())
                .addRound(roundBuilder
                        .type(roundFactory.getBettingRound())
                        .addQuestion(myQuestions.get(10))
                        .addQuestion(myQuestions.get(11))
                        .addQuestion(myQuestions.get(12))
                        .addQuestion(myQuestions.get(13))
                        .addQuestion(myQuestions.get(14))
                        .build())*/
                .addRound(roundFactory.getThermometerRound())
                /*.addRound(roundBuilder
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
                        .build())*/
                .addRound(roundBuilder
                        .type(roundFactory.getQuickAnswerRound())
                        .addQuestion(myQuestions.get(30))
                        .addQuestion(myQuestions.get(31))
                        .addQuestion(myQuestions.get(32))
                        .addQuestion(myQuestions.get(33))
                        .addQuestion(myQuestions.get(34))
                        .build())
                .build();
    }
}
