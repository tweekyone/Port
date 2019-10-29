package ru.tweekyone.exercises.port;

import java.util.LinkedList;
import java.util.Queue;
import ru.tweekyone.exercises.port.Port.Dock;

public class Ship implements Runnable{

    private final String name;
    private final int capasity;
    private Queue<Container> storage;
    private Port port;

    public Ship(String name, int capasity, Port port) {
        this.name = name;
        this.capasity = capasity;
        this.port = port;
        storage = new LinkedList<>();
    }

    public Ship(String name, Port port, LinkedList<Container> storage) {
        this.name = name;
        this.capasity = storage.size();
        this.port = port;
        this.storage = storage;
    }

    public String getName() {
        return name;
    }

    public int getCAPASITY() {
        return capasity;
    }
    
    public void landing(Port.Dock dock) {
        System.out.println("Ship " + getName() + " start landing in Dock " + dock.getNumberOfDock());
        dock.putContainers(storage);
        System.out.println("Ship " + getName() + " end landing in Dock " + dock.getNumberOfDock());
    }
    
    public void loading(Port.Dock dock){
        System.out.println("Ship " + getName() + " start loadingin Dock " + dock.getNumberOfDock());
        dock.takeContainers(storage, capasity);
        System.out.println("Ship " + getName() + " end loadingin Dock " + dock.getNumberOfDock());
    }

    @Override
    public void run() {
        Dock dock = port.getFreeDock();
        if (storage.isEmpty()){
            loading(dock);
        } else {
            landing(dock);
        }
        port.freeTheDock(dock);
        dock = null;
    }
}
