package com.epam.javaprogram.multithreading.task5.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {
    public static void main(String args[]) {
        BlockingQueue<Integer> cola = new LinkedBlockingQueue<>(10);

        Productor productor = new Productor(cola);
        Consumidor consumidor = new Consumidor(cola);

        new Thread(productor).start();
        new Thread(consumidor).start();
    }
}
