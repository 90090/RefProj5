

public class RunStateLastFrame extends RunState {
    public RunStateLastFrame(Lane lane) {
        super(lane);
    }
    // this state will only be used when there are bowlers left

    @Override
    public void setState() {

    }

    @Override
    public void bowlFrame() {
        /*
        if (frameNumber == 9){
            finalScores[bowlIndex][gameNumber] = cumulScores[bowlIndex][9];
            try{
            Date date = new Date();
            String dateString = "" + date.getHours() + ":" + date.getMinutes() + " " + date.getMonth() + "/" + date.getDay() + "/" + (date.getYear() + 1900);
            ScoreHistoryFile.addScore(currentThrower.getNick(), dateString, new Integer(cumulScores[bowlIndex][9]).toString());
            } catch (Exception e) {System.err.println("Exception in addScore. "+ e );}
        }
         */
    }

    /*
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
