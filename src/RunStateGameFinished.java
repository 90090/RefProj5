import java.util.Iterator;
import java.util.Vector;

public class RunStateGameFinished extends RunState {
    Vector printVector;
    EndGameReport egr;
    boolean partyAssigned;
    Iterator scoreIt;
    Party party;
    int gameNumber;
    int[][] finalScores;
    int result;

    public RunStateGameFinished(Lane lane) {
        super(lane);
        finalScores = lane.getFinalScores();
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
        EndGamePrompt egp = new EndGamePrompt( ((Bowler) party.getMembers().get(0)).getNickName() + "'s Party" );
        int result = egp.getResult();
        egp.distroy();
        egp = null;
        System.out.println("result was: " + result);

        if(result == 1){
            lane.resetBowlerIterator();
            lane.resetScores();
            setState(new RunStateBowlersLeft(lane));
        }else{
            Vector printVector;
            EndGameReport egr = new EndGameReport( ((Bowler)party.getMembers().get(0)).getNickName() + "'s Party", party);
            printVector = egr.getResult();
            partyAssigned = false;
            Iterator scoreIt = party.getMembers().iterator();
            party = null;
            partyAssigned = false;

            lane.publish(lane.lanePublish());
            int myIndex = 0;
            while (scoreIt.hasNext()){
                Bowler thisBowler = (Bowler)scoreIt.next();
                ScoreReport sr = new ScoreReport( thisBowler, finalScores[myIndex++], gameNumber );
                sr.sendEmail(thisBowler.getEmail());
                Iterator printIt = printVector.iterator();
                while (printIt.hasNext()){
                    if (thisBowler.getNick() == (String)printIt.next()){
                        System.out.println("Printing " + thisBowler.getNick());
                        sr.sendPrintout();
                    }
                }
            }
        }
    }
}
