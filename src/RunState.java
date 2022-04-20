

public abstract class RunState {
    protected Lane lane;
    protected RunState state;

    public RunState(Lane lane) {
        this.lane = lane;
    }

    public abstract void setState(); // update what should happen next based on the conditions
    public abstract void setState(RunState state); // update state, but with a given RunState for simplicity
    public abstract void bowlFrame(); // do the actions
}
