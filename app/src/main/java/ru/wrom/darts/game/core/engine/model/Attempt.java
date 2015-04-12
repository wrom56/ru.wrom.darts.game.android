package ru.wrom.darts.game.core.engine.model;


import ru.wrom.darts.game.core.api.IAttempt;

public class Attempt implements IAttempt {

	private Integer totalScore;
	private Integer dartCount;
	private PlayerLeg playerLeg;

	public Attempt(Integer totalScore, PlayerLeg playerLeg) {
		this(totalScore, null, playerLeg);
	}

	public Attempt(Integer totalScore, Integer dartCount, PlayerLeg playerLeg) {
		this.totalScore = totalScore;
		this.dartCount = dartCount;
		this.playerLeg = playerLeg;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public Integer getDartCount() {
		return dartCount;
	}

	public void setDartCount(Integer dartCount) {
		this.dartCount = dartCount;
	}

	public PlayerLeg getPlayerLeg() {
		return playerLeg;
	}

	public void setPlayerLeg(PlayerLeg playerLeg) {
		this.playerLeg = playerLeg;
	}
}
