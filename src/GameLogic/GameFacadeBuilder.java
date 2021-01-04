package GameLogic;

/**
 * Builder for the GameFacade
 */
public class GameFacadeBuilder {
    GameFacade gameFacade;

    public GameFacadeBuilder() {
        gameFacade = this.getNewGameFacade();
    }

    /**
     * Appends the given Round to our GameFacade
     * @param round the Round to be appended
     * @return the current GameFacadeBuilder reference to allow stacking together function calls
     */
    public GameFacadeBuilder addRound(RoundInterface round) {
        this.gameFacade.addRound(round);

        return this;
    }

    /**
     *
     * @return the constructed GameFacade
     */
    public GameFacade build() {
        GameFacade game = this.gameFacade;
        this.gameFacade = this.getNewGameFacade();

        return game;
    }

    /**
     * Utility function to get a new GameFacade object
     * @return the newly fetched GameFacade
     */
    private GameFacade getNewGameFacade() {
        return new GameFacade();
    }
}
