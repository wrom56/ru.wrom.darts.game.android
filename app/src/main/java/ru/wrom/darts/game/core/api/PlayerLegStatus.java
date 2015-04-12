package ru.wrom.darts.game.core.api;

import java.util.List;

public interface PlayerLegStatus {

	List<? extends IAttempt> getAttempts();

	int getScore();

	int getDartCount();

	float getAverageAttemptScore();

	List<String> getHints();

}
