package chinusoid.greenhouse;

final class IrrigationOutput {
    private IrrigationOutput() {
    }

    static void print(String systemName, int durationMinutes) {
        if (durationMinutes <= 0) {
            throw new IllegalArgumentException("Watering duration must be positive");
        }
        System.out.printf("%s: watering for %d minutes.%n", systemName, durationMinutes);
    }
}
