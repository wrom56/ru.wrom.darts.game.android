package ru.wrom.darts.game.core.engine.model;

import java.util.ArrayList;
import java.util.List;

import ru.wrom.darts.game.core.api.GameType;
import ru.wrom.darts.game.core.api.PlayerSettings;

public class Match {

	private GameType gameType;
	private int maxSetCount;
	private int maxLegCount;
	private List<PlayerSettings> playerSettingsList;
	private List<Set> sets = new ArrayList<>();

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
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

	public List<PlayerSettings> getPlayerSettingsList() {
		return playerSettingsList;
	}

	public void setPlayerSettingsList(List<PlayerSettings> playerSettingsList) {
		this.playerSettingsList = playerSettingsList;
	}

	public List<Set> getSets() {
		return sets;
	}

	public void setSets(List<Set> sets) {
		this.sets = sets;
	}

	public void newSet() {
		sets.add(new Set(this));
	}
}
