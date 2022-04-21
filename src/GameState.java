public abstract class GameState {

    protected Game theGame;

    public GameState(Game game) {
        theGame = game;
    }

    public abstract boolean hasNextTurn();

    public abstract void nextTurn();
}
