package chinusoid.greenhouse;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/** Dependency-free checks. Run this class directly; assertions are always enabled here. */
public final class BridgeVerification {
    private BridgeVerification() {
    }

    public static void main(String[] args) {
        verifyProgramDurations();
        verifyEveryCombination();
        verifyRuntimeSwitch();
        verifyNullSystemsAreRejected();
        verifyInvalidDurationsAreRejected();
        System.out.println("All 5 Bridge verification groups passed.");
    }

    private static void verifyProgramDurations() {
        RecordingIrrigationSystem system = new RecordingIrrigationSystem();
        new RegularWatering(system).run();
        check(system.lastDuration == 5, "Regular watering should last 5 minutes");
        new IntensiveWatering(system).run();
        check(system.lastDuration == 15, "Intensive watering should last 15 minutes");
        check(system.calls == 2, "Each program must delegate exactly once");
    }

    private static void verifyEveryCombination() {
        checkOutput(new RegularWatering(new DripIrrigation()),
                "Drip irrigation: watering for 5 minutes.");
        checkOutput(new RegularWatering(new SprinklerIrrigation()),
                "Sprinkler irrigation: watering for 5 minutes.");
        checkOutput(new IntensiveWatering(new DripIrrigation()),
                "Drip irrigation: watering for 15 minutes.");
        checkOutput(new IntensiveWatering(new SprinklerIrrigation()),
                "Sprinkler irrigation: watering for 15 minutes.");
    }

    private static void verifyRuntimeSwitch() {
        RecordingIrrigationSystem first = new RecordingIrrigationSystem();
        RecordingIrrigationSystem second = new RecordingIrrigationSystem();
        WateringProgram program = new RegularWatering(first);
        program.run();
        program.setIrrigationSystem(second);
        program.run();
        check(first.calls == 1, "The previous system must stop receiving calls");
        check(second.calls == 1 && second.lastDuration == 5,
                "The same program must delegate to the replacement system");

        checkOutput(programWithSwitch(), "Sprinkler irrigation: watering for 5 minutes.");
    }

    private static WateringProgram programWithSwitch() {
        WateringProgram program = new RegularWatering(new DripIrrigation());
        program.setIrrigationSystem(new SprinklerIrrigation());
        return program;
    }

    private static void verifyNullSystemsAreRejected() {
        expectThrows(NullPointerException.class, () -> new RegularWatering(null));
        expectThrows(NullPointerException.class, () -> new IntensiveWatering(null));

        RecordingIrrigationSystem system = new RecordingIrrigationSystem();
        WateringProgram program = new RegularWatering(system);
        expectThrows(NullPointerException.class, () -> program.setIrrigationSystem(null));
        program.run();
        check(system.calls == 1, "An invalid replacement must retain the previous system");
    }

    private static void verifyInvalidDurationsAreRejected() {
        IrrigationSystem[] systems = {new DripIrrigation(), new SprinklerIrrigation()};
        for (IrrigationSystem system : systems) {
            expectThrows(IllegalArgumentException.class, () -> system.irrigate(0));
            expectThrows(IllegalArgumentException.class, () -> system.irrigate(-1));
        }
    }

    private static void checkOutput(WateringProgram program, String expected) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream original = System.out;
        try (PrintStream captured = new PrintStream(buffer, true, StandardCharsets.UTF_8)) {
            System.setOut(captured);
            program.run();
        } finally {
            System.setOut(original);
        }
        String actual = buffer.toString(StandardCharsets.UTF_8);
        check(actual.equals(expected + System.lineSeparator()), "Unexpected output: " + actual);
    }

    private static void expectThrows(Class<? extends Throwable> type, Runnable action) {
        try {
            action.run();
        } catch (Throwable error) {
            if (type.isInstance(error)) {
                return;
            }
            throw new AssertionError("Expected " + type.getSimpleName(), error);
        }
        throw new AssertionError("Expected " + type.getSimpleName() + " to be thrown");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    /** A new Implementor can be used without changing either watering program. */
    private static final class RecordingIrrigationSystem implements IrrigationSystem {
        private int lastDuration;
        private int calls;

        @Override
        public void irrigate(int durationMinutes) {
            lastDuration = durationMinutes;
            calls++;
        }
    }
}
