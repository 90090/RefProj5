/**
 * Author: Ethan Ligi
 */

import javax.swing.*;
public interface Iterator {
    //checks if the iterator has more objects in its collection
    boolean hasNext();

    //Returns next object in the collection
    Object next();

    /**
     * updates gui labels given with information from bowlers in iterator
     * @param event the last LaneEvent that happened, gives info about bowlers and frame numbers
     * @param ballLabel the gui label that shows score for one frame
     * @param scoreLabel gui label that shows cumulative score
     */
    void process(LaneEvent event, JLabel[][] ballLabel, JLabel[][] scoreLabel);
}
