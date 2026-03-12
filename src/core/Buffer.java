package core;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Trådsikker buffer der fungerer som et bånd mellem producer og consumer.
// Implementeret som en ressource-monitor med ReentrantLock og betingelsesvariable
public class Buffer {

    private final Queue<String> queue = new LinkedList<>();
    private final int maxSize;

    private final Lock lock = new ReentrantLock();

    // To separate betingelser så vi kun vækker relevante tråde.
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
    }

    // Tilføj flaske, vent hvis bufferen er fuld.
    public void put(String bottle) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == maxSize) {
                notFull.await();
            }
            queue.add(bottle);
            notEmpty.signal(); // vækker en ventende consumer.
        } finally {
            lock.unlock(); // frigiv låsen selv ved fejl.
        }
    }

    // Hent flaske, vent hvis bufferen er tom.
    public String get() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            String bottle = queue.poll();
            notFull.signal(); // vækker en ventende producer.
            return bottle;
        } finally {
            lock.unlock(); // frigiv låsen selv ved fejl.
        }
    }
}