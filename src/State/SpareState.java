package State;

public class SpareState extends PinStates{

    public SpareState(ScoreCalculationState context, BowlingFrame prevFrame){
        super(context, prevFrame, null);
    }
    @Override
    public void updatePrevScoreFirstRoll(int scoreToAdd) {
        //First roll should factor into previous frame
        previousFrame.addToFrameScore(scoreToAdd);
    }
    //This does nothing because it's a spare
    @Override
    public void updatePrevScoreSecondRoll(int scoreToAdd) {

    }
}
