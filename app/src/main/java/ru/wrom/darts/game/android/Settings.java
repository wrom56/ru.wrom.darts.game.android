package ru.wrom.darts.game.android;

import ru.wrom.darts.game.core.api.GameSettings;
import ru.wrom.darts.game.core.api.MatchSettings;

public class Settings {

	private static final Settings instance = new Settings();
	private GameSettings gameSettings;
	private MatchSettings matchSettings = new MatchSettings();

	public static Settings getInstance() {
		return instance;
	}

	public GameSettings getGameSettings() {
		if (gameSettings == null) {
			gameSettings = new GameSettings();
		}
		return gameSettings;
	}

	public MatchSettings getMatchSettings() {
		return matchSettings;
	}

	public void setGameSettings(GameSettings gameSettings) {
		this.gameSettings = gameSettings;
	}
}
