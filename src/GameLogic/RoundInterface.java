package GameLogic;

public interface RoundInterface {
    Question getCurrentQuestion();
    boolean fetchNextQuestion();
    void answerQuestion(GamePlayer player, int answerIndex);
    String getRoundName();
}
