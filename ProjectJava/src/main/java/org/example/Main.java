package org.example;
import org.example.model.Game;
import org.example.model.JsonIO;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        JsonIO json = new JsonIO();
        Game g = new Game(json.getBoard(),json.getPlayerList(),json.getDie(),json.getGameStart(),json.getInteraction(),json.getCurrentPlayer());
        g.play();
        json.saveGame();
    }
}