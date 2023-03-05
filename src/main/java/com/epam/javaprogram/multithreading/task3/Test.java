package com.epam.javaprogram.multithreading.task3;

import com.epam.javaprogram.util.ClearConsole;
import com.epam.javaprogram.util.Results;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String args[]) {
        System.out.print("Directory's Path ::: ");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.next();

        //File file = new File("/Users/emmanuel_merida/Documents");
        File file = new File(path);
        if (!file.exists() || !file.isDirectory()) {
            System.err.println("Invalid directory");
            System.exit(1);
        }

        Results results = new Results();
        Runnable listener = new ListenerExit();
        Thread threadListener = new Thread(listener);
        threadListener.setDaemon(true);
        threadListener.start();

        Runnable progressBar = new ProgressBar();
        Thread threadBar = new Thread(progressBar);
        threadBar.setDaemon(true);
        threadBar.start();

        results.startTime();
        Folder folder = Directory.detailsOf(file);
        long taken = results.endTime();
        ClearConsole.clearConsole();
        System.out.println("Number of Files '" + folder.getNumberFile() + "'");
        System.out.println("Number of Folder '" + folder.getNumberFolder() + "'");
        System.out.println("Size of '" + file.getName() + "': " + folder.getSize() + " bytes");

        System.out.println(taken + " nano (" + TimeUnit.NANOSECONDS.toSeconds(taken) + " seconds)");
    }
}
