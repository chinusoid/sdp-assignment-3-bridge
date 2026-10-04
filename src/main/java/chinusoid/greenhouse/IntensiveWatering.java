package chinusoid.greenhouse;

/** Refined Abstraction: a longer watering program for plants needing more water. */
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
