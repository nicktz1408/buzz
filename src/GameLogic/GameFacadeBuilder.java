package GameLogic;

public class GameFacadeBuilder {
    GameFacade gameFacade;

    public GameFacadeBuilder() {
        gameFacade = this.getNewGameFacade();
    }

    public GameFacadeBuilder addRound(RoundInterface round) {
        this.gameFacade.addRound(round);

        return this;
    }

    public GameFacade build() {
        GameFacade game = this.gameFacade;
        this.gameFacade = this.getNewGameFacade();

        return game;
    }

    private GameFacade getNewGameFacade() {
        return new GameFacade();
    }
}
