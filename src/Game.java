import java.util.Iterator;
import java.util.Objects;
import java.util.Vector;

public class Game {

    private Bowler currentThrower;
    private int ball;
    private GameState Gamestate;
    private Lane ThisLane;

    public Game(Lane lane) {
        ThisLane = lane;
    }

    private boolean began = false;

    private Iterator bowlerIterator;

    //Begins the game
    public void begin() throws Exception {
        if (began) {
            throw new Exception("Sorry, game can't begin twice");
        }

        Gamestate = new PlayingGameState(this);
        setBowlerIterator(ThisLane.party.getMembers().iterator());
    }

    public boolean hasNextTurn() {
        return Gamestate.hasNextTurn();
    }

    public void nextTurn() {
        Gamestate.nextTurn();
    }

    public void setState(GameState state) {
        this.Gamestate = state;
    }

    //State of game before it is paused, this is null if it hasn't been paused
    private GameState prePause;

    //pause game
    public void pause() {
        prePause = Gamestate;
        setState(new HaltedGameState(this));
    }

    //unpause Game
    public void unpause() throws Exception {
        if (prePause == null) {
            throw new Exception("Can not unpause a non paused game!!! fool");
        }
        setState(prePause);
        prePause = null;
    }

    //ends game
    public void end() {
        EndGamePrompt egp = new EndGamePrompt(ThisLane.party.getMembers().get(0).getNickName() + "'s Party");
        int result = egp.getResult();
        egp.distroy();

        if (result == 1) {                    // yes, want to play again
            ThisLane.resetScores();

        } else if (result == 2) {// no, dont want to play another game
            Vector printVector;
            EndGameReport egr = new EndGameReport(ThisLane.party.getMembers().get(0).getNickName() + "'s Party", ThisLane.party);
            printVector = egr.getResult();
            Iterator scoreIt = ThisLane.party.getMembers().iterator();
            ThisLane.party = null;
            ThisLane.publish(ThisLane.lanePublish());

            int myIndex = 0;
            while (scoreIt.hasNext()) {
                Bowler thisBowler = (Bowler) scoreIt.next();
                ScoreReport sr = new ScoreReport(thisBowler, ThisLane.finalScores[myIndex++], ThisLane.gameNumber);
                sr.sendEmail(thisBowler.getEmail());
                Iterator printIt = (Iterator) printVector.iterator();
                while (printIt.hasNext()) {
                    if (thisBowler.getNick() == (String) printIt.next()) {
                        System.out.println("Printing " + thisBowler.getNick());
                        sr.sendPrintout();
                    }
                }

            }
        }

    }

    public int getBall() {
        return ball;
    }

    protected void setBall(int ball) {
        this.ball = ball;
    }

    public Bowler getCurrentThrower() {
        return currentThrower;
    }

    protected void setCurrentThrower(Bowler currentThrower) {
        this.currentThrower = currentThrower;
    }

    public GameState getState() {
        return Gamestate;
    }

    protected void setBowlerIterator(Iterator bowlerIterator) {
        this.bowlerIterator = bowlerIterator;
    }

    public Iterator getBowlerIterator() {
        return bowlerIterator;
    }

    public Lane getLane() {
        return ThisLane;
    }

}
