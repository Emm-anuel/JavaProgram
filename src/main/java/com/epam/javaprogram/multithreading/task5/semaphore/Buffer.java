package com.epam.javaprogram.multithreading.task5.semaphore;

import java.util.concurrent.Semaphore;

public class Buffer {
    private int[] buffer;
    private Semaphore semaforoProducir;
    private Semaphore semaforoConsumir;
    private int numElementos;

    public Buffer(int capacidad) {
        this.buffer = new int[capacidad];
        this.semaforoProducir = new Semaphore(capacidad, true);
        this.semaforoConsumir = new Semaphore(0, true);
        this.numElementos = 0;
    }

    public void producir(int valor) throws InterruptedException {
        semaforoProducir.acquire();
        synchronized (this) {
            buffer[numElementos++] = valor;
        }
        semaforoConsumir.release();
    }

    public int consumir() throws InterruptedException {
        semaforoConsumir.acquire();
        int valor;
        synchronized (this) {
            valor = buffer[--numElementos];
        }
        semaforoProducir.release();
        return valor;
    }
}
