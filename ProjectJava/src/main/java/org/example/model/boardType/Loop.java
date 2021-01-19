package org.example.model.boardType;
import org.example.model.Player;
import org.example.ui.Screen;

public class Loop implements BoardType
{
    boolean winner;
    int rounds;

    public Loop(int rounds)
    {
        this.rounds = rounds;
    }

    public void endAction(int posAfterRoll, int lastSquare, Player player)
    {
        if(posAfterRoll > lastSquare)
        {
            posAfterRoll = posAfterRoll - lastSquare;
            player.setRounds();
            Screen.displayMessage(player.getName() + " Looped "+player.getRound() + " Times");
        }

        player.setCurrentPos(posAfterRoll);

        if(player.getRound() == rounds)
        {
            player.setCurrentPos(lastSquare);
            winner = true;
        }
    }

    public boolean isWinner()
    {
        return winner;
    }
}
