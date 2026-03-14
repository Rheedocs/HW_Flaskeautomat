package core;

import util.AnsiColors;

// Producerer flasker og lægger dem i bufferen
public class Producer implements Runnable {

    private static final int MAX_SLEEP_MS = 1000;

    private final Buffer buffer;
    private int count = 1;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Bottle bottle = generateBottle();
                System.out.println(AnsiColors.GREEN + "Producer      → bånd:    " + bottle + AnsiColors.RESET);
                buffer.put(bottle);
                Thread.sleep((long) (Math.random() * MAX_SLEEP_MS));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    // Genererer en tilfældig flaske, 50/50 chance for øl eller vand
    private Bottle generateBottle() {
        String type = Math.random() > 0.5 ? "øl" : "vand";
        return new Bottle(count++, type);
    }
}