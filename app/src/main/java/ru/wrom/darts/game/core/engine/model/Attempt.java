package ru.wrom.darts.game.core.engine.model;


import ru.wrom.darts.game.core.api.IAttempt;

public class Attempt implements IAttempt {

	private Integer totalScore;
	private Integer dartCount;

	public Attempt(Integer totalScore) {
		this(totalScore, null);
	}

	public Attempt(Integer totalScore, Integer dartCount) {
		this.totalScore = totalScore;
		this.dartCount = dartCount;
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

}
