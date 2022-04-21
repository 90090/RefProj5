public class RunStateNoBowlers extends RunState {
    int frameNumber;
    int bowlIndex;
    boolean gameFinished;
    int gameNumber;
    int result;

    public RunStateNoBowlers(Lane lane) {
        super(lane);
        frameNumber = lane.getFrameNumber();
        bowlIndex = lane.getBowlIndex();
        gameFinished = lane.isGameFinished();
        gameNumber = lane.getGameNumber();
        result = lane.getResult();
    }

    @Override
    public void setState() {
        //squash
    }

    @Override
    public void setState(RunState newState) {
        state = newState;
    }

    @Override
    public void bowlFrame() {
        frameNumber++;
        lane.resetBowlerIterator();
        bowlIndex = 0;
        if (frameNumber > 9) {
            gameFinished = true;
            gameNumber++;
        }

        if(result == 1){
            setState(new RunStateBowlersLeft(lane));
        }else{
            setState(new RunStateGameFinished(lane));
        }
    }
}
