

public abstract class RunState {

    private Lane lane;

    public RunState(Lane lane) {
        this.lane = lane;
    }

    public abstract void setState(); // update what should happen next based on the conditions
    public abstract void bowlFrame(); // do the actions
}
