package ru.tweekyone.exercises.port;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private final String name;
    private final int capasity;
    private List<Container> storage;

    public Ship(String name, int capasity) {
        this.name = name;
        this.capasity = capasity;
        storage = new ArrayList<>();
    }

    public Ship(String name, int capasity, ArrayList<Container> storage) {
        this.name = name;
        this.capasity = capasity;
        this.storage = storage;
    }

    public String getName() {
        return name;
    }

    public int getCAPASITY() {
        return capasity;
    }
    
    public Container takeContainer() {
        if (storage.size() > 0){
            return storage.
        }
    }
}
