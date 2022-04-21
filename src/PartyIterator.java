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

        }

    }
}
