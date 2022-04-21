import javax.swing.*;
import java.util.ArrayList;

public class PartyIterator implements Iterator{

    //ArrayList of bowlers being processed
    private ArrayList<Bowler> bowlers;
    //index of bowler being processed
    private int index = 0;

    public PartyIterator(Party party) {
        this.bowlers = new ArrayList<>();
        bowlers.addAll(party.getMembers());
    }

    @Override
    public boolean hasNext() {
        return this.index < this.bowlers.size();
    }

    @Override
    public Object next() {
        return bowlers.get(index++);
    }

    //updates gui labels given with information from bowlers in iterator
    @Override
    public void process(LaneEvent event, JLabel[][] ballLabel, JLabel[][] scoreLabel) {
        int[][] scores = event.getCumulScore();
        Bowler currentBowler;
        int b = 0;

        //run through each bowler's frames and update ballLabel with score, b is number of current bowler
        while (this.hasNext()) {
            currentBowler = (Bowler) this.next();
            //for all frames that have happened, calculate cumulative score and update gui label, f is frame number
            for(int i = 0; i <= event.getFrameNum() - 1; i++) {
                if(scores[b][i] != 0) { // if score in frame is not 0
                    scoreLabel[b][i].setText(String.valueOf(Integer.valueOf(scores[b][i])));
                }
            }
            //for all frames, 1-20, get the score for that frame and update gui label
            for (int f = 0; f < 21; f++) {
                int curScore = ((int[]) (event.getScore()).get(bowlers.get(b)))[f];
                if (curScore != -1) {
                    if (curScore == 10 && (f % 2 == 0 || f == 19)) {
                        //if strike and frame is first of turn or 19th frame
                        ballLabel[b][f].setText("X");
                    } else if (f > 0 && curScore + ((int[]) (event.getScore()).get(bowlers.get(b)))[f - 1] == 10 && f % 2 == 1) {
                        //if two frames score add up to 10, meaning spare
                        ballLabel[b][f].setText("/");
                    }
                    else if (curScore == -2) {
                        //should not reach
                        System.out.println("Set to F");
                        ballLabel[b][f].setText("F");
                    } else {
                        //update gui label with score, no special state
                        ballLabel[b][f].setText((new Integer(curScore)).toString());
                    }
                }
            }
            b++;

        }

    }
}
