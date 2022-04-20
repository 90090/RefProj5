package RunState;

public class NoBowlersLeftState implements RunState {
    @Override
    public void setState() {

    }

    @Override
    public void bowlFrame() {

    }
    // what to do when there are no more bowlers left to bowl the current frame

    /*
     else {
        frameNumber++;
        resetBowlerIterator();
        bowlIndex = 0;
        if (frameNumber > 9) {
            gameFinished = true;
            gameNumber++;
        }
     }
     */
}
