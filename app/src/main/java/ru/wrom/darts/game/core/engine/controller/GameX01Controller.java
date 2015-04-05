package ru.wrom.darts.game.core.engine.controller;

import java.util.Arrays;
import java.util.List;

import ru.wrom.darts.game.core.engine.Util;
import ru.wrom.darts.game.core.engine.model.Attempt;
import ru.wrom.darts.game.core.engine.model.AttemptStatus;
import ru.wrom.darts.game.core.engine.model.Game;
import ru.wrom.darts.game.core.engine.model.PlayerLeg;


public class GameX01Controller extends AbstractGameController {

	private List<Integer> invalidCheckouts = Arrays.asList(169, 168, 166, 165, 163, 162);
	private List<Integer> twoDartsCheckouts = Arrays.asList(100, 101, 104, 107, 110);

	private int startScore;

	public GameX01Controller(int startScore) {
		this.startScore = startScore;
	}

	@Override
	protected AttemptStatus checkAttempt(Attempt attempt, PlayerLeg playerLeg) {
		int legScore = calculateLegScore(playerLeg);

		if (invalidCheckouts.contains(legScore)) {
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
	protected int calculateLegScore(PlayerLeg playerLeg) {
		return startScore - Util.calculateAttemptsTotalScore(playerLeg.getAttempts());
	}

	@Override
	protected boolean checkGameOver(Game game) {
		return calculateLegScore(getCurrentPlayerGame()) == 0;
	}

	@Override
	protected boolean isCanSubmitScore(int totalScore, PlayerLeg playerLeg) {
		return totalScore * 10 > calculateLegScore(playerLeg);
	}
}
