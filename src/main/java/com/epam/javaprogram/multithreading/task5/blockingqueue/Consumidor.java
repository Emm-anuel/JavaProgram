package com.epam.javaprogram.multithreading.task5.blockingqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

public class Consumidor implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Consumidor.class);

    private BlockingQueue<Integer> cola;

    public Consumidor(BlockingQueue<Integer> cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                int valor = cola.take();
                LOGGER.info("Consumidor removed: " + valor);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}