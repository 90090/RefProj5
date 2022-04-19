/**
 * This is the subject class from the Observer pattern
 */
public interface LaneRunner {
    void register(GameObserver observer);
    void deregister(GameObserver observer);
    void notify(Lane lane);
}
