package com.epam.javaprogram.util;

import java.io.IOException;

public class ClearConsole {
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.println("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {}
    }
}