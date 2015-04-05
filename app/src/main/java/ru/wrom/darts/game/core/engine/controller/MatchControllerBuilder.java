package ru.wrom.darts.game.core.engine.controller;

import ru.wrom.darts.game.core.api.IMatchController;
import ru.wrom.darts.game.core.api.MatchSettings;
import ru.wrom.darts.game.core.engine.model.Match;

public class MatchControllerBuilder {

	public static IMatchController buildController(MatchSettings settings) {
		Match match = new Match();
		match.setGameType(settings.getGameType());
		match.setMaxSetCount(settings.getMaxSetCount());
		match.setMaxLegCount(settings.getMaxLegCount());
		match.setPlayerSettingsList(settings.getPlayersSettings());
		MatchController controller = new MatchController(match);
		return controller;
	}

}
