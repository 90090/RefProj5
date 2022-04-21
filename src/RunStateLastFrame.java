import java.util.Iterator;
import java.util.Date;

public class RunStateLastFrame extends RunState {
    Iterator bowlerIterator;
    Pinsetter setter;
    Bowler currentThrower;
    int bowlIndex;
    int frameNumber;
    int[][] finalScores;
    int gameNumber;
    int[][] cumulScores;

    public RunStateLastFrame(Lane lane) {
        super(lane);
        bowlerIterator = lane.getBowlerIterator();
        setter = lane.getPinsetter();
        frameNumber = lane.getFrameNumber();
        currentThrower = lane.getCurrentThrower();
        bowlIndex = lane.getBowlIndex();
        frameNumber = lane.getFrameNumber();
        finalScores = lane.getFinalScores();
        gameNumber = lane.getGameNumber();
        cumulScores = lane.getCumulScores();
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
        if (frameNumber == 9){
            finalScores[bowlIndex][gameNumber] = cumulScores[bowlIndex][9];
            lane.setFinalScores(this.finalScores);
            try{
                Date date = new Date();
                String dateString = "" + date.getHours() + ":" + date.getMinutes() + " " + date.getMonth() + "/" + date.getDay() + "/" + (date.getYear() + 1900);
                ScoreHistoryFile.addScore(currentThrower.getNick(), dateString, String.valueOf(cumulScores[bowlIndex][9]));
            } catch (Exception e) {System.err.println("Exception in addScore. "+ e );}
        }
        lane.setter.reset();
        bowlIndex++;
        lane.setBowlIndex(this.bowlIndex);

        if(bowlerIterator.hasNext()){
            setState(new RunStateBowlersLeft(lane));
        }else{
            setState(new RunStateNoBowlers(lane));
        }
    }
}
