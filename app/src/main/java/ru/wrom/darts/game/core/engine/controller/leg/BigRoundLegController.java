package ru.wrom.darts.game.core.engine.controller.leg;

import java.util.Arrays;
import java.util.List;

import ru.wrom.darts.game.core.engine.model.Attempt;
import ru.wrom.darts.game.core.engine.model.AttemptStatus;
import ru.wrom.darts.game.core.engine.model.Leg;
import ru.wrom.darts.game.core.engine.model.PlayerLeg;

public class BigRoundLegController extends AbstractLegController {

	@Override
	public AttemptStatus checkAttempt(Attempt attempt, PlayerLeg playerLeg) {
		int attemptNumber = playerLeg.getAttempts().size() + 1;
		if (attemptNumber < 21) {
			if (attempt.getTotalScore() % attemptNumber != 0 || attempt.getTotalScore() > attemptNumber * 9) {
				return AttemptStatus.INVALID;
			}
		} else {
			if (attempt.getTotalScore() % 25 != 0 || attempt.getTotalScore() > 150) {
				return AttemptStatus.INVALID;
			}
		}
		return AttemptStatus.VALID;
	}

	@Override
	public List<String> getHints(PlayerLeg playerLeg) {
		int attemptNumber = playerLeg.getAttempts().size() + 1;
		if (attemptNumber <= 20) {
			return Arrays.asList("sector " + attemptNumber);
		} else if (attemptNumber == 21) {
			return Arrays.asList("sector bull");
		} else {
			return super.getHints(playerLeg);
		}
	}

	@Override
	public boolean checkLegOver(Leg leg) {
		return leg.getPlayerLegs().get(0).getAttempts().size() == 21;
	}

	@Override
	public boolean canSubmitScore(Attempt attempt, PlayerLeg playerLeg) {
		return true;
	}
}
