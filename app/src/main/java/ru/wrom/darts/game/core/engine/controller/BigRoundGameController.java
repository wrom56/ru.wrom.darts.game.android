package ru.wrom.darts.game.core.engine.controller;

import java.util.Arrays;
import java.util.List;

import ru.wrom.darts.game.core.api.AddAttemptResult;
import ru.wrom.darts.game.core.engine.model.Attempt;
import ru.wrom.darts.game.core.engine.model.Game;
import ru.wrom.darts.game.core.engine.model.PlayerGame;

public class BigRoundGameController extends AbstractGameController {

	@Override
	protected AddAttemptResult checkAttempt(Attempt attempt, PlayerGame playerGame) {
		int attemptNumber = playerGame.getAttempts().size() + 1;
		if (attemptNumber < 21) {
			if (attempt.getTotalScore() % attemptNumber != 0 || attempt.getTotalScore() > attemptNumber * 9) {
				return AddAttemptResult.INVALID_ATTEMPT;
			}
		} else {
			if (attempt.getTotalScore() % 25 != 0 || attempt.getTotalScore() > 150) {
				return AddAttemptResult.INVALID_ATTEMPT;
			}
		}
		return AddAttemptResult.ATTEMPT_ADDED;
	}

	@Override
	protected List<String> buildHints(PlayerGame playerGame) {
		int attemptNumber = playerGame.getAttempts().size() + 1;
		if (attemptNumber <= 20) {
			return Arrays.asList("sector " + attemptNumber);
		} else if (attemptNumber == 21) {
			return Arrays.asList("sector bull");
		} else {
			return super.buildHints(playerGame);
		}
	}

	@Override
	protected boolean checkGameOver(Game game) {
		return getCurrentPlayerGame().getAttempts().size() == 21;
	}
}
