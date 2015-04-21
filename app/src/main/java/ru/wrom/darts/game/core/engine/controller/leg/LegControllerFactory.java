package ru.wrom.darts.game.core.engine.controller.leg;

import ru.wrom.darts.game.core.api.GameType;

public class LegControllerFactory {

	public static AbstractLegController getController(GameType gameType) {
		switch (gameType.getCode()) {
			case SECTOR_ATTEMPT:
				return new SectorLegController(gameType.getParam());
			case ALL_DOUBLE:
				return new AllDoubleLegController();
			case GAME_X01:
				return new X01LegController(Integer.valueOf(gameType.getParam()));
			case BIG_ROUND:
				return new BigRoundLegController();
		}
		throw new IllegalArgumentException("Game type: " + gameType.getCode().name());
	}


}
