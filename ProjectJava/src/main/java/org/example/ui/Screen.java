package org.example.ui;

import java.util.Scanner;

public class Screen {
    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public static String promptString() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}
