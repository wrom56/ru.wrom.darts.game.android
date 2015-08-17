package ru.wrom.darts.game.core.api;


public class GameStats {

	private float score;
	private float average3d;
	private float dartCount;

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public float getAverage3d() {
		return average3d;
	}

	public void setAverage3d(float average3d) {
		this.average3d = average3d;
	}

	public float getDartCount() {
		return dartCount;
	}

	public void setDartCount(float dartCount) {
		this.dartCount = dartCount;
	}
}
