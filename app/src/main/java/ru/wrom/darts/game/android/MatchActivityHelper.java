package ru.wrom.darts.game.android;

import ru.wrom.darts.game.core.api.MatchSettings;

public class MatchActivityHelper {

	public static String getTitle(MatchSettings settings) {
		String firstToLeg = ": first to " + (settings.getMaxLegCount() / 2 + 1);
		switch (settings.getGameType().getCode()) {
			case SECTOR_ATTEMPT:
				return "Sector " + settings.getGameType().getParam() + firstToLeg;
			case ALL_DOUBLE:
				return "All double" + firstToLeg;
			case GAME_X01:
				return settings.getGameType().getParam() + firstToLeg;
			case BIG_ROUND:
				return "Big round" + firstToLeg;
		}
		return "";
	}
}
