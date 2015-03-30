package ru.wrom.darts.game.engine.controller;

import java.util.ArrayList;
import java.util.List;

import ru.wrom.darts.game.engine.api.AddAttemptResult;
import ru.wrom.darts.game.engine.api.Attempt;
import ru.wrom.darts.game.engine.model.PlayerGame;

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
	public List<String> getHints(PlayerGame playerGame) {
		List<String> hints = new ArrayList<>();
		int totalScore = getTotalScore(playerGame);
		if (totalScore == 20) {
			hints.add("BULL");
		} else {
			hints.add("D" + ++totalScore);
		}
		return hints;
	}
}
