package app;

import core.*;

public class Main {

    public static void main(String[] args) {
        // Tre buffere der fungerer som bånd mellem trådene
        Buffer inputBuffer = new Buffer(5);
        Buffer beerBuffer = new Buffer(5);
        Buffer waterBuffer = new Buffer(5);

        Thread producer = new Thread(new Producer(inputBuffer));
        Thread splitter = new Thread(new Splitter(inputBuffer, beerBuffer, waterBuffer));
        Thread consumerBeer = new Thread(new ConsumerBeer(beerBuffer));
        Thread consumerWater = new Thread(new ConsumerWater(waterBuffer));

        producer.start();
        splitter.start();
        consumerBeer.start();
        consumerWater.start();
    }
}