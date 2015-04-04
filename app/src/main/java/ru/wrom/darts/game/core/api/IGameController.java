package ru.wrom.darts.game.core.api;

import java.util.List;

public interface IGameController {

	void init(GameSettings gameSettings);

	LegStatus addAttempt(int totalScore, Integer dartCount);

	List<IPlayerLegStatus> getPlayerStatuses();

	IPlayerLegStatus getPlayerLegStatus(Player player);

	Player getCurrentPlayer();

	void cancelLastAttempt();

	boolean isCanSubmitScore(int totalScore);

}
