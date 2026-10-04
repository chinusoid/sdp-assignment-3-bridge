package chinusoid.greenhouse;

import java.util.Objects;

/** Abstraction: a watering program connected to a replaceable irrigation system. */
public abstract class WateringProgram {
    private IrrigationSystem irrigationSystem;

    protected WateringProgram(IrrigationSystem irrigationSystem) {
        setIrrigationSystem(irrigationSystem);
    }

    public final void setIrrigationSystem(IrrigationSystem irrigationSystem) {
        this.irrigationSystem = Objects.requireNonNull(
                irrigationSystem, "Irrigation system must not be null");
    }

    protected final void irrigateFor(int durationMinutes) {
        irrigationSystem.irrigate(durationMinutes);
    }

    public abstract void run();
}
