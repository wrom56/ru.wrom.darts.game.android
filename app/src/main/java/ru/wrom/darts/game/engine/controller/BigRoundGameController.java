package ru.wrom.darts.game.engine.controller;

import ru.wrom.darts.game.engine.api.AddAttemptResult;
import ru.wrom.darts.game.engine.api.Attempt;
import ru.wrom.darts.game.engine.model.PlayerGame;

public class BigRoundGameController extends AbstractGameController {

	@Override
	protected AddAttemptResult checkAttempt(Attempt attempt, PlayerGame playerGame) {
		return null;
	}
}
