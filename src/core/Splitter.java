package core;

import util.AnsiColors;
import util.BottleFormatter;

// Henter flasker fra inputbåndet og sorterer dem til øl- eller vandbåndet.
public class Splitter implements Runnable {

    private static final int MAX_SLEEP_MS = 500;
    private final Buffer inputBuffer;
    private final Buffer beerBuffer;
    private final Buffer waterBuffer;

    public Splitter(Buffer inputBuffer, Buffer beerBuffer, Buffer waterBuffer) {
        this.inputBuffer = inputBuffer;
        this.beerBuffer = beerBuffer;
        this.waterBuffer = waterBuffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String bottle = inputBuffer.get();
                sortBottle(bottle);
                // Simuler tilfældig sorteringstid.
                Thread.sleep((long)(Math.random() * MAX_SLEEP_MS));
            } catch (InterruptedException e) {
                System.out.println("Thread error: " + e.getMessage());
            }
        }
    }

    // Send flasken til det rigtige bånd baseret på type.
    private void sortBottle(String bottle) throws InterruptedException {
        if (bottle.startsWith("øl")) {
            System.out.println(AnsiColors.YELLOW + "Splitter      → øl:      "
                    + BottleFormatter.format(bottle) + AnsiColors.RESET);
            beerBuffer.put(bottle);
        } else {
            System.out.println(AnsiColors.YELLOW + "Splitter      → vand:    "
                    + BottleFormatter.format(bottle) + AnsiColors.RESET);
            waterBuffer.put(bottle);
        }
    }
}