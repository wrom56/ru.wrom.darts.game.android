package ru.wrom.darts.game.core.engine.controller;

import ru.wrom.darts.game.core.api.AddAttemptResult;
import ru.wrom.darts.game.core.engine.model.Attempt;
import ru.wrom.darts.game.core.engine.model.Game;
import ru.wrom.darts.game.core.engine.model.PlayerGame;


public class GameX01Controller extends AbstractGameController {

	private int startScore;

	public GameX01Controller(int startScore) {
		this.startScore = startScore;
	}

	@Override
	protected AddAttemptResult checkAttempt(Attempt attempt, PlayerGame playerGame) {
		int totalScore = calculateTotalScore(playerGame);
		if (totalScore == attempt.getTotalScore()) {
			if (attempt.getDartCount() == null) {
				return AddAttemptResult.NEED_DART_COUNT;
			}
			if (attempt.getDartCount() == 0) {
				return AddAttemptResult.INVALID_DART_COUNT;
			}
			return AddAttemptResult.GAME_OVER;
		}

		if (totalScore < attempt.getTotalScore() + 1) {
			return AddAttemptResult.INVALID_ATTEMPT;
		}

		return AddAttemptResult.ATTEMPT_ADDED;
	}

	@Override
	protected int calculateTotalScore(PlayerGame playerGame) {
		return startScore - super.calculateTotalScore(playerGame);
	}

	@Override
	protected boolean checkGameOver(Game game) {
		return calculateTotalScore(getCurrentPlayerGame()) == 0;
	}
}
