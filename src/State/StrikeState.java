package State;

/**
 * Author: Ethan Ligi
 */

public class StrikeState extends PinStates{

    public StrikeState(ScoreCalculationState context, BowlingFrame prevFrame) {
        super(context, prevFrame, null);
    }

    @Override
    public void updatePrevScoreFirstRoll(int scoreToAdd) {
        previousFrame.addToFrameScore(scoreToAdd);
    }

    @Override
    public void updatePrevScoreSecondRoll(int scoreToAdd) {
        previousFrame.addToFrameScore(scoreToAdd);
    }
}
