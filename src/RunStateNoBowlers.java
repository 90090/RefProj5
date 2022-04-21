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
        lane.setRunState(state);
    }

    @Override
    public void bowlFrame() {
        System.out.println("CALLED THE RIGHT FUNCTION");
        this.frameNumber++;
        lane.setFrameNumber(this.frameNumber);

        lane.resetBowlerIterator();
        bowlIndex = 0;
        lane.setBowlIndex(this.bowlIndex);
        if (frameNumber > 9) {
            gameNumber++;
            lane.setGameNumber(this.gameNumber);
            setState(new RunStateGameFinished(lane));
        } else {
            lane.setter.reset();
            setState(new RunStateBowlersLeft(lane));

        }

        if(result == 1){
            setState(new RunStateBowlersLeft(lane));
        }else{
            setState(new RunStateGameFinished(lane));
        }
    }
}
