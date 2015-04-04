package ru.wrom.darts.game.core.engine.controller;

import java.util.ArrayList;
import java.util.List;

import ru.wrom.darts.game.core.engine.model.Attempt;
import ru.wrom.darts.game.core.engine.model.AttemptStatus;
import ru.wrom.darts.game.core.engine.model.Game;
import ru.wrom.darts.game.core.engine.model.PlayerGame;


public class AllDoubleGameController extends AbstractGameController {

	@Override
	public AttemptStatus checkAttempt(Attempt attempt, PlayerGame playerGame) {
		if (attempt.getTotalScore() > 3) {
			return AttemptStatus.INVALID;
		} else {
			return AttemptStatus.VALID;
		}
	}

	@Override
	public List<String> buildHints(PlayerGame playerGame) {
		List<String> hints = new ArrayList<>();
		int totalScore = calculateLegScore(playerGame);
		if (totalScore == 20) {
			hints.add("BULL");
		} else {
			hints.add("D" + ++totalScore);
		}
		return hints;
	}

	@Override
	protected boolean isCanSubmitScore(int totalScore, PlayerGame playerGame) {
		return true;
	}

	@Override
	protected boolean checkGameOver(Game game) {
		return calculateLegScore(getCurrentPlayerGame()) == 21;
	}
}
