package ru.wrom.darts.game.engine.controller;

import ru.wrom.darts.game.engine.api.AddAttemptResult;
import ru.wrom.darts.game.engine.api.Attempt;
import ru.wrom.darts.game.engine.model.PlayerGame;

public class GameX01Controller extends AbstractGameController {

	private int startScore;

	public GameX01Controller(int startScore) {
		this.startScore = startScore;
	}

	@Override
	protected AddAttemptResult checkAttempt(Attempt attempt, PlayerGame playerGame) {
		int totalScore = getTotalScore(playerGame);
		if (totalScore == attempt.getTotalScore()) {
			if (attempt.getDartCount() == null) {
				return AddAttemptResult.NEED_DART_COUNT;
			}
			if (attempt.getDartCount() == 0) {
				return AddAttemptResult.INVALID_DART_COUNT;
			}
			return AddAttemptResult.GAME_OVER;
		}

		if (totalScore < attempt.getTotalScore()) {
			return AddAttemptResult.INVALID_ATTEMPT;
		}

		return AddAttemptResult.ATTEMPT_ADDED;
	}

	@Override
	protected int getTotalScore(PlayerGame playerGame) {
		return startScore - super.getTotalScore(playerGame);
	}
}
