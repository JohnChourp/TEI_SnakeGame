package org.example.model.squares;
import org.example.model.Player;
import org.example.ui.Screen;

public class GetImmunity implements SquareInfo
{
    public void applyAction(Player currentPlayer)
    {
      currentPlayer.setHasImmunity(true);
      Screen.displayMessage("Got immunity");
    }
}
