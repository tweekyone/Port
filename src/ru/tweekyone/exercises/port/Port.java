package ru.tweekyone.exercises.port;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class Port {
    
    private static final int NUMBER_OF_DOCKS = 3; 
    private static final int CAPACITY = 30;  
    private BlockingQueue<Container> storage;

    public Port() {
        storage = new ArrayBlockingQueue<>(CAPACITY);
    }

    class Dock {
        
        private final int numberOfDock;
        private Lock lock;
        private Condition isFree;

        public Dock(int numberOfDock) {
            this.numberOfDock = numberOfDock;
            lock = new ReentrantLock();
            isFree = lock.newCondition();
        }

        public int getNumberOfDock() {
            return numberOfDock;
        }
        
        public Container takeContainer() {
            Container container = null;
            lock.lock();
            
            try{
                container = storage.take();
            } catch (InterruptedException e) {
                System.out.println("Interrupted exception " + e);
            }
            
            if (container != null) {
                System.out.println("Container " + container.getSerialNumber() + " was taken from " + getNumberOfDock() + " dock");
                isFree.signalAll();
                lock.unlock();
                return container;
            } else throw new IllegalArgumentException();           
        }
        
        public void putContainer(Container container) {
            
            try {
                lock.lock();
                storage.put(container);
                System.out.println("Container " + container.getSerialNumber() + " was accepted in storage through " + getNumberOfDock() + " dock");
                isFree.signalAll();
            } catch (InterruptedException e) {
                System.out.println("Interrupted exception " + e);
            } finally {
                lock.unlock();
            }
        } 
    }
    
    public int getNUMBER_OF_DOCKS() {
        return NUMBER_OF_DOCKS;
    }
    
    public List<Container> getStorage() {
        List<Container> containers = Arrays.stream(storage.toArray())
                                           .map(x -> (Container) x)
                                           .collect(Collectors.toList());
        return containers;
    }
}
