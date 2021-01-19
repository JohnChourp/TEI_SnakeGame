package org.example.ui;

public class QuitGame {
    boolean quit;
    boolean tryAgain;

    public boolean isQuit() {
        return quit;
    }

    public boolean isQuitPrompt() {
//        Screen.displayMessage("Quit game yes ('y') or no ('n').");
//
//        do
//        {
//            tryAgain = false;
//            quit = true;
//
//            switch (Screen.promptString())
//            {
//                case "y":
//                    quit = false;
//                    break;
//                case "n":
//                    break;
//                default:
//                    Screen.displayMessage("Invalid option, Try again");
//                    tryAgain = true;
//                    break;
//            }
//        }
//        while(tryAgain);
        quit = true;
        return quit;
    }
}
