package State;

/**
 * Author: Ethan Ligi
 */

public class DoubleStrikeState extends PinStates{

    /**
     * creates the double strike state, must have both frames to properly calculate score
     * @param context
     * @param previousFrame
     * @param doublePreviousFrame
     */
    public DoubleStrikeState(ScoreCalculationState context, BowlingFrame previousFrame, BowlingFrame doublePreviousFrame) {
        super(context,previousFrame,doublePreviousFrame);
    }

    /**
     * adds both individual frame scores to the framescore
     * @param scoreToAdd the score that needs to be added to the framescore
     */
    @Override
    public void updatePrevScoreFirstRoll(int scoreToAdd) {
        previousFrame.addToFrameScore(scoreToAdd);
        doublePreviousFrame.addToFrameScore(scoreToAdd);
    }

    /**
     * adds the previous frame score to the framescore
     * @param scoreToAdd the score that needs to be added to the framescore
     */
    @Override
    public void updatePrevScoreSecondRoll(int scoreToAdd) {
        previousFrame.addToFrameScore(scoreToAdd);
    }
}
