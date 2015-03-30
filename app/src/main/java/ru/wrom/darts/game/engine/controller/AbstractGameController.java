package ru.wrom.darts.game.engine.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import ru.wrom.darts.game.engine.api.AddAttemptResult;
import ru.wrom.darts.game.engine.api.Attempt;
import ru.wrom.darts.game.engine.api.GameSettings;
import ru.wrom.darts.game.engine.api.GameStatus;
import ru.wrom.darts.game.engine.api.IGameController;
import ru.wrom.darts.game.engine.api.PlayerSettings;
import ru.wrom.darts.game.engine.api.PlayerStatus;
import ru.wrom.darts.game.engine.model.Game;
import ru.wrom.darts.game.engine.model.PlayerGame;

public abstract class AbstractGameController implements IGameController {

	protected Game game;

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
	public AddAttemptResult addAttempt(Attempt attempt) {
		AddAttemptResult result = checkAttempt(attempt, getCurrentPlayerGame());
		if (result != AddAttemptResult.ATTEMPT_ADDED) {
			return result;
		}
		processAttempt(attempt);
		addAttempt(attempt, getCurrentPlayerGame());
		return AddAttemptResult.ATTEMPT_ADDED;
	}


	@Override
	public GameStatus getGameStatus() {
		GameStatus gameStatus = new GameStatus();
		for (PlayerGame playerGame : game.getPlayerGames()) {
			PlayerStatus playerStatus = new PlayerStatus();
			playerStatus.setPlayer(playerGame.getPlayer());
			playerStatus.setAttempts(playerGame.getAttempts());
			playerStatus.setTotalScore(getTotalScore(playerGame));
			gameStatus.getPlayerStatuses().add(playerStatus);
		}
		return gameStatus;
	}

	@Override
	public List<String> getHints() {
		return getHints(getCurrentPlayerGame());
	}

	protected int getTotalScore(PlayerGame playerGame) {
		int totalScore = 0;
		for (Attempt attempt : playerGame.getAttempts()) {
			totalScore += attempt.getTotalScore();
		}
		return totalScore;
	}

	protected void addAttempt(Attempt attempt, PlayerGame playerGame) {
		playerGame.getAttempts().add(attempt);
	}

	protected abstract AddAttemptResult checkAttempt(Attempt attempt, PlayerGame playerGame);

	protected List<String> getHints(PlayerGame playerGame) {
		return Collections.emptyList();
	}

	protected void processAttempt(Attempt attempt) {
		if (attempt.getDartCount() == null) {
			attempt.setDartCount(3);
		}
	}


	private PlayerGame getCurrentPlayerGame() {
		return game.getPlayerGames().get(0);
	}

}
