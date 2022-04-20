package State;

public class SpareState extends PinStates{

    /**
     * SpareState is when the second roll clears all remaining pins
     * @param context
     * @param prevFrame
     */
    public SpareState(ScoreCalculationState context, BowlingFrame prevFrame){
        super(context, prevFrame, null);
    }

    /**
     * adds the first roll to the score of the previous frame
     * @param scoreToAdd
     */
    @Override
    public void updatePrevScoreFirstRoll(int scoreToAdd) {
        //First roll should factor into previous frame
        previousFrame.addToFrameScore(scoreToAdd);
    }

    /**
     * blank, spare does not change the previous second roll
     * @param scoreToAdd
     */
    @Override
    public void updatePrevScoreSecondRoll(int scoreToAdd) {

    }
}
