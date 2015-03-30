package ru.wrom.darts.game.engine.api;

import java.util.ArrayList;
import java.util.List;

public class PlayerStatus {

	private Player player;
	private List<Attempt> attempts;
	private Integer totalScore;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Attempt> getAttempts() {
		if (attempts == null) {
			attempts = new ArrayList<>();
		}
		return attempts;
	}

	public void setAttempts(List<Attempt> attempts) {
		this.attempts = attempts;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
}
