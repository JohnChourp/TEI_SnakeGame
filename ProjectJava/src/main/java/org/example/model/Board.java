package org.example.model;
import org.example.model.boardType.BoardType;
import org.example.model.squares.SquareInfo;
import java.util.ArrayList;

public class Board
{

	private final ArrayList<SquareInfo> squares = new ArrayList<>();
	private BoardType boardType;

	public ArrayList<SquareInfo> getSquares()
	{
		return squares;
	}

	public int getSquaresSize()
	{
		return squares.size()-1;
	}

	public void addSquare(SquareInfo squareToAdd)
	{
		squares.add(squareToAdd);
	}

	public void setBoardType(BoardType boardType)
	{
		this.boardType = boardType;
	}

	public boolean isWinner()
	{
		return boardType.isWinner();
	}

	public void getEndAction(int posAfterRoll, Player player)
	{
		boardType.endAction(posAfterRoll,getSquaresSize(),player);
	}

	public SquareInfo getApplyAction(int currentPlayerPos)
	{
		return squares.get(currentPlayerPos);
	}
}