package ru.wrom.darts.game.core.engine.controller.leg;

import ru.wrom.darts.game.core.api.GameType;

public class LegControllerFactory {

	public static AbstractLegController getController(GameType gameType) {
		switch (gameType.getCode()) {
			case SECTOR_ATTEMPT:
				return new LegController();
			case ALL_DOUBLE:
				return new LegController();
			case GAME_X01:
				return new LegController();
			case BIG_ROUND:
				return new LegController();
		}
		throw new IllegalArgumentException("Game type: " + gameType.getCode().name());
	}

}
