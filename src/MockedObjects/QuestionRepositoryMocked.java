package MockedObjects;

import GameLogic.Question;
import GameLogic.QuestionRepository;

public class QuestionRepositoryMocked extends QuestionRepository {
    public QuestionRepositoryMocked() {

    }

    @Override
    public Question getSingleRandomQuestion() {
        return Question.builder().addAnswer("random answer").setRightAnswerIndex(0).build();
    }
}
