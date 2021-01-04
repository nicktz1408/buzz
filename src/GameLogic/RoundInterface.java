package GameLogic;

/**
 * Interface for the Rounds (is being used by the various round types/modes)
 */
public interface RoundInterface {
    /**
     * Shows the Question associated with the given Player
     * @param player the Player (in FixedRounds, it is not needed it's here just to be in
     * accordance with the Liskov's Principle)
     * @return the Question associated with the given Player
     */
    Question getCurrentQuestion(GamePlayer player);

    /**
     * Instructs the Round class to fetch the next Question of a given Player
     * @param player the Player (in FixedRounds, it is not needed it's here just to be in
     * accordance with the Liskov's Principle)
     * @return true if the new Question has been fetched, false if this round has been finished
     * (either by exhausting all Questions or if a winner has been determined)
     */
    boolean fetchNextQuestion(GamePlayer player);

    /**
     * Given a Player, answers the current Question (associated with them) and it adjusts the Player's
     * score accordingly
     * @param player the Player
     * @param answerIndex the index (0 to 3) of the answer chosen by the Player
     * @param additionalRequestData potential additional data that might be needed by the various round types
     */
    void answerQuestion(GamePlayer player, int answerIndex, Object... additionalRequestData);

    /**
     *
     * @return the round id of the subtype
     */
    int getRoundName();
}
