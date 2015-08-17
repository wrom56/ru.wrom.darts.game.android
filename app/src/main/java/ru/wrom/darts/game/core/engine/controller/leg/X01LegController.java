package ru.wrom.darts.game.core.engine.controller.leg;

import java.util.Arrays;
import java.util.List;

import ru.wrom.darts.game.core.engine.Util;
import ru.wrom.darts.game.core.engine.model.Attempt;
import ru.wrom.darts.game.core.engine.model.AttemptStatus;
import ru.wrom.darts.game.core.engine.model.Leg;
import ru.wrom.darts.game.core.engine.model.PlayerLeg;


public class X01LegController extends AbstractLegController {

	private List<Integer> invalidCheckouts = Arrays.asList(169, 168, 166, 165, 163, 162);
	private List<Integer> twoDartsCheckouts = Arrays.asList(100, 101, 104, 107, 110);

	private int startScore;

	public X01LegController(int startScore) {
		this.startScore = startScore;
	}

	@Override
	public boolean checkLegOver(Leg leg) {
		return getTotalScore(leg.getPlayerLegs().get(0)) == 0;
	}

	@Override
	public int getTotalScore(PlayerLeg playerLeg) {
		return startScore - Util.calculateAttemptsTotalScore(playerLeg.getAttempts());
	}

	@Override
	public int getMinCheckoutDartCount(Attempt attempt) {
		int score = attempt.getTotalScore();
		if (score == 50 || (score <= 40 && score % 2 == 0)) {
			return 1;
		}
		if (score <= 98 || (twoDartsCheckouts.contains(score))) {
			return 2;
		}
		return 3;
	}

	@Override
	public boolean canSubmitScore(Attempt attempt, PlayerLeg playerLeg) {
		return attempt.getTotalScore() * 10 > getTotalScore(playerLeg);
	}

	@Override
	public AttemptStatus checkAttempt(Attempt attempt, PlayerLeg playerLeg) {
		int legScore = getTotalScore(playerLeg);

		if (attempt.getTotalScore() == legScore && invalidCheckouts.contains(legScore)) {
			return AttemptStatus.INVALID;
		}

		if (legScore == attempt.getTotalScore()) {
			return AttemptStatus.CHECKOUT;
		}

		if (legScore - 1 <= attempt.getTotalScore()) {
			return AttemptStatus.INVALID;
		}

		return AttemptStatus.VALID;
	}

	@Override
	public boolean isDartCountMainMark() {
		return false;
	}
}
