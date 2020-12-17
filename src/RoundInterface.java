public interface RoundInterface {
    Question getCurrentQuestion();
    boolean fetchNextQuestion();
    void answerQuestion(Player, int);
}
