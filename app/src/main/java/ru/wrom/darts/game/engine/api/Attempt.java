package ru.wrom.darts.game.engine.api;


import java.util.ArrayList;
import java.util.List;

public class Attempt {
	private Integer totalScore;
	private List<String> dartScores;
	private Integer dartCount;

	public Attempt(Integer totalScore) {
		this.totalScore = totalScore;
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

	public List<String> getDartScores() {
		if (dartScores == null) {
			dartScores = new ArrayList<>();
		}
		return dartScores;
	}

	public void setDartScores(List<String> dartScores) {
		this.dartScores = dartScores;
	}
}
