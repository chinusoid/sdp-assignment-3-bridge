package chinusoid.greenhouse;

public final class SprinklerIrrigation implements IrrigationSystem {
    @Override
    public void irrigate(int durationMinutes) {
        IrrigationOutput.print("Sprinkler irrigation", durationMinutes);
    }
}
