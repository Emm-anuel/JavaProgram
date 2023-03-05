package com.epam.javaprogram.multithreading.task5.semaphore;

public class Test {
    public static void main(String args[]) {
        Buffer buffer = new Buffer(10);

        Runnable rProductor = new Productor(buffer);
        Runnable rConsumidor = new Consumidor(buffer);

        Thread productor = new Thread(rProductor);
        Thread consumidor = new Thread(rConsumidor);

        productor.start();
        consumidor.start();
    }
}
