package chinusoid.greenhouse;

/** Concrete Implementor: simulates watering plants through drip emitters. */
public final class DripIrrigation implements IrrigationSystem {
    @Override
    public void irrigate(int durationMinutes) {
        IrrigationOutput.print("Drip irrigation", durationMinutes);
    }
}
