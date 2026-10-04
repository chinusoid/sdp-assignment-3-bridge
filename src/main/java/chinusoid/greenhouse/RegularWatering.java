package chinusoid.greenhouse;

/** Refined Abstraction: a short watering program for routine greenhouse care. */
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
