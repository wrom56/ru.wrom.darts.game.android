package ru.wrom.darts.game.core.engine.controller;

import java.util.Arrays;
import java.util.List;

import ru.wrom.darts.game.core.engine.model.Attempt;
import ru.wrom.darts.game.core.engine.model.AttemptStatus;
import ru.wrom.darts.game.core.engine.model.Game;
import ru.wrom.darts.game.core.engine.model.PlayerLeg;

public class BigRoundGameController extends AbstractGameController {

	@Override
	protected AttemptStatus checkAttempt(Attempt attempt, PlayerLeg playerLeg) {
		int attemptNumber = playerLeg.getAttempts().size() + 1;
		if (attemptNumber < 21) {
			if (attempt.getTotalScore() % attemptNumber != 0 || attempt.getTotalScore() > attemptNumber * 9) {
				return AttemptStatus.INVALID;
			}
		} else {
			if (attempt.getTotalScore() % 25 != 0 || attempt.getTotalScore() > 150) {
				return AttemptStatus.INVALID;
			}
		}
		return AttemptStatus.VALID;
	}

	@Override
	protected List<String> buildHints(PlayerLeg playerLeg) {
		int attemptNumber = playerLeg.getAttempts().size() + 1;
		if (attemptNumber <= 20) {
			return Arrays.asList("sector " + attemptNumber);
		} else if (attemptNumber == 21) {
			return Arrays.asList("sector bull");
		} else {
			return super.buildHints(playerLeg);
		}
	}

	@Override
	protected boolean checkGameOver(Game game) {
		return getCurrentPlayerGame().getAttempts().size() == 21;
	}

	@Override
	protected boolean isCanSubmitScore(int totalScore, PlayerLeg playerLeg) {
		return true;
	}
}
