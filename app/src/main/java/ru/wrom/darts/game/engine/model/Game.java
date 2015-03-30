package ru.wrom.darts.game.engine.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.wrom.darts.game.engine.api.GameType;

public class Game {

	private GameType gameType;

	private Date startDate;

	private Date endDate;

	private List<PlayerGame> playerGames;

	public List<PlayerGame> getPlayerGames() {
		if (playerGames == null) {
			playerGames = new ArrayList<>();
		}
		return playerGames;
	}

	public void addPlayerGame(PlayerGame playerGame) {
		playerGame.setGame(this);
		getPlayerGames().add(playerGame);
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setPlayerGames(List<PlayerGame> playerGames) {
		this.playerGames = playerGames;
	}


}



