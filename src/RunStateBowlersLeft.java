import java.util.Iterator;

public class RunStateBowlersLeft extends RunState {
    Iterator bowlerIterator;
    Boolean canThrowAgain;
    Boolean tenthFrameStrike;
    Pinsetter setter;
    int bowlIndex;
    int frameNumber;

    public RunStateBowlersLeft(Lane lane) {
        super(lane);
        bowlerIterator = lane.getBowlerIterator();
        canThrowAgain = true;
        tenthFrameStrike = false;
        setter = lane.getPinsetter();
        frameNumber = lane.getFrameNumber();
    }
    // how to perform when there are more bowlers to bowl in the current frame

    /**
     * checks the bowlerIterator to see what should happen next
     */
    public void checkNext() {}

    @Override
    public void setState() {
        state = new RunStateLastFrame(lane);
    }

    @Override
    public void setState(RunState state) {
        //squash
    }

    @Override
    public void bowlFrame() {
        Bowler currentThrower = (Bowler) this.bowlerIterator.next();
        int ball = 0;
        while (canThrowAgain) {
            setter.ballThrown();		// simulate the thrower's ball hiting
            ball++;
        }
        if(frameNumber == 9){
            setState();
        }
        setter.reset();
        bowlIndex++;
    }
}
