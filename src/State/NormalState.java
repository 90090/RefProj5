package State;

/**
 * Author: Ethan Ligi
 */

public class NormalState extends PinStates{

    /**
     * Normal state is handled through other classes
     * @param context
     */
    public NormalState(ScoreCalculationState context) {
        super(context, null, null);
    }
    //Nothing happens in this state It just has to be here for the pattern but since
    //it's the normal state so all the code for it is in the other classes
    @Override
    public void updatePrevScoreFirstRoll(int scoreToAdd) {

    }

    @Override
    public void updatePrevScoreSecondRoll(int scoreToAdd) {

    }
}
