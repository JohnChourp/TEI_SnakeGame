package org.example.model;

import org.example.model.boardType.Board;
import org.example.model.squares.Squares;
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

public class JsonIO {
	JSONObject obj;
	JFileChooser chooser = new JFileChooser();
	Die die = new Die();
	PlayerList playerList = new PlayerList();

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

	private void newGame() throws IOException, JSONException {
		String contents;
		chooser.setCurrentDirectory(new File("ProjectJava/src/main/resources/JsonFiles"));
		chooser.setDialogTitle("Choose a Game");
		contents = new String(Files.readAllBytes(Paths.get(String.valueOf(getFileChooser()))));
		obj = new JSONObject(contents);
	}

	private void loadGame() throws IOException, JSONException {
		String contents;
		chooser.setCurrentDirectory(new File("ProjectJava/src/main/resources/JsonFilesSaved"));
		chooser.setDialogTitle("Choose a Game");
		contents = new String(Files.readAllBytes(Paths.get(String.valueOf(getFileChooser()))));
		obj = new JSONObject(contents);
	}

	public JsonIO() throws IOException, JSONException {
		boolean tryAgain;
		Screen.displayMessage("New Game or Saved Game?, type('new') or ('load')");

		do {
			tryAgain = false;

			switch (Screen.promptString()) {
				case "new":
					newGame();
					break;
				case "load":
					loadGame();
					playerList.setPlayerNumber(obj.getInt("turn"));
					break;
				default:
					Screen.displayMessage("Invalid option, Try again");
					tryAgain = true;
					break;
			}
		} while (tryAgain);
	}

	public Die loadDie() throws JSONException {
		die.setDieBound(obj.getInt("dieNumber"));
		return die;
	}

	public GameStart loadGameStart() throws JSONException {
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

	public Interaction loadInteraction() throws JSONException {
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

	public Board loadBoardType() throws JSONException {
		JSONObject jsonBoardType = (JSONObject) obj.get("boardType");
		JSONObject jsonBoard = obj.getJSONObject("Board");
		JSONArray squares = jsonBoard.getJSONArray("squares");
		Board board = new Board();

		switch (jsonBoardType.getString("type")) {
			case "Bounce":
				board.setBoardType(new Bounce(squares.length() - 1));
				break;
			case "Classic":
				board.setBoardType(new Classic(squares.length() - 1));
				break;
			case "Loop":
				board.setBoardType(new Loop(jsonBoardType.getInt("rounds"), squares.length() - 1));
				break;
		}
		return board;
	}

	public Squares loadSquares() throws JSONException {
		JSONObject jsonBoard = obj.getJSONObject("Board");
		JSONArray jsonSquares = jsonBoard.getJSONArray("squares");
		JSONObject square;
		Squares squares = new Squares();

		for (int i = 0; i < jsonSquares.length(); i++) {
			square = (JSONObject) jsonSquares.get(i);

			switch (square.getString("SquareInfo")) {
				case "Empty":
					squares.addSquare(new Empty());
					break;
				case "Ladder":
					squares.addSquare(new Ladder(square.getInt("GotoSquare")));
					break;
				case "Snake":
					squares.addSquare(new Snake(square.getInt("GotoSquare")));
					break;
				case "loseTurn":
					squares.addSquare(new loseTurn());
					break;
				case "GetImmunity":
					squares.addSquare(new GetImmunity());
					break;
				case "Winner":
					squares.addSquare(new Win());
					break;
			}
		}
		return squares;
	}

	public PlayerList loadPlayerList() throws JSONException {
		JSONObject jsonPlayerList = obj.getJSONObject("playerList");
		JSONObject jsonPlayer;
		Player player;

		for (int i = 0; i < jsonPlayerList.length(); i++) {
			player = new Player();
			jsonPlayer = jsonPlayerList.getJSONObject("player" + (i+1));

			player.setName(jsonPlayer.getString("name"));
			player.setPos(jsonPlayer.getInt("pos"));
			player.setLostTurn(jsonPlayer.getBoolean("lostTurn"));
			player.setCanPlayAtStart(jsonPlayer.getBoolean("canPlayAtStart"));
			player.setHasImmunity(jsonPlayer.getBoolean("hasImmunity"));
			player.setRound(jsonPlayer.getInt("round"));
			player.setWinner(jsonPlayer.getBoolean("winner"));
			playerList.setPlayerList(player);
		}
		return playerList;
	}

	public void saveGame() throws IOException, JSONException {
		chooser.setCurrentDirectory(new java.io.File("ProjectJava/src/main/resources/JsonFilesSaved"));
		chooser.setDialogTitle("Save File");

		JSONObject jsonPlayerList = obj.getJSONObject("playerList");
		JSONObject jsonPlayer;

		FileWriter file = new FileWriter(getFileChooser());
		file.write("{\"dieNumber\":" + die.getDieBound() + ",\n");
		file.write("\"GameStart\":\"" + obj.getString("GameStart") + "\",\n");
		file.write("\"Interaction\":" + obj.get("Interaction") + ",\n");
		file.write("\"boardType\":" + obj.getJSONObject("boardType") + ",\n");
		file.write("\"Board\":" + obj.getJSONObject("Board") + ",\n");
		file.write("\"turn\":" + playerList.getPlayerNumber() + ",\n");
		file.write("\"playerList\":{");

		for (int i = 0; i < jsonPlayerList.length(); i++) {
			file.write("\"player" + (i+1) + "\"" + ":");
			jsonPlayer = jsonPlayerList.getJSONObject("player" + (i+1));
			jsonPlayer.put("name", playerList.getPlayerList().get(i).getName());
			jsonPlayer.put("pos", playerList.getPlayerList().get(i).getPos());
			jsonPlayer.put("lostTurn", playerList.getPlayerList().get(i).isLostTurn());
			jsonPlayer.put("canPlayAtStart", playerList.getPlayerList().get(i).isCanPlayAtStart());
			jsonPlayer.put("hasImmunity", playerList.getPlayerList().get(i).isHasImmunity());
			jsonPlayer.put("round", playerList.getPlayerList().get(i).getRound());
			jsonPlayer.put("winner", playerList.getPlayerList().get(i).isWinner());
			file.write(String.valueOf(jsonPlayer));
			if (i < (jsonPlayerList.length()-1)) {
				file.write(",");
			}
			file.write("\n");
			playerList.setNextPlayerNumber();
		}
		file.write("\t}\n}");
		file.flush();
		file.close();
	}
}