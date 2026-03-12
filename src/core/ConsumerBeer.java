package core;

import util.AnsiColors;
import util.BottleFormatter;

// Henter ølflasker fra øl-båndet og behandler dem.
public class ConsumerBeer implements Runnable {

    private static final int MAX_SLEEP_MS = 800;
    private final Buffer beerBuffer;

    public ConsumerBeer(Buffer beerBuffer) {
        this.beerBuffer = beerBuffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String bottle = beerBuffer.get();
                System.out.println(AnsiColors.BLUE + "ØL-consumer   → henter:  "
                        + BottleFormatter.format(bottle) + AnsiColors.RESET);
                // Simuler tilfældig behandlingstid
                Thread.sleep((long)(Math.random() * MAX_SLEEP_MS));
            } catch (InterruptedException e) {
                System.out.println("Thread error: " + e.getMessage());
            }
        }
    }
}