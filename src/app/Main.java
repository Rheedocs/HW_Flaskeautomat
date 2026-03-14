package app;

import core.Buffer;
import core.Consumer;
import core.Producer;
import core.Splitter;
import util.AnsiColors;

// Starter flaskeautomaten med én producer, én splitter og to consumers
public class Main {

    public static void main(String[] args) {
        Buffer inputBuffer = new Buffer(5);
        Buffer beerBuffer  = new Buffer(5);
        Buffer waterBuffer = new Buffer(5);

        new Thread(new Producer(inputBuffer)).start();
        new Thread(new Splitter(inputBuffer, beerBuffer, waterBuffer)).start();
        new Thread(new Consumer(beerBuffer,  "ØL-consumer  ", AnsiColors.BLUE)).start();
        new Thread(new Consumer(waterBuffer, "VAND-consumer", AnsiColors.MAGENTA)).start();
    }
}