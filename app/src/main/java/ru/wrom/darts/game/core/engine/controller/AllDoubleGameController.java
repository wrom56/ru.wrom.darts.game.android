package ru.wrom.darts.game.core.engine.controller;

import java.util.ArrayList;
import java.util.List;

import ru.wrom.darts.game.core.api.AddAttemptResult;
import ru.wrom.darts.game.core.engine.model.Attempt;
import ru.wrom.darts.game.core.engine.model.Game;
import ru.wrom.darts.game.core.engine.model.PlayerGame;


public class AllDoubleGameController extends AbstractGameController {

	@Override
	public AddAttemptResult checkAttempt(Attempt attempt, PlayerGame playerGame) {
		if (attempt.getTotalScore() > 3) {
			return AddAttemptResult.INVALID_ATTEMPT;
		} else {
			return AddAttemptResult.ATTEMPT_ADDED;
		}
	}

	@Override
	public List<String> buildHints(PlayerGame playerGame) {
		List<String> hints = new ArrayList<>();
		int totalScore = calculateTotalScore(playerGame);
		if (totalScore == 20) {
			hints.add("BULL");
		} else {
			hints.add("D" + ++totalScore);
		}
		return hints;
	}

	@Override
	protected boolean checkGameOver(Game game) {
		return calculateTotalScore(getCurrentPlayerGame()) == 21;
	}
}
