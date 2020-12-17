package GameLogic;

public interface RoundInterface {
    Question getCurrentQuestion();
    boolean fetchNextQuestion();
    void answerQuestion(Player player, int answerIndex);
    String getRoundName();
}
