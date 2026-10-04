package chinusoid.greenhouse;

public final class IntensiveWatering extends WateringProgram {
    private static final int DURATION_MINUTES = 15;

    public IntensiveWatering(IrrigationSystem irrigationSystem) {
        super(irrigationSystem);
    }

    @Override
    public void run() {
        irrigateFor(DURATION_MINUTES);
    }
}
