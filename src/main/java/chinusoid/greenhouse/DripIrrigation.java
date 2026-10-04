package chinusoid.greenhouse;

public final class DripIrrigation implements IrrigationSystem {
    @Override
    public void irrigate(int durationMinutes) {
        IrrigationOutput.print("Drip irrigation", durationMinutes);
    }
}
