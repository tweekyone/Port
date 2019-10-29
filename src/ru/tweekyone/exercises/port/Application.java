package ru.tweekyone.exercises.port;

public class Application {

    public static void main(String[] args) {
        Port port = new Port(ContainerLoader.getPortStorage());
        Ship nautilus = new Ship("Nautilus", 5, port);
        Ship titanic = new Ship("Titanic", port, ContainerLoader.getShipStorage("Titanic"));
        Ship aurora = new Ship("Aurora", 7, port);
        Ship anastasia = new Ship("Anastasia", port, ContainerLoader.getShipStorage("Anastasia"));
        Ship polaris = new Ship("Polaris", port, ContainerLoader.getShipStorage("Polaris"));
        Thread thread1 = new Thread(nautilus);
        Thread thread2 = new Thread(titanic);
        Thread thread3 = new Thread(aurora);
        Thread thread4 = new Thread(anastasia);
        Thread thread5 = new Thread(polaris);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
    
}
