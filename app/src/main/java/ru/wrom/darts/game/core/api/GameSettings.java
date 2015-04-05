package ru.wrom.darts.game.core.api;

import java.util.ArrayList;
import java.util.List;

public class GameSettings {
	private GameTypeCode gameTypeCode;
	private String gameTypeParam;
	private List<PlayerSettings> playersSettings = new ArrayList<>();

	public void setGameTypeCode(GameTypeCode gameTypeCode) {
		this.gameTypeCode = gameTypeCode;
	}

	public void addPlayer(PlayerSettings playerSettings) {
		getPlayersSettings().add(playerSettings);
	}

	public GameTypeCode getGameTypeCode() {
		return gameTypeCode;
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



