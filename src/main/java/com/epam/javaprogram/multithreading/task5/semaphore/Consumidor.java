package com.epam.javaprogram.multithreading.task5.semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumidor implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Consumidor.class);

    private Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                int valor = buffer.consumir();
                LOGGER.info("Consumidor read: " + valor);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}