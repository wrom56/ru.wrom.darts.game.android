package ru.wrom.darts.game.android;

import ru.wrom.darts.game.engine.api.GameSettings;

public class Settings {

	private static final Settings instance = new Settings();
	private GameSettings gameSettings;

	public static Settings getInstance() {
		return instance;
	}

	public GameSettings getGameSettings() {
		if (gameSettings == null) {
			gameSettings = new GameSettings();
		}
		return gameSettings;
	}

	public void setGameSettings(GameSettings gameSettings) {
		this.gameSettings = gameSettings;
	}
}
