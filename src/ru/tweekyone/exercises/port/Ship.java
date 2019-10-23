package ru.tweekyone.exercises.port;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ship {

    private final String name;
    private final int capasity;
    private List<Container> storage;
    private Lock lock;
    private Condition isFree;

    public Ship(String name, int capasity) {
        this.name = name;
        this.capasity = capasity;
        storage = new ArrayList<>();
        lock = new ReentrantLock();
        isFree = lock.newCondition();
    }

    public String getName() {
        return name;
    }

    public int getCAPASITY() {
        return capasity;
    }
    
    public Container takeContainer() {
        
    }
}
