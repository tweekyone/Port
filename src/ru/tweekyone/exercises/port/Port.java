package ru.tweekyone.exercises.port;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
    private BlockingQueue<Dock> docks;

    public Port() {
        storage = new ArrayBlockingQueue<>(CAPACITY);
        docks();
    }
    
    public Port(LinkedList<Container> storage){
        this.storage = new ArrayBlockingQueue<>(CAPACITY);
        this.storage.addAll(storage);
        docks();
    }

    private void docks(){
        docks = new ArrayBlockingQueue<>(NUMBER_OF_DOCKS);
        for (int i = 1; i < NUMBER_OF_DOCKS + 1; i++) {
            docks.add(new Dock(i));
        }
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
        
        public void takeContainers(Queue<Container> containers, int capasity) {
            try{
                //lock.lock();
                while (containers.size() < capasity){
                    Container cont = storage.take();
                    containers.add(cont);
                    System.out.println("Container " + cont.getSerialNumber() + " was taken from the storage through Dock " + getNumberOfDock());
                }
                //isFree.signalAll();
            } catch (InterruptedException e){
                System.out.println("Interrupted exception in TAKE "+ e);
            } finally {
                //lock.unlock();
            }
        }
        
        public void putContainers(Queue<Container> containers) {
            try {
                //lock.lock();
                while (!containers.isEmpty()){
                    Container cont = containers.poll();
                    storage.put(cont);
                    System.out.println("Container " + cont.getSerialNumber() + " was accepted in storage through Dock " + getNumberOfDock());
                }
                //isFree.signalAll();
            } catch (InterruptedException e) {
                System.out.println("Interrupted exception in PUT " + e);
            } finally {
                //lock.unlock();
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
    
    public Dock getFreeDock(){
        try{
            return docks.take();
        }catch (InterruptedException e){
            System.out.println("Interrupted exception " + e);
        } throw new IllegalArgumentException("Port has no free dock");
    }
    
    public void freeTheDock(Dock dock){
        try{
            docks.put(dock);
        }catch (InterruptedException e){
            System.out.println("Interrupted exception " + e);
        }
    }
}
