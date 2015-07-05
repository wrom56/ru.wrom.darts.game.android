package ru.wrom.darts.game.core.engine.controller;

import java.util.List;

import ru.wrom.darts.game.core.api.AddAttemptResult;
import ru.wrom.darts.game.core.api.IAttempt;
import ru.wrom.darts.game.core.api.IMatchController;
import ru.wrom.darts.game.core.api.IPlayerMatchStatus;
import ru.wrom.darts.game.core.api.Player;
import ru.wrom.darts.game.core.api.PlayerLegStatus;
import ru.wrom.darts.game.core.api.PlayerSettings;
import ru.wrom.darts.game.core.engine.controller.leg.AbstractLegController;
import ru.wrom.darts.game.core.engine.controller.leg.LegControllerFactory;
import ru.wrom.darts.game.core.engine.model.Attempt;
import ru.wrom.darts.game.core.engine.model.AttemptStatus;
import ru.wrom.darts.game.core.engine.model.Leg;
import ru.wrom.darts.game.core.engine.model.Match;
import ru.wrom.darts.game.core.engine.model.PlayerLeg;
import ru.wrom.darts.game.core.engine.model.Set;

public class MatchController implements IMatchController {

	private Match match = new Match();
	private Leg currentLeg;
	private AbstractLegController legController;

	public MatchController(Match match) {
		this.match = match;
		match.newSet();
		legController = LegControllerFactory.getController(match.getGameType());
	}

	@Override
	public void newLeg() {
		currentLeg = new Leg(getCurrentSet(), match.getPlayerSettingsList());
	}

	@Override
	public List<PlayerSettings> getPlayerSettingsList() {
		return match.getPlayerSettingsList();
	}

	@Override
	public IPlayerMatchStatus getPlayerMatchStatus(Player player) {
		return new IPlayerMatchStatus() {
			@Override
			public int setWin() {
				return match.getSets().size() - 1;
			}

			@Override
			public int legWin() {
				return getCurrentSet().getLegs().size();
			}
		};
	}

	@Override
	public Player getCurrentPlayer() {
		return match.getPlayerSettingsList().get(0).getPlayer();
	}

	@Override
	public PlayerLegStatus getPlayerLegStatus(final Player player) {
		final PlayerLeg playerLeg = getCurrentPlayerLeg();
		return new PlayerLegStatus() {
			@Override
			public List<? extends IAttempt> getAttempts() {
				return playerLeg.getAttempts();
			}

			@Override
			public int getScore() {
				return legController.getTotalScore(playerLeg);
			}

			@Override
			public int getDartCount() {
				return legController.getDartCount(playerLeg);
			}

			@Override
			public float getAverageAttemptScore() {
				return legController.getAverageAttemptScore(playerLeg);
			}

			@Override
			public List<String> getHints() {
				return legController.getHints(playerLeg);
			}
		};
	}

	@Override
	public AddAttemptResult addAttempt(int totalScore, Integer dartCount) {
		return addAttempt(buildAttempt(totalScore, dartCount));
	}

	@Override
	public boolean canSubmitScore(int totalScore) {
		Attempt attempt = buildAttempt(totalScore, null);
		return (totalScore >= 19 || legController.canSubmitScore(attempt, getCurrentPlayerLeg())) && commonCheckAttempt(attempt) && legController.checkAttempt(attempt, getCurrentPlayerLeg()) != AttemptStatus.INVALID;
	}

	@Override
	public void cancelLastAttempt() {
		List<Attempt> attempts = getCurrentPlayerLeg().getAttempts();
		if (attempts.isEmpty()) {
			return;
		}
		attempts.remove(attempts.size() - 1);
	}

	private boolean commonCheckAttempt(Attempt attempt) {
		if (attempt.getTotalScore() != null && attempt.getTotalScore() > 180) {
			return false;
		}
		if (attempt.getDartCount() != null && (attempt.getDartCount() > 3 || attempt.getDartCount() < 1)) {
			return false;
		}
		return true;
	}

	private Attempt buildAttempt(int totalScore, Integer dartCount) {
		return new Attempt(totalScore, dartCount);
	}

	private AddAttemptResult addAttempt(Attempt attempt) {
		if (!commonCheckAttempt(attempt)) {
			return AddAttemptResult.INVALID_ATTEMPT;
		}

		AttemptStatus attemptStatus = legController.checkAttempt(attempt, getCurrentPlayerLeg());

		if (attemptStatus == AttemptStatus.CHECKOUT) {
			int minCheckoutDartCount = legController.getMinCheckoutDartCount(attempt);
			if (attempt.getDartCount() != null) {
				if (attempt.getDartCount() < minCheckoutDartCount) {
					return AddAttemptResult.INVALID_ATTEMPT;
				} else {
					return submitAttempt(attempt);
				}
			} else {
				if (minCheckoutDartCount == 3) {
					attempt.setDartCount(3);
					return submitAttempt(attempt);
				} else {
					return minCheckoutDartCount == 1 ? AddAttemptResult.NEED_DART_COUNT_1 : AddAttemptResult.NEED_DART_COUNT_2;
				}
			}
		} else if (attemptStatus == AttemptStatus.VALID) {
			attempt.setDartCount(3);
			return submitAttempt(attempt);
		}

		return AddAttemptResult.INVALID_ATTEMPT;
	}

	@Override
	public void submitLeg() {
		getCurrentSet().getLegs().add(currentLeg);
		if (checkSetOver()) {
			if (!checkMatchOver()) {
				match.newSet();
			}
		}
	}

	@Override
	public boolean checkLegOver() {
		return legController.checkLegOver(getCurrentLeg());
	}

	@Override
	public boolean checkMatchOver() {
		return match.getSets().size() == match.getMaxSetCount();
	}

	private AddAttemptResult submitAttempt(Attempt attempt) {
		getCurrentPlayerLeg().addAttempt(attempt);
		return AddAttemptResult.ATTEMPT_ADDED;
	}

	/*private AddAttemptResult submitAttempt(Attempt attempt) {
		PlayerLeg currentPlayerLeg = getCurrentPlayerLeg();
		currentPlayerLeg.addAttempt(attempt);
		if (!legController.checkLegOver(getCurrentLeg())) {
			return AddAttemptResult.ATTEMPT_ADDED;
		}

		if (!checkSetOver()) {
			return AddAttemptResult.LEG_OVER;
		}

		if (!checkMatchOver()) {
			return AddAttemptResult.SET_OVER;
		}

		return AddAttemptResult.MATCH_OVER;
	}*/


	private boolean checkSetOver() {
		return getCurrentSet().getLegs().size() == match.getMaxLegCount();
	}

	private PlayerLeg getCurrentPlayerLeg() {
		return getCurrentLeg().getPlayerLegs().get(0);
	}

	private Set getCurrentSet() {
		return match.getSets().get(match.getSets().size() - 1);
	}

	private Leg getCurrentLeg() {
		return currentLeg;
	}

}
