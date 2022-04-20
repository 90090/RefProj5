package State;

/**
 * Author: Ethan Ligi
 */

public class StrikeState extends PinStates{

    /**
     * StrikeState adds the score to both previous rolls
     * @param context
     * @param prevFrame
     */
    public StrikeState(ScoreCalculationState context, BowlingFrame prevFrame) {
        super(context, prevFrame, null);
    }

    /**
     * Adds the score to the first roll of the previous frame
     * @param scoreToAdd
     */
    @Override
    public void updatePrevScoreFirstRoll(int scoreToAdd) {
        previousFrame.addToFrameScore(scoreToAdd);
    }

    /**
     * Adds the score to the second roll of the previous frame
     * @param scoreToAdd
     */
    @Override
    public void updatePrevScoreSecondRoll(int scoreToAdd) {
        previousFrame.addToFrameScore(scoreToAdd);
    }
}
