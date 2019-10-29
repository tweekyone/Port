package ru.tweekyone.exercises.port;

import java.util.LinkedList;

public class ContainerLoader {
    
    public static LinkedList<Container> getShipStorage(String shipName){
        LinkedList<Container> storage = new LinkedList<>();
        int capasity = (int) ((Math.random() * 5) + 1);
        for (int i = 0; i < capasity; i++){
            storage.add(new Container(shipName.toUpperCase() + i));
        }
        return storage;
    }
    
    public static LinkedList<Container> getPortStorage(){
        LinkedList<Container> storage = new LinkedList<>();
        int capasity = 25; //(int) (Math.random() * 21);
        for (int i = 0; i < capasity; i++){
            storage.add(new Container("PORT" + i));
        }
        return storage;
    }
}
