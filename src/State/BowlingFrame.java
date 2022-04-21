package State;

import java.util.Optional;

/**
 * Author: Ethan Ligi
 */

public class BowlingFrame {

    public enum RollEnum {
        First,
        Second,
        Third
    }

    private Integer roll1;
    private Integer roll2;
    private Integer roll3;
    private boolean isLastFrame;
    private Integer frameScore;

    public BowlingFrame(boolean isLastFrame){
        frameScore = 0;
        roll1 = null;
        roll2 = null;
        this.isLastFrame = isLastFrame;
    }

    /**
     * returns the first roll
     * @return
     * @throws RollException
     */
    public int getRoll1() throws RollException {
        if(roll2 != null){
            return roll1;
        } else {
            throw new RollException("Error: Roll not found.");
        }
    }

    /**
     * returns the second roll
     * @return
     * @throws RollException
     */
    public int getRoll2() throws RollException {
        if(roll1 != null){
            return roll2;
        } else {
            throw new RollException("Error: Roll not found.");
        }
    }

    /**
     * returns the third roll
     * @return
     * @throws RollException
     */
    public int getRoll3() throws RollException {
        if(roll3 != null){
            return roll3;
        } else {
            throw new RollException("Error: Roll not found.");
        }
    }

    /**
     * adds the the parameter roll to the frame that needs it
     * @throws FrameException
     */
    public void addRoll(int newRoll) throws FrameException{
        if(roll1 == null){
            roll1 = newRoll;
        } else if (roll2 == null) {
            roll2 = newRoll;
        } else if (isLastFrame) {
            roll3 = newRoll;
        } else {
            throw new FrameException("Error: Frame is full");
        }
    }

    /**
     * adds the score to the framescore
     * @param score
     */
    public void addToFrameScore(int score) { frameScore += score; }

    /**
     * returns the framescore
     * @return
     */
    public int getFrameScore() { return frameScore; }

    /**
     * returns if the current frame is the last frame
     * @return true if not the last frame, false if it is
     */
    public boolean isNotLastFrame(){ return !isLastFrame; }

    /**
     * declares a new FrameException class
     */
    public class FrameException extends Exception {
        public FrameException(String message) {
            super(message);
        }
    }

    /**
     * Declares a new RollException class
     */
    public class RollException extends Exception {
        public RollException(String message) {
            super(message);
        }
    }

    /**
     * returns a toString form of the rolls and total score
     * @return
     */
    public String toString(){
        return String.format("roll1: %d, + roll2: %d, +roll3: %d, framescore=%d", roll1, roll2, roll3, frameScore);
    }
}
