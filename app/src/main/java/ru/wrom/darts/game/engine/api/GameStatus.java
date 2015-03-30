package ru.wrom.darts.game.engine.api;

import java.util.ArrayList;
import java.util.List;

public class GameStatus {
	private List<PlayerStatus> playerStatuses;

	public List<PlayerStatus> getPlayerStatuses() {
		if (playerStatuses == null) {
			playerStatuses = new ArrayList<>();
		}
		return playerStatuses;
	}

	public void setPlayerStatuses(List<PlayerStatus> playerStatuses) {
		this.playerStatuses = playerStatuses;
	}
}
