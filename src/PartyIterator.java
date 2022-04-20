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

    @Override
    public void process(LaneEvent event, JLabel[][] ballLabel, JLabel[][] scoreLabel) {
        int[][] scores = event.getCumulScore();
        Bowler currentBowler;
        int b = 0;

        //run through each bowler's frames and update ballLabel with score, b is number of current bowler
    }
}
