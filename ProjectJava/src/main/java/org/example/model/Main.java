package org.example.model;

import org.json.JSONException;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException, JSONException {
		JsonIO json = new JsonIO();
		Game g = new Game(json.getBoard(), json.getPlayerList(), json.getDie(), json.getGameStart(), json.getInteraction(), json.setPlayer());
		g.play();
		json.saveGame();
	}
}