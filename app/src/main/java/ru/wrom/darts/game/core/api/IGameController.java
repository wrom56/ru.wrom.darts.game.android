package ru.wrom.darts.game.core.api;

import java.util.List;

public interface IGameController {

	void init(GameSettings gameSettings);

	AddAttemptResult addAttempt(int totalScore);

	AddAttemptResult addAttempt(int totalScore, int dartCount);

	List<IPlayerLegStatus> getPlayerStatuses();

	IPlayerLegStatus getPlayerLegStatus(Player player);

	Player getCurrentPlayer();

	void cancelLastAttempt();

}
