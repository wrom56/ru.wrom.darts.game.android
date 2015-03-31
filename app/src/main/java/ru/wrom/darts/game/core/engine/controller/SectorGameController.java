package ru.wrom.darts.game.core.engine.controller;

import ru.wrom.darts.game.core.api.AddAttemptResult;
import ru.wrom.darts.game.core.engine.model.Attempt;
import ru.wrom.darts.game.core.engine.model.PlayerGame;

public class SectorGameController extends AbstractGameController {

	private String sector;

	public SectorGameController(String sector) {
		this.sector = sector;
	}


	@Override
	public AddAttemptResult checkAttempt(Attempt attempt, PlayerGame playerGame) {
		if (sector.equals("BULL")) {
			if (attempt.getTotalScore() % 25 != 0 || attempt.getTotalScore() > 150) {
				return AddAttemptResult.INVALID_ATTEMPT;
			}
		} else {
			Integer sectorInt = Integer.valueOf(sector);
			if (attempt.getTotalScore() % sectorInt != 0 || attempt.getTotalScore() > 9 * sectorInt) {
				return AddAttemptResult.INVALID_ATTEMPT;
			}
		}
		return AddAttemptResult.ATTEMPT_ADDED;
	}

}
