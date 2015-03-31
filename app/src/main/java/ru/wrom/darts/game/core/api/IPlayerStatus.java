package ru.wrom.darts.game.core.api;

import java.math.BigDecimal;
import java.util.List;

public interface IPlayerStatus {

	Player getPlayer();

	List<? extends IAttempt> getAttempts();

	int getTotalScore();

	int getDartCount();

	BigDecimal getLegAverageAttemptScore();

	int getLegMaxAttemptScore();

	int getMaxCheckout();

	List<String> getHints();

}
