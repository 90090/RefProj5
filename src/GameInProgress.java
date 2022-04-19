public class GameInProgress implements GameObserver {

    GameState currentState;

    @Override
    public void updateGameStatus() {
        this.currentState = GameState.IN_PROGRESS;
    }
}
