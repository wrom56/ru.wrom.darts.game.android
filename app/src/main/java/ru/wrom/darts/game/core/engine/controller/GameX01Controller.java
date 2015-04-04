package ru.wrom.darts.game.core.engine.controller;

import ru.wrom.darts.game.core.api.AddAttemptResult;
import ru.wrom.darts.game.core.engine.Util;
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
		int totalScore = calculateLegScore(playerGame);
		if (totalScore == attempt.getTotalScore()) {
			if (attempt.getDartCount() == null) {
				return AddAttemptResult.NEED_DART_COUNT;
			}
			if (attempt.getDartCount() == 0) {
				return AddAttemptResult.INVALID_DART_COUNT;
			}
			return AddAttemptResult.GAME_OVER;
		}

		if (totalScore - 1 <= attempt.getTotalScore()) {
			return AddAttemptResult.INVALID_ATTEMPT;
		}

		return AddAttemptResult.ATTEMPT_ADDED;
	}


	@Override
	protected int calculateLegScore(PlayerGame playerGame) {
		return startScore - Util.calculateAttemptsTotalScore(playerGame.getAttempts());
	}

	@Override
	protected boolean checkGameOver(Game game) {
		return calculateLegScore(getCurrentPlayerGame()) == 0;
	}

	@Override
	protected boolean isCanSubmitScore(int totalScore, PlayerGame playerGame) {
		return totalScore * 10 >= calculateLegScore(playerGame);
	}
}
