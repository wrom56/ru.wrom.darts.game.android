package ru.wrom.darts.game.android;

import ru.wrom.darts.game.core.api.MatchSettings;

public class Settings {

	private static final Settings instance = new Settings();
	private MatchSettings matchSettings = new MatchSettings();

	public static Settings getInstance() {
		return instance;
	}


	public MatchSettings getMatchSettings() {
		return matchSettings;
	}

	public MatchSettings newMatchSettings() {
		matchSettings = new MatchSettings();
		return matchSettings;
	}

}
