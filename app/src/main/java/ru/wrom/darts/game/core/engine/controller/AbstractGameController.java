package ru.wrom.darts.game.core.engine.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import ru.wrom.darts.game.core.api.GameSettings;
import ru.wrom.darts.game.core.api.IAttempt;
import ru.wrom.darts.game.core.api.IGameController;
import ru.wrom.darts.game.core.api.IPlayerLegStatus;
import ru.wrom.darts.game.core.api.LegStatus;
import ru.wrom.darts.game.core.api.Player;
import ru.wrom.darts.game.core.api.PlayerSettings;
import ru.wrom.darts.game.core.engine.Util;
import ru.wrom.darts.game.core.engine.model.Attempt;
import ru.wrom.darts.game.core.engine.model.AttemptStatus;
import ru.wrom.darts.game.core.engine.model.Game;
import ru.wrom.darts.game.core.engine.model.PlayerGame;

public abstract class AbstractGameController implements IGameController {


	@Override
	public boolean isCanSubmitScore(int totalScore) {
		return (totalScore >= 19 || isCanSubmitScore(totalScore, getCurrentPlayerGame())) && (checkAttempt(new Attempt(totalScore), getCurrentPlayerGame()) != AttemptStatus.INVALID);
	}

	protected boolean isCanSubmitScore(int totalScore, PlayerGame playerGame) {
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
		for (PlayerGame playerGame : game.getPlayerGames()) {
			playerStatuses.add(buildPlayerStatus(playerGame));
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
		game.setGameType(gameSettings.getGameType());
		for (PlayerSettings playerSettings : gameSettings.getPlayersSettings()) {
			PlayerGame playerGame = new PlayerGame();
			playerGame.setPlayer(playerSettings.getPlayer());
			playerGame.setDart(playerSettings.getDart());
			game.addPlayerGame(playerGame);
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
		PlayerGame currentPlayerGame = getCurrentPlayerGame();
		AttemptStatus attemptStatus = checkAttempt(attempt, currentPlayerGame);

		if (attemptStatus == AttemptStatus.CHECKOUT) {
			int minCheckoutDartCount = getMinCheckoutDartCount(attempt);
			if (attempt.getDartCount() != null) {
				if (attempt.getDartCount() < minCheckoutDartCount) {
					return LegStatus.INVALID_ATTEMPT;
				} else {
					return addAttempt(attempt, currentPlayerGame);
				}
			} else {
				if (minCheckoutDartCount == 3) {
					attempt.setDartCount(3);
					return addAttempt(attempt, currentPlayerGame);
				} else {
					return minCheckoutDartCount == 1 ? LegStatus.NEED_DART_COUNT_1 : LegStatus.NEED_DART_COUNT_2;
				}
			}
		}

		if (attemptStatus == AttemptStatus.VALID) {
			attempt.setDartCount(3);
			return addAttempt(attempt, currentPlayerGame);
		}

		return LegStatus.INVALID_ATTEMPT;
	}


	protected int getMinCheckoutDartCount(Attempt totalScore) {
		return 3;
	}


	private IPlayerLegStatus buildPlayerStatus(final PlayerGame playerGame) {
		return new IPlayerLegStatus() {

			@Override
			public List<? extends IAttempt> getAttempts() {
				return playerGame.getAttempts();
			}

			@Override
			public int getTotalScore() {
				return calculateLegScore(playerGame);
			}

			@Override
			public int getDartCount() {
				return calculateDartCount(playerGame);
			}

			@Override
			public float getAverageAttemptScore() {
				if (getDartCount() == 0) {
					return 0;
				}
				return Util.calculateAttemptsTotalScore(playerGame.getAttempts()) * 3f / getDartCount();
			}

			@Override
			public List<String> getHints() {
				return buildHints(playerGame);
			}
		};

	}

	protected int calculateLegScore(PlayerGame playerGame) {
		return Util.calculateAttemptsTotalScore(playerGame.getAttempts());
	}

	protected int calculateDartCount(PlayerGame playerGame) {
		int result = 0;
		for (Attempt attempt : playerGame.getAttempts()) {
			result += attempt.getDartCount();
		}
		return result;
	}

	private LegStatus addAttempt(Attempt attempt, PlayerGame playerGame) {
		playerGame.getAttempts().add(attempt);
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

	protected abstract AttemptStatus checkAttempt(Attempt attempt, PlayerGame playerGame);

	protected List<String> buildHints(PlayerGame playerGame) {
		return Collections.emptyList();
	}

	protected void processAttempt(Attempt attempt) {
		if (attempt.getDartCount() == null) {
			attempt.setDartCount(3);
		}
	}


	protected PlayerGame getCurrentPlayerGame() {
		return game.getPlayerGames().get(0);
	}

	protected PlayerGame getPreviousPlayerGame() {
		return game.getPlayerGames().get(0);
	}


	protected boolean checkGameOver(Game game) {
		return getCurrentPlayerGame().getAttempts().size() == 10;
	}
}
