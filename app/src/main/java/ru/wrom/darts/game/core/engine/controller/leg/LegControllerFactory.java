package ru.wrom.darts.game.core.engine.controller.leg;

import ru.wrom.darts.game.core.api.GameType;

public class LegControllerFactory {

	public static AbstractLegController getController(GameType gameType) {
		switch (gameType.getCode()) {
			case SECTOR_ATTEMPT:
				return new SectorLegController(gameType.getParam());
		}
		throw new IllegalArgumentException("Game type: " + gameType.getCode().name());
	}


}
