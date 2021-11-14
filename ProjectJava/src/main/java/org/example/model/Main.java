package org.example.model;

import org.json.JSONException;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException, JSONException {
		JsonIO json = new JsonIO();
		Game g = new Game(json.loadBoardType(),json.loadSquares(), json.loadPlayerList(), json.loadDie(), json.loadGameStart(), json.loadInteraction(), new Player());
		g.play();
		json.saveGame();
	}
}