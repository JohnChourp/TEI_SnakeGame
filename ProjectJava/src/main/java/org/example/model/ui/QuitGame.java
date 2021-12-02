package org.example.model.ui;

public class QuitGame {
	boolean quit;
	boolean tryAgain;

	public boolean isQuit() {
		return quit;
	}

	public boolean isQuitPrompt() {
		Screen.displayMessage("Quit game yes ('y') or no ('n').");
		quit = false;
		tryAgain = false;

		do {
			switch (Screen.promptString()) {
				case "n":
					quit = true;
					tryAgain = false;
					break;
				case "y":
					quit = false;
					tryAgain = false;
					break;
				default:
					Screen.displayMessage("Invalid option, Try again");
					tryAgain = true;
					break;
			}
		} while (tryAgain);
		return quit;
	}
}
