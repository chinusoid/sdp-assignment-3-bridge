package chinusoid.greenhouse;

/** Concrete Implementor: simulates watering plants through sprinkler nozzles. */
public final class SprinklerIrrigation implements IrrigationSystem {
    @Override
    public void irrigate(int durationMinutes) {
        IrrigationOutput.print("Sprinkler irrigation", durationMinutes);
    }
}
