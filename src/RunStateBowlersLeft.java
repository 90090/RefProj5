import java.util.Date;
import java.util.Iterator;

public class RunStateBowlersLeft extends RunState {
    Iterator bowlerIterator;
    Boolean canThrowAgain;
    Boolean tenthFrameStrike;
    Pinsetter setter;
    Bowler currentThrower;
    int bowlIndex;
    int frameNumber;
    int ball;
    int[][] finalScores;
    int gameNumber;
    int[][] cumulScores;

    public RunStateBowlersLeft(Lane lane) {

        super(lane);
        System.out.println("NEW BOWLERS LEFT STATE HAS BEEN CREATED");
        bowlerIterator = lane.getBowlerIterator();
        bowlIndex = lane.getBowlIndex();
        setter = lane.getPinsetter();
        frameNumber = lane.getFrameNumber();
        finalScores = lane.getFinalScores();
        gameNumber = lane.getGameNumber();
        cumulScores = lane.getCumulScores();

    }
    // how to perform when there are more bowlers to bowl in the current frame

    @Override
    public void setState() {
        state = new RunStateLastFrame(lane);
        lane.setRunState(state);
    }

    @Override
    public void setState(RunState newState) {
        state = newState;
        lane.setRunState(state);
    }

    @Override
    public void bowlFrame() {
            if (!bowlerIterator.hasNext()) {
                setState(new RunStateNoBowlers(lane));
            } else {
                this.currentThrower = (Bowler) this.bowlerIterator.next();

                lane.setCurrentThrower(this.currentThrower);
                ball = 0;
                lane.setBall(this.ball);
                canThrowAgain = true;
                lane.setCanThrowAgain(this.canThrowAgain);
                tenthFrameStrike = false;
                lane.setTenthFrameStrike(this.tenthFrameStrike);
                while (ball < 2) {
                    lane.setter.ballThrown();        // simulate the thrower's ball hiting
                    ball++;
                }
                if (frameNumber == 9) {
                    setState();
                }
                lane.setter.reset();
                bowlIndex++;
                lane.setBowlIndex(this.bowlIndex);
            }



    }
}
