package ru.wrom.darts.game.core.engine.controller;

import ru.wrom.darts.game.core.api.GameSettings;
import ru.wrom.darts.game.core.api.IGameController;

public class GameControllerFactory {

	public static IGameController getController(GameSettings gameSettings) {
		switch (gameSettings.getGameType()) {
			case SECTOR_ATTEMPT:
				return new SectorGameController(gameSettings.getGameTypeParam());
			case ALL_DOUBLE:
				return new AllDoubleGameController();
			case GAME_X01:
				return new GameX01Controller(Integer.valueOf(gameSettings.getGameTypeParam()));
			case BIG_ROUND:
				return new BigRoundGameController();
		}
		throw new IllegalArgumentException("Game type: " + gameSettings.getGameType().name());
	}

}
