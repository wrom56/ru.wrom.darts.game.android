package ru.wrom.darts.game.core.api;

import java.util.ArrayList;
import java.util.List;

public class MatchSettings {
	private GameType gameType = new GameType();
	private List<PlayerSettings> playersSettings = new ArrayList<>();
	private int maxSetCount;
	private int maxLegCount;

	public void addPlayer(PlayerSettings playerSettings) {
		getPlayersSettings().add(playerSettings);
	}

	public List<PlayerSettings> getPlayersSettings() {
		return playersSettings;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameTypeCode code) {
		setGameType(code, null);
	}

	public void setGameType(GameTypeCode code, String param) {
		gameType.setCode(code);
		gameType.setParam(param);
	}

	public int getMaxSetCount() {
		return maxSetCount;
	}

	public void setMaxSetCount(int maxSetCount) {
		this.maxSetCount = maxSetCount;
	}

	public int getMaxLegCount() {
		return maxLegCount;
	}

	public void setMaxLegCount(int maxLegCount) {
		this.maxLegCount = maxLegCount;
	}
}



