package chinusoid.greenhouse;

public final class RegularWatering extends WateringProgram {
    private static final int DURATION_MINUTES = 5;

    public RegularWatering(IrrigationSystem irrigationSystem) {
        super(irrigationSystem);
    }

    @Override
    public void run() {
        irrigateFor(DURATION_MINUTES);
    }
}
