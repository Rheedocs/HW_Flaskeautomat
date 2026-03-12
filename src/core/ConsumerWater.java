package core;

import util.AnsiColors;
import util.BottleFormatter;

// Henter vandflasker fra vand-båndet og behandler dem.
public class ConsumerWater implements Runnable {

    private static final int MAX_SLEEP_MS = 800;
    private final Buffer waterBuffer;

    public ConsumerWater(Buffer waterBuffer) {
        this.waterBuffer = waterBuffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String bottle = waterBuffer.get();
                System.out.println(AnsiColors.MAGENTA + "VAND-consumer → henter:  "
                        + BottleFormatter.format(bottle) + AnsiColors.RESET);
                // Simuler tilfældig behandlingstid
                Thread.sleep((long) (Math.random() * MAX_SLEEP_MS));
            } catch (InterruptedException e) {
                System.out.println("Thread error: " + e.getMessage());
            }
        }
    }
}