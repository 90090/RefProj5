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

    public RunStateBowlersLeft(Lane lane) {

        super(lane);
        System.out.println("NEW BOWLERS LEFT STATE HAS BEEN CREATED");
        bowlerIterator = lane.getBowlerIterator();
        bowlIndex = lane.getBowlIndex();
        setter = lane.getPinsetter();
        frameNumber = lane.getFrameNumber();

    }
    // how to perform when there are more bowlers to bowl in the current frame

    @Override
    public void setState() {
        state = new RunStateLastFrame(lane);
    }

    @Override
    public void setState(RunState newState) {
        state = newState;
    }

    @Override
    public void bowlFrame() {
        while (true) {
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
                while (canThrowAgain) {
                    lane.setter.ballThrown();        // simulate the thrower's ball hiting
                    ball++;
                    lane.setBall(this.ball);
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
}
