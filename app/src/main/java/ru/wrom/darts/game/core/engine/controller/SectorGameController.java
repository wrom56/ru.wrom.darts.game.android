package ru.wrom.darts.game.core.engine.controller;

import ru.wrom.darts.game.core.engine.model.Attempt;
import ru.wrom.darts.game.core.engine.model.AttemptStatus;
import ru.wrom.darts.game.core.engine.model.PlayerLeg;

public class SectorGameController extends AbstractGameController {

	private String sector;

	public SectorGameController(String sector) {
		this.sector = sector;
	}


	@Override
	public AttemptStatus checkAttempt(Attempt attempt, PlayerLeg playerLeg) {
		if (sector.equals("BULL")) {
			if (attempt.getTotalScore() % 25 != 0 || attempt.getTotalScore() > 150) {
				return AttemptStatus.INVALID;
			}
		} else {
			Integer sectorInt = Integer.valueOf(sector);
			if (attempt.getTotalScore() % sectorInt != 0 || attempt.getTotalScore() > 9 * sectorInt) {
				return AttemptStatus.INVALID;
			}
		}
		return AttemptStatus.VALID;
	}

}
