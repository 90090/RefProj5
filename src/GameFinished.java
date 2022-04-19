/**
 * This is the concrete observer class from the Observer pattern
 */
public class GameFinished implements GameObserver {

    GameState currentState;

    @Override
    public void updateGameStatus() {
        this.currentState = GameState.COMPLETED;
    }
}
