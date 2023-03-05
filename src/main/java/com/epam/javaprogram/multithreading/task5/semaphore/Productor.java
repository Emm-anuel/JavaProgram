package com.epam.javaprogram.multithreading.task5.semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Productor implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Productor.class);

    private Buffer buffer;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                buffer.producir(i);
                LOGGER.info("Productor add: " + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}