package RunState;

public class GameFinished implements RunState {
    @Override
    public void setState() {

    }

    @Override
    public void bowlFrame() {

    }
    // what to do when the game is finished

    /*
     else if (partyAssigned) {
        EndGamePrompt egp = new EndGamePrompt( ((Bowler) party.getMembers().get(0)).getNickName() + "'s Party" );
        int result = egp.getResult();
        egp.distroy();
        egp = null;

        System.out.println("result was: " + result);

        // TODO: send record of scores to control desk
        if (result == 1) {					// yes, want to play again
            resetScores();
            resetBowlerIterator();
        } else if (result == 2) {// no, dont want to play another game
            Vector printVector;
            EndGameReport egr = new EndGameReport( ((Bowler)party.getMembers().get(0)).getNickName() + "'s Party", party);
            printVector = egr.getResult();
            partyAssigned = false;
            Iterator scoreIt = party.getMembers().iterator();
            party = null;
            partyAssigned = false;

            publish(lanePublish());

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
     */
}
