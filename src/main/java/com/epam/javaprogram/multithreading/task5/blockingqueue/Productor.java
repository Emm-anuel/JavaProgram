package com.epam.javaprogram.multithreading.task5.blockingqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

public class Productor implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Productor.class);

    private BlockingQueue<Integer> cola;

    public Productor(BlockingQueue<Integer> cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                cola.put(i);
                LOGGER.info("Productor added: " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}