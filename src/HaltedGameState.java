public class HaltedGameState extends GameState{

    public HaltedGameState(Game game) {
        super(game);
    }
    @Override
    public boolean hasNextTurn() {
        return true;
    }

    @Override
    public void nextTurn() {
        //Nothing happens, game is halted
    }
}
