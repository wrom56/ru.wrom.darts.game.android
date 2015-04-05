package ru.wrom.darts.game.android;

import ru.wrom.darts.game.core.api.MatchSettings;

public class MatchActivityHelper {

	public static String getTitle(MatchSettings settings) {
		switch (settings.getGameType().getCode()) {
			case SECTOR_ATTEMPT:
				return "Sector " + settings.getGameType().getParam();
			case ALL_DOUBLE:
				return "All double";
			case GAME_X01:
				return settings.getGameType().getParam();
			case BIG_ROUND:
				return "Big round";
		}
		return "";
	}
}
