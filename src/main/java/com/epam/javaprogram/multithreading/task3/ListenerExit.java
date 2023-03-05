package com.epam.javaprogram.multithreading.task3;

import java.util.Scanner;

public class ListenerExit implements Runnable {
    @Override
    public void run() {
        System.out.println("Press [c] for interrupt the process");
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("");
        while (true) {
            if (scanner.hasNextLine()) {
                String input = scanner.next();
                if (input.equalsIgnoreCase("c")) {
                    ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
                    ThreadGroup rootGroup = currentGroup;
                    while (rootGroup.getParent() != null) {
                        rootGroup = rootGroup.getParent();
                    }
                    Thread[] threads = new Thread[rootGroup.activeCount()];
                    int count = rootGroup.enumerate(threads);
                    for (int ind = 0; ind < count; ind++) {
                        threads[ind].interrupt();
                    }
                    System.out.println("Bye");
                    System.exit(0);
                }
            }
        }
    }
}
