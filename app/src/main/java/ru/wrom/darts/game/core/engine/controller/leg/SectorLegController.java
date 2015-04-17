package ru.wrom.darts.game.core.engine.controller.leg;

import ru.wrom.darts.game.core.engine.model.Attempt;
import ru.wrom.darts.game.core.engine.model.AttemptStatus;
import ru.wrom.darts.game.core.engine.model.PlayerLeg;

public class SectorLegController extends AbstractLegController {

	private String sector;

	public SectorLegController(String sector) {
		this.sector = sector;
	}

	@Override
	public AttemptStatus checkAttempt(Attempt attempt, PlayerLeg currentPlayerLeg) {
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
