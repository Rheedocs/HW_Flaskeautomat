package core;

import util.AnsiColors;

// Genererer tilfældige øl- og soda/vandflasker og lægger dem på inputbåndet
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
                buffer.put(generateBottle());
                // Simuler tilfældig produktionstid
                Thread.sleep((long)(Math.random() * MAX_SLEEP_MS));
            } catch (InterruptedException e) {
                System.out.println("Thread error: " + e.getMessage());
            }
        }
    }

    // Generer tilfældig øl- eller vandflaske med løbenummer
    private String generateBottle() {
        // 50/50 chance for øl eller vand
        String type = Math.random() > 0.5 ? "øl" : "vand";
        System.out.println(AnsiColors.GREEN + "Producer      → bånd:    " + type + " #" + count + AnsiColors.RESET);
        return type + count++;
    }
}