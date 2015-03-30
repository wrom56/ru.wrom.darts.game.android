package ru.wrom.darts.game.engine.api;

import java.util.List;

public interface IGameController {

	void init(GameSettings gameSettings);

	AddAttemptResult addAttempt(Attempt attempt);

	GameStatus getGameStatus();

	List<String> getHints();

}
