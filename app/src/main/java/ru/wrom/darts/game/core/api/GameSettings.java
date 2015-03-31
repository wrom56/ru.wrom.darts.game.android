package ru.wrom.darts.game.core.api;

import java.util.ArrayList;
import java.util.List;

public class GameSettings {
	private GameType gameType;
	private String gameTypeParam;
	private List<PlayerSettings> playersSettings = new ArrayList<>();

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public void addPlayer(PlayerSettings playerSettings) {
		getPlayersSettings().add(playerSettings);
	}

	public GameType getGameType() {
		return gameType;
	}

	public List<PlayerSettings> getPlayersSettings() {
		return playersSettings;
	}

	public String getGameTypeParam() {
		return gameTypeParam;
	}

	public void setGameTypeParam(String gameTypeParam) {
		this.gameTypeParam = gameTypeParam;
	}
}



