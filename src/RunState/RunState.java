package RunState;

public interface RunState {
    void setState(); // update what should happen next based on the conditions
    void bowlFrame(); // do the actions
}
