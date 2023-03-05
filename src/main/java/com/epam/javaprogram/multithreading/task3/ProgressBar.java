package com.epam.javaprogram.multithreading.task3;

public class ProgressBar implements Runnable {
    @Override
    public void run() {
        String progressBar;
        int counter = 0;
        char[] animChars = {'|', '/', '-', '\\'};
        int i = 0;
        while (true) {
            System.out.print("\rScanning " + animChars[i++ % animChars.length]);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // Ignore
            }
        }
    }
}


