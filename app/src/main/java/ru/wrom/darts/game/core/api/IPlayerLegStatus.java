package ru.wrom.darts.game.core.api;

import java.util.List;

public interface IPlayerLegStatus {

	List<? extends IAttempt> getAttempts();

	int getTotalScore();

	int getDartCount();

	float getAverageAttemptScore();

	List<String> getHints();

}
