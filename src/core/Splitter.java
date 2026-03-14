package core;

import util.AnsiColors;

// Henter flasker fra inputbufferen og sorterer dem til øl- eller vandbufferen
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
                Bottle bottle = inputBuffer.get();
                sortBottle(bottle);
                Thread.sleep((long) (Math.random() * MAX_SLEEP_MS));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    // Sorterer flasken til den rigtige buffer baseret på type
    private void sortBottle(Bottle bottle) throws InterruptedException {
        if (bottle.getType().equals("øl")) {
            System.out.println(AnsiColors.YELLOW + "Splitter      → øl:      " + bottle + AnsiColors.RESET);
            beerBuffer.put(bottle);
        } else {
            System.out.println(AnsiColors.YELLOW + "Splitter      → vand:    " + bottle + AnsiColors.RESET);
            waterBuffer.put(bottle);
        }
    }
}