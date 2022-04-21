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
        if (!bowlerIterator.hasNext()) {
            setState(new RunStateNoBowlers(lane));
        } else {
            this.currentThrower = (Bowler)this.bowlerIterator.next();
            lane.setCurrentThrower(this.currentThrower);
            ball = 0;
            canThrowAgain = true;
            tenthFrameStrike = false;
            while (canThrowAgain) {
                setter.ballThrown();        // simulate the thrower's ball hiting
                ball++;
            }
            if (frameNumber == 9) {
                setState();
            }
            setter.reset();
            bowlIndex++;
        }

    }
}
