package GameLogic;

import java.util.ArrayList;

/**
 * Responsible to build the entire GameFacade structure from scratch
 */
public class GameFacadeDirector {
    /**
     * Builds the GameFacade for a single Player
     * @return the built GameFacade for 1 player
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
     * @return the built GameFacade for 2 players
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
}