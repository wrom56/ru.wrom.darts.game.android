package ru.wrom.darts.game.core.engine.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import ru.wrom.darts.game.core.api.AddAttemptResult;
import ru.wrom.darts.game.core.api.GameSettings;
import ru.wrom.darts.game.core.api.IAttempt;
import ru.wrom.darts.game.core.api.IGameController;
import ru.wrom.darts.game.core.api.IPlayerStatus;
import ru.wrom.darts.game.core.api.Player;
import ru.wrom.darts.game.core.api.PlayerSettings;
import ru.wrom.darts.game.core.engine.model.Attempt;
import ru.wrom.darts.game.core.engine.model.Game;
import ru.wrom.darts.game.core.engine.model.PlayerGame;

public abstract class AbstractGameController implements IGameController {

	protected Game game;

	@Override
	public List<IPlayerStatus> getPlayerStatuses() {
		List<IPlayerStatus> playerStatuses = new ArrayList<>();
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
	public AddAttemptResult addAttempt(int totalScore) {
		return addAttempt(new Attempt(totalScore));
	}

	@Override
	public AddAttemptResult addAttempt(int totalScore, int dartCount) {
		return addAttempt(new Attempt(totalScore, dartCount));
	}


	@Override
	public IPlayerStatus getCurrentPlayerStatus() {
		return buildPlayerStatus(getCurrentPlayerGame());
	}


	protected AddAttemptResult addAttempt(Attempt attempt) {
		PlayerGame currentPlayerGame = getCurrentPlayerGame();
		AddAttemptResult result = checkAttempt(attempt, currentPlayerGame);
		if (result != AddAttemptResult.ATTEMPT_ADDED) {
			return result;
		}
		processAttempt(attempt);
		addAttempt(attempt, currentPlayerGame);
		if (checkGameOver(game)) {
			return AddAttemptResult.GAME_OVER;
		} else {
			return AddAttemptResult.ATTEMPT_ADDED;
		}
	}

	protected boolean checkGameOver(Game game) {
		return getCurrentPlayerGame().getAttempts().size() == 10;
	}

	private IPlayerStatus buildPlayerStatus(final PlayerGame playerGame) {
		return new IPlayerStatus() {
			@Override
			public Player getPlayer() {
				return playerGame.getPlayer();
			}

			@Override
			public List<? extends IAttempt> getAttempts() {
				return playerGame.getAttempts();
			}

			@Override
			public int getTotalScore() {
				return calculateTotalScore(playerGame);
			}

			@Override
			public int getDartCount() {
				return calculateDartCount(playerGame);
			}

			@Override
			public BigDecimal getLegAverageAttemptScore() {
				return new BigDecimal(0);
			}

			@Override
			public int getLegMaxAttemptScore() {
				return 0;
			}

			@Override
			public int getMaxCheckout() {
				return 0;
			}

			@Override
			public List<String> getHints() {
				return buildHints(playerGame);
			}
		};

	}


	protected int calculateTotalScore(PlayerGame playerGame) {
		int totalScore = 0;
		for (Attempt attempt : playerGame.getAttempts()) {
			totalScore += attempt.getTotalScore();
		}
		return totalScore;
	}

	protected int calculateDartCount(PlayerGame playerGame) {
		return playerGame.getAttempts().size() * 3;
	}

	protected void addAttempt(Attempt attempt, PlayerGame playerGame) {
		playerGame.getAttempts().add(attempt);
	}

	protected abstract AddAttemptResult checkAttempt(Attempt attempt, PlayerGame playerGame);

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


}
