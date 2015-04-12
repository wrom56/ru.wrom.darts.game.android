package ru.wrom.darts.game.core.engine.controller.leg;

import java.util.Collections;
import java.util.List;

import ru.wrom.darts.game.core.engine.Util;
import ru.wrom.darts.game.core.engine.model.Attempt;
import ru.wrom.darts.game.core.engine.model.AttemptStatus;
import ru.wrom.darts.game.core.engine.model.PlayerLeg;

public abstract class AbstractLegController {

	public int getTotalScore(PlayerLeg playerLeg) {
		return Util.calculateAttemptsTotalScore(playerLeg.getAttempts());
	}

	public int getDartCount(PlayerLeg playerLeg) {
		return Util.calculateAttemptsDartCount(playerLeg.getAttempts());
	}

	public float getAverageAttemptScore(PlayerLeg playerLeg) {
		return 0;
	}

	public List<String> getHints(PlayerLeg playerLeg) {
		return Collections.emptyList();
	}

	public AttemptStatus checkAttempt(Attempt attempt) {
		if (attempt.getTotalScore() != null && attempt.getTotalScore() > 180) {
			return AttemptStatus.INVALID;
		}
		if (attempt.getDartCount() != null && (attempt.getDartCount() > 3 || attempt.getDartCount() < 1)) {
			return AttemptStatus.INVALID;
		}
		return AttemptStatus.VALID;
	}

	public boolean isCanSubmitScore(Attempt attempt) {
		return false;
	}
}
