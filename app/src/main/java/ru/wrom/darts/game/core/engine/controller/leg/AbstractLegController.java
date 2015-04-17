package ru.wrom.darts.game.core.engine.controller.leg;

import java.util.Collections;
import java.util.List;

import ru.wrom.darts.game.core.api.Player;
import ru.wrom.darts.game.core.engine.Util;
import ru.wrom.darts.game.core.engine.model.Attempt;
import ru.wrom.darts.game.core.engine.model.AttemptStatus;
import ru.wrom.darts.game.core.engine.model.Leg;
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

	public AttemptStatus checkAttempt(Attempt attempt, PlayerLeg currentPlayerLeg) {
		if (attempt.getTotalScore() != null && attempt.getTotalScore() > 180) {
			return AttemptStatus.INVALID;
		}
		if (attempt.getDartCount() != null && (attempt.getDartCount() > 3 || attempt.getDartCount() < 1)) {
			return AttemptStatus.INVALID;
		}
		return AttemptStatus.VALID;
	}

	public boolean canSubmitScore(Attempt attempt) {
		return true;
	}

	public int getMinCheckoutDartCount(Attempt attempt) {
		return 3;
	}

	public boolean checkLegOver(Leg leg) {
		return leg.getPlayerLegs().get(0).getAttempts().size() == 10;
	}

	public Player getWinner(Leg leg) {
		return leg.getPlayerLegs().get(0).getPlayerSettings().getPlayer();
	}


}
