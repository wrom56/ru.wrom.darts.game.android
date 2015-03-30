package ru.wrom.darts.game.engine.controller;

import ru.wrom.darts.game.engine.api.AddAttemptResult;
import ru.wrom.darts.game.engine.api.Attempt;
import ru.wrom.darts.game.engine.model.PlayerGame;

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
		}
		Integer sectorInt = Integer.valueOf(sector);
		if (attempt.getTotalScore() % sectorInt != 0 || attempt.getTotalScore() > 9 * sectorInt) {
			return AddAttemptResult.INVALID_ATTEMPT;
		}
		return AddAttemptResult.ATTEMPT_ADDED;
	}

}
