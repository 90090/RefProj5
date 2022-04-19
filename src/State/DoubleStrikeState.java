package State;

/**
 * Author: Ethan Ligi
 */

public class DoubleStrikeState extends PinStates{

    public DoubleStrikeState(ScoreCalculationState context, BowlingFrame previousFrame, BowlingFrame doublePreviousFrame) {
        super(context,previousFrame,doublePreviousFrame);
    }

    @Override
    public void updatePrevScoreFirstRoll(int scoreToAdd) {
        previousFrame.addToFrameScore(scoreToAdd);
        doublePreviousFrame.addToFrameScore(scoreToAdd);
    }

    @Override
    public void updatePrevScoreSecondRoll(int scoreToAdd) {
        previousFrame.addToFrameScore(scoreToAdd);
    }
}
