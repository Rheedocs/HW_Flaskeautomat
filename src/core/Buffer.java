package core;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Delt ressource mellem trådene, bruger ReentrantLock og Condition til trådsikker kommunikation
public class Buffer {

    private final Queue<Bottle> queue = new LinkedList<>();
    private final int maxSize;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull  = lock.newCondition(); // producer venter her
    private final Condition notEmpty = lock.newCondition(); // consumer venter her

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
    }

    // Tilføj flaske, vent hvis bufferen er fuld
    public void put(Bottle bottle) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == maxSize) notFull.await();
            queue.add(bottle);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    // Hent flaske, vent hvis bufferen er tom
    public Bottle get() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) notEmpty.await();
            Bottle bottle = queue.poll();
            notFull.signal();
            return bottle;
        } finally {
            lock.unlock();
        }
    }
}