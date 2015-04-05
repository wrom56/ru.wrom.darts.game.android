package ru.wrom.darts.game.core.engine.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.wrom.darts.game.core.api.GameType;

public class Game {

	private GameType gameType;

	private Date startDate;

	private Date endDate;

	private List<PlayerLeg> playerLegs;

	public List<PlayerLeg> getPlayerLegs() {
		if (playerLegs == null) {
			playerLegs = new ArrayList<>();
		}
		return playerLegs;
	}

	public void addPlayerGame(PlayerLeg playerLeg) {
		playerLeg.setGame(this);
		getPlayerLegs().add(playerLeg);
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

	public void setPlayerLegs(List<PlayerLeg> playerLegs) {
		this.playerLegs = playerLegs;
	}


}



