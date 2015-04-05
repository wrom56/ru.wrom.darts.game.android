package ru.wrom.darts.game.core.engine;

import java.util.List;

import ru.wrom.darts.game.core.engine.model.Attempt;

public class Util {

	public static int calculateAttemptsTotalScore(List<Attempt> attempts) {
		int totalScore = 0;
		for (Attempt attempt : attempts) {
			totalScore += attempt.getTotalScore();
		}
		return totalScore;
	}

	public static int calculateAttemptsDartCount(List<Attempt> attempts) {
		int result = 0;
		for (Attempt attempt : attempts) {
			result += attempt.getDartCount();
		}
		return result;
	}
}
