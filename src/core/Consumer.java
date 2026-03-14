package core;

import util.AnsiColors;

// Henter flasker fra en buffer, navn og farve sendes ind i konstruktøren
public class Consumer implements Runnable {

    private static final int MAX_SLEEP_MS = 800;

    private final Buffer buffer;
    private final String name;
    private final String color;

    public Consumer(Buffer buffer, String name, String color) {
        this.buffer = buffer;
        this.name = name;
        this.color = color;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Bottle bottle = buffer.get();
                System.out.println(color + name + " → henter:  " + bottle + AnsiColors.RESET);
                Thread.sleep((long) (Math.random() * MAX_SLEEP_MS));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}