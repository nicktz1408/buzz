package GameLogic;

public interface RoundInterface {
    Question getCurrentQuestion(GamePlayer player);
    boolean fetchNextQuestion(GamePlayer player);
    void answerQuestion(GamePlayer player, int answerIndex, Object... additionalRequestData);
    String getRoundName();
}
