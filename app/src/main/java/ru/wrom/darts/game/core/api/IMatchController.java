package ru.wrom.darts.game.core.api;

import java.util.List;

public interface IMatchController {

	void newLeg();

	List<PlayerSettings> getPlayerSettingsList();

	IPlayerMatchStatus getPlayerMatchStatus(Player player);

	Player getCurrentPlayer();

	PlayerLegStatus getPlayerLegStatus(Player player);

	AddAttemptResult addAttempt(int totalScore, Integer dartCount);

	boolean canSubmitScore(int totalScore);

	void cancelLastAttempt();

	void submitLeg();

	boolean checkLegOver();

	boolean checkMatchOver();

	boolean checkSetOver();

}
