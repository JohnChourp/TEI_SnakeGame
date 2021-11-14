package org.example.model;

public class Player {
	private String name;
	private int pos;
	private boolean lostTurn;
	private boolean canPlayAtStart;
	private boolean hasImmunity;
	private int round;
	private boolean winner;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public boolean isLostTurn() {
		return lostTurn;
	}

	public void setLostTurn(boolean lostTurn) {
		this.lostTurn = lostTurn;
	}

	public boolean isCanPlayAtStart() {
		return canPlayAtStart;
	}

	public void setCanPlayAtStart(boolean canPlayAtStart) {
		this.canPlayAtStart = canPlayAtStart;
	}

	public boolean isHasImmunity() {
		return hasImmunity;
	}

	public void setHasImmunity(boolean hasImmunity) {
		this.hasImmunity = hasImmunity;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public void setRound() {
		this.round = round + 1;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}
}