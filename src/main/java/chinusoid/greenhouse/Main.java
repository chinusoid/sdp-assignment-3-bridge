package chinusoid.greenhouse;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        System.out.println("Greenhouse watering");
        System.out.println();

        demonstrate("Regular program + drip system",
                new RegularWatering(new DripIrrigation()));
        demonstrate("Regular program + sprinkler system",
                new RegularWatering(new SprinklerIrrigation()));
        demonstrate("Intensive program + drip system",
                new IntensiveWatering(new DripIrrigation()));
        demonstrate("Intensive program + sprinkler system",
                new IntensiveWatering(new SprinklerIrrigation()));

        System.out.println("Switching irrigation system:");
        WateringProgram program = new RegularWatering(new DripIrrigation());
        program.run();
        program.setIrrigationSystem(new SprinklerIrrigation());
        program.run();
    }

    private static void demonstrate(String label, WateringProgram program) {
        System.out.println(label);
        program.run();
        System.out.println();
    }
}
