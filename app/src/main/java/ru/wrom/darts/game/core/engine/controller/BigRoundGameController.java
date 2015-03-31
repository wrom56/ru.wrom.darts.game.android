package ru.wrom.darts.game.core.engine.controller;

import ru.wrom.darts.game.core.api.AddAttemptResult;
import ru.wrom.darts.game.core.engine.model.Attempt;
import ru.wrom.darts.game.core.engine.model.PlayerGame;

public class BigRoundGameController extends AbstractGameController {

	@Override
	protected AddAttemptResult checkAttempt(Attempt attempt, PlayerGame playerGame) {
		return null;
	}
}
