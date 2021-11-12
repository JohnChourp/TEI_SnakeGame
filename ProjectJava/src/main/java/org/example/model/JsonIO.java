package org.example.model;

import org.example.model.boardType.Bounce;
import org.example.model.boardType.Classic;
import org.example.model.boardType.Loop;
import org.example.model.gameStart.GameStart;
import org.example.model.gameStart.NoGameStart;
import org.example.model.gameStart.RollDiceToEnter;
import org.example.model.interaction.Interaction;
import org.example.model.interaction.MoveOtherPlayer;
import org.example.model.interaction.NoInteraction;
import org.example.model.squares.*;
import org.example.model.ui.Screen;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class JsonIO {
	JSONObject obj;
	JSONObject JsonPlayerList;
	JFileChooser chooser = new JFileChooser();
	Die die = new Die();
	PlayerList playerList = new PlayerList();

	public JsonIO() throws IOException, JSONException {
		boolean tryAgain;
		Screen.displayMessage("New Game or Saved Game?, type('new') or ('load')");

		do {
			tryAgain = false;

			switch (Screen.promptString()) {
				case "new":
					setJsonPlayerList();
					break;
				case "load":
					setJsonPlayerList();
					playerList.setCurrentPlayerNumber(obj.getInt("turn"));
					break;
				default:
					Screen.displayMessage("Invalid option, Try again");
					tryAgain = true;
					break;
			}
		}
		while (tryAgain);
	}

	private void setJsonPlayerList() throws IOException, JSONException {
		String contents;
		chooser.setCurrentDirectory(new File("ProjectJava\\src\\main\\resources\\JsonFiles"));
		chooser.setDialogTitle("Choose a Game");
		contents = new String(Files.readAllBytes(Paths.get(String.valueOf(getFileChooser()))));
		obj = new JSONObject(contents);
		JsonPlayerList = obj.getJSONObject("playerList");
	}

	private File getFileChooser() {
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			Screen.displayMessage("File Path: " + chooser.getSelectedFile());
		} else {
			Screen.displayMessage("No Selection ");
		}
		return chooser.getSelectedFile();
	}

	public Die getDie() throws JSONException {
		die.setDieBound(obj.getInt("dieNumber"));
		return die;
	}

	public GameStart getGameStart() throws JSONException {
		GameStart gameStart = null;

		switch (obj.getString("GameStart")) {
			case "NoGameStart":
				gameStart = new NoGameStart();
				break;
			case "rollDiceToEnter":
				gameStart = new RollDiceToEnter(die);
				break;
		}
		return gameStart;
	}

	public Interaction getInteraction() throws JSONException {
		JSONObject interactObject = (JSONObject) obj.get("Interaction");
		Interaction interaction = null;

		switch (interactObject.getString("InteractType")) {
			case "NoInteraction":
				interaction = new NoInteraction();
				break;
			case "MoveOtherPlayer":
				interaction = new MoveOtherPlayer(playerList, interactObject.getInt("sendPlayerTo"));
				break;
		}
		return interaction;
	}

	public Board getBoard() throws JSONException {
		JSONObject jsonBoard = obj.getJSONObject("Board");
		JSONObject jsonBoardType = jsonBoard.getJSONObject("boardType");
		JSONArray squares = jsonBoard.getJSONArray("squares");
		JSONObject square;
		Board board = new Board();

		switch (jsonBoardType.getString("type")) {
			case "Bounce":
				board.setBoardType(new Bounce());
				break;
			case "Classic":
				board.setBoardType(new Classic());
				break;
			case "Loop":
				board.setBoardType(new Loop(jsonBoardType.getInt("rounds")));
				break;
		}

		for (int i = 0; i < squares.length(); i++) {
			square = (JSONObject) squares.get(i);

			switch (square.getString("SquareInfo")) {
				case "Empty":
					board.addSquare(new Empty());
					break;
				case "Ladder":
					board.addSquare(new Ladder(square.getInt("GotoSquare")));
					break;
				case "Snake":
					board.addSquare(new Snake(square.getInt("GotoSquare")));
					break;
				case "SquareLoseTurn":
					board.addSquare(new SquareLoseTurn());
					break;
				case "GetImmunity":
					board.addSquare(new GetImmunity());
					break;
			}
		}
		return board;
	}

	public Player getCurrentPlayer() {
		return new Player();
	}

	public PlayerList getPlayerList() throws JSONException {
		Iterator<?> iterator = this.JsonPlayerList.keys();
		Player player;
		JSONObject playerObject;

		do {
			player = new Player();
			playerObject = (JSONObject) this.JsonPlayerList.get(iterator.next().toString());

			player.setName(playerObject.getString("name"));
			player.setCurrentPos(playerObject.getInt("currentPos"));
			player.setLostTurn(playerObject.getBoolean("lostTurn"));
			player.setCanPlayAtStart(playerObject.getBoolean("canPlayAtStart"));
			player.setHasImmunity(playerObject.getBoolean("hasImmunity"));
			player.setRound(playerObject.getInt("round"));
			playerList.setPlayer(player);
		}
		while (iterator.hasNext());
		return playerList;
	}

	public void saveGame() throws IOException, JSONException {
		chooser.setCurrentDirectory(new java.io.File("ProjectJava\\src\\main\\resources\\JsonFilesSaved"));
		chooser.setDialogTitle("Choose a Game Slot to Save");
		FileWriter file = new FileWriter(getFileChooser());

		file.write("{\n\"dieNumber\":" + die.getDieBound() + ",\n");
		file.write("\"GameStart\":\"" + obj.getString("GameStart") + "\",\n");
		file.write("\"Interaction\":" + obj.get("Interaction") + ",\n");
		file.write("\"Board\":" + obj.getJSONObject("Board") + ",\n");
		file.write("\"turn\":" + playerList.getCurrentPlayerNumber() + ",\n" + "\"playerList\":\n\t{\n");

		JSONObject player;
		Iterator<?> iterator = this.JsonPlayerList.keys();
		int j = 0;

		do {
			j += 1;
			player = (JSONObject) this.JsonPlayerList.get(iterator.next().toString());
			player.put("name", playerList.getCurrentPlayer().getName());
			player.put("currentPos", playerList.getCurrentPlayer().getCurrentPos());
			player.put("lostTurn", playerList.getCurrentPlayer().isLostTurn());
			player.put("canPlayAtStart", playerList.getCurrentPlayer().isCanPlayAtStart());
			player.put("hasImmunity", playerList.getCurrentPlayer().isHasImmunity());
			player.put("round", playerList.getCurrentPlayer().getRound());
			file.write("\t\"player" + (playerList.getCurrentPlayerNumber() + 1) + "\":\n\t\t" + player);

			if (j < playerList.getPlayerListSize()) {
				file.write(",");
			}
			file.write("\n");
			playerList.setNextPlayer();
		}
		while (iterator.hasNext());

		file.write("\t}\n}");
		file.flush();
		file.close();
	}
}