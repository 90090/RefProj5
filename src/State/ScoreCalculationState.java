package State;

import java.util.ArrayList;

/**
 * Author: Ethan Ligi
 */

public class ScoreCalculationState {

    private PinStates state;
    private ArrayList<BowlingFrame> frames;

    public ScoreCalculationState(ArrayList<BowlingFrame> frames) {
        state = new NormalState(this);
        this.frames = frames;
    }

    public void setState(PinStates newState) {
        state = newState;
    }

    //Calculates total of all active frames
    public int calculateTotal(){
        setState(new NormalState(this));
        int total = 0;
        //Go through all frames passing each frame into the current state.
        for(BowlingFrame frame : frames) {
            state.evaluateFrame(frame);
        }
        //Iterate through all frames and add the scores
        for(BowlingFrame frame : frames) {
            total += frame.getFrameScore();
        }

        return total;
    }
}
