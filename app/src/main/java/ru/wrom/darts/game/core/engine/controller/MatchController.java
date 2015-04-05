package ru.wrom.darts.game.core.engine.controller;

import java.util.List;

import ru.wrom.darts.game.core.api.IMatchController;
import ru.wrom.darts.game.core.api.IPlayerMatchStatus;
import ru.wrom.darts.game.core.api.Player;
import ru.wrom.darts.game.core.api.PlayerSettings;
import ru.wrom.darts.game.core.engine.model.Match;

public class MatchController implements IMatchController {

	private Match match = new Match();

	public MatchController(Match match) {
		this.match = match;
	}

	@Override
	public List<PlayerSettings> getPlayerSettingsList() {
		return match.getPlayerSettingsList();
	}

	@Override
	public IPlayerMatchStatus getPlayerMatchStatus(Player player) {
		return null;
	}




	/*
	@Override
	public boolean isCanSubmitScore(int totalScore) {
		return (totalScore >= 19 || isCanSubmitScore(totalScore, getCurrentPlayerGame())) && (checkAttempt(new Attempt(totalScore), getCurrentPlayerGame()) != AttemptStatus.INVALID);
	}

	protected boolean isCanSubmitScore(int totalScore, PlayerLeg playerLeg) {
		return false;
	}

	protected Game game;


	@Override
	public Player getCurrentPlayer() {
		return getCurrentPlayerGame().getPlayer();
	}

	@Override
	public List<IPlayerLegStatus> getPlayerStatuses() {
		List<IPlayerLegStatus> playerStatuses = new ArrayList<>();
		for (PlayerLeg playerLeg : game.getPlayerLegs()) {
			playerStatuses.add(buildPlayerStatus(playerLeg));
		}
		return playerStatuses;
	}

	@Override
	public void cancelLastAttempt() {
		List<Attempt> attempts = getPreviousPlayerGame().getAttempts();
		if (attempts.isEmpty()) {
			return;
		}
		attempts.remove(attempts.size() - 1);
	}

	@Override
	public void init(GameSettings gameSettings) {
		game = new Game();
		game.setStartDate(new Date());
		for (PlayerSettings playerSettings : gameSettings.getPlayersSettings()) {
			PlayerLeg playerLeg = new PlayerLeg();
			playerLeg.setPlayer(playerSettings.getPlayer());
			playerLeg.setDart(playerSettings.getDart());
			game.addPlayerGame(playerLeg);
		}
	}

	@Override
	public LegStatus addAttempt(int totalScore, Integer dartCount) {
		return addAttempt(new Attempt(totalScore, dartCount));
	}


	@Override
	public IPlayerLegStatus getPlayerLegStatus(Player player) {
		return buildPlayerStatus(getCurrentPlayerGame());
	}


	protected LegStatus addAttempt(Attempt attempt) {
		if (!checkAttempt(attempt)) {
			return LegStatus.INVALID_ATTEMPT;
		}
		PlayerLeg currentPlayerLeg = getCurrentPlayerGame();
		AttemptStatus attemptStatus = checkAttempt(attempt, currentPlayerLeg);

		if (attemptStatus == AttemptStatus.CHECKOUT) {
			int minCheckoutDartCount = getMinCheckoutDartCount(attempt);
			if (attempt.getDartCount() != null) {
				if (attempt.getDartCount() < minCheckoutDartCount) {
					return LegStatus.INVALID_ATTEMPT;
				} else {
					return addAttempt(attempt, currentPlayerLeg);
				}
			} else {
				if (minCheckoutDartCount == 3) {
					attempt.setDartCount(3);
					return addAttempt(attempt, currentPlayerLeg);
				} else {
					return minCheckoutDartCount == 1 ? LegStatus.NEED_DART_COUNT_1 : LegStatus.NEED_DART_COUNT_2;
				}
			}
		}

		if (attemptStatus == AttemptStatus.VALID) {
			attempt.setDartCount(3);
			return addAttempt(attempt, currentPlayerLeg);
		}

		return LegStatus.INVALID_ATTEMPT;
	}


	protected int getMinCheckoutDartCount(Attempt totalScore) {
		return 3;
	}


	private IPlayerLegStatus buildPlayerStatus(final PlayerLeg playerLeg) {
		return new IPlayerLegStatus() {

			@Override
			public List<? extends IAttempt> getAttempts() {
				return playerLeg.getAttempts();
			}

			@Override
			public int getTotalScore() {
				return calculateLegScore(playerLeg);
			}

			@Override
			public int getDartCount() {
				return calculateDartCount(playerLeg);
			}

			@Override
			public float getAverageAttemptScore() {
				if (getDartCount() == 0) {
					return 0;
				}
				return Util.calculateAttemptsTotalScore(playerLeg.getAttempts()) * 3f / getDartCount();
			}

			@Override
			public List<String> getHints() {
				return buildHints(playerLeg);
			}
		};

	}

	protected int calculateLegScore(PlayerLeg playerLeg) {
		return Util.calculateAttemptsTotalScore(playerLeg.getAttempts());
	}

	protected int calculateDartCount(PlayerLeg playerLeg) {
		return Util.calculateAttemptsDartCount(playerLeg.getAttempts());
	}

	private LegStatus addAttempt(Attempt attempt, PlayerLeg playerLeg) {
		playerLeg.getAttempts().add(attempt);
		return checkGameOver(game) ? LegStatus.LEG_OVER : LegStatus.ATTEMPT_ADDED;
	}

	protected boolean checkAttempt(Attempt attempt) {
		if (attempt.getTotalScore() != null && attempt.getTotalScore() > 180) {
			return false;
		}
		if (attempt.getDartCount() != null && (attempt.getDartCount() > 3 || attempt.getDartCount() < 1)) {
			return false;
		}
		return true;
	}

	protected abstract AttemptStatus checkAttempt(Attempt attempt, PlayerLeg playerLeg);

	protected List<String> buildHints(PlayerLeg playerLeg) {
		return Collections.emptyList();
	}

	protected void processAttempt(Attempt attempt) {
		if (attempt.getDartCount() == null) {
			attempt.setDartCount(3);
		}
	}


	protected PlayerLeg getCurrentPlayerGame() {
		return game.getPlayerLegs().get(0);
	}

	protected PlayerLeg getPreviousPlayerGame() {
		return game.getPlayerLegs().get(0);
	}


	protected boolean checkGameOver(Game game) {
		return getCurrentPlayerGame().getAttempts().size() == 10;
	}

	*/
}
