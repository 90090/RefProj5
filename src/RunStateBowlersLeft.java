public class RunStateBowlersLeft extends RunState {
    public RunStateBowlersLeft(Lane lane) {
        super(lane);
    }
    // how to perform when there are more bowlers to bowl in the current frame

    /**
     * checks the bowlerIterator to see what should happen next
     */
    public void checkNext() {}

    @Override
    public void setState() {

    }

    @Override
    public void bowlFrame() {

    }

    /*
    if (bowlerIterator.hasNext()) { // while there are bowlers to go
        currentThrower = (Bowler)bowlerIterator.next();
        canThrowAgain = true;
        tenthFrameStrike = false;
        ball = 0;
        while (canThrowAgain) {
            setter.ballThrown();		// simulate the thrower's ball hiting
            ball++;
        }

        if (frameNumber == 9){
            finalScores[bowlIndex][gameNumber] = cumulScores[bowlIndex][9];
            try{
            Date date = new Date();
            String dateString = "" + date.getHours() + ":" + date.getMinutes() + " " + date.getMonth() + "/" + date.getDay() + "/" + (date.getYear() + 1900);
            ScoreHistoryFile.addScore(currentThrower.getNick(), dateString, new Integer(cumulScores[bowlIndex][9]).toString());
            } catch (Exception e) {System.err.println("Exception in addScore. "+ e );}
        }
        setter.reset();
        bowlIndex++;
     */
}
