package ru.tweekyone.exercises.port;

public class Container {
    private final String serialNumber;

    public Container(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public String getSerialNumber() {
        return serialNumber;
    }    
}
