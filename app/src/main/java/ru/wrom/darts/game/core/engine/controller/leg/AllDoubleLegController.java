package ru.wrom.darts.game.core.engine.controller.leg;

import java.util.ArrayList;
import java.util.List;

import ru.wrom.darts.game.core.engine.model.Attempt;
import ru.wrom.darts.game.core.engine.model.AttemptStatus;
import ru.wrom.darts.game.core.engine.model.Leg;
import ru.wrom.darts.game.core.engine.model.PlayerLeg;


public class AllDoubleLegController extends AbstractLegController {

	@Override
	public AttemptStatus checkAttempt(Attempt attempt, PlayerLeg playerLeg) {
		if (attempt.getTotalScore() > 3) {
			return AttemptStatus.INVALID;
		} else {
			return AttemptStatus.VALID;
		}
	}

	@Override
	public List<String> getHints(PlayerLeg playerLeg) {
		List<String> hints = new ArrayList<>();
		int totalScore = getTotalScore(playerLeg);
		if (totalScore == 20) {
			hints.add("BULL");
		} else {
			hints.add("D" + ++totalScore);
		}
		return hints;
	}

	@Override
	public boolean canSubmitScore(Attempt attempt, PlayerLeg playerLeg) {
		return true;
	}

	@Override
	public boolean checkLegOver(Leg leg) {
		return getTotalScore(leg.getPlayerLegs().get(0)) == 21;
	}

}
