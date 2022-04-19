package State;
import static State.BowlingFrame.RollEnum.First;
import static State.BowlingFrame.RollEnum.Second;
import static State.BowlingFrame.RollEnum.Third;

/**
 * Author: Ethan Ligi
 */

public abstract class PinStates {

    public static final int MAX_FRAME = 10;

    private ScoreCalculationState context;
    BowlingFrame previousFrame;
    BowlingFrame doublePreviousFrame;

    public PinStates(ScoreCalculationState context, BowlingFrame previousFrame, BowlingFrame doublePreviousFrame) {
        this.context = context;
        this.previousFrame = previousFrame;
        this.doublePreviousFrame = doublePreviousFrame;
    }

    /**
     * Based on the current state evaluateFrame calculates the value of the current frame
     * and updates the previous frames
     * @param frame
     */
    public void evaluateFrame(BowlingFrame frame) {

        //Get rolls of current frame
        int roll1 = unwrapRoll(frame,First);
        int roll2 = unwrapRoll(frame,Second);
        int roll3 = unwrapRoll(frame,Third);

        //update the previous scores based on current state
        updatePrevScoreFirstRoll(roll1);
        updatePrevScoreSecondRoll(roll2);

        //if there was a strike
        if(roll1 == MAX_FRAME){

            //if we are in the strike state
            if(this instanceof DoubleStrikeState || this instanceof StrikeState) {
                //enter Double Strike State
                context.setState(new DoubleStrikeState(context, frame, previousFrame));
            } else {
                //Enter the strike state if we aren't in it already
                context.setState(new StrikeState(context, frame));
            }
        }

        //if there was a spare
        else if ( roll1 + roll2 == MAX_FRAME) {
            //Enter SpareState
            context.setState(new SpareState(context,frame));
        } else {
            //Frame wasn't a spare
            context.setState(new NormalState(context));
        }

        //Set frames score
        int total = roll1 + roll2 + roll3;
        frame.addToFrameScore(total);

    }

    public abstract void updatePrevScoreFirstRoll(int scoreToAdd);

    public abstract void updatePrevScoreSecondRoll(int scoreToAdd);

    public int unwrapRoll(BowlingFrame frame, BowlingFrame.RollEnum roll) {
        try {
            switch (roll) {
                case First:
                    return frame.getRoll1();
                case Second:
                    return frame.getRoll2();
                case Third:
                    return frame.getRoll3();
                default:
                    return 0;
            }
        } catch (BowlingFrame.RollException e) {
            return 0;
        }
    }
}
