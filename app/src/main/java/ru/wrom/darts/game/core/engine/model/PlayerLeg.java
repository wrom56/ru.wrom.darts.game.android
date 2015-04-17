package ru.wrom.darts.game.core.engine.model;

import java.util.ArrayList;
import java.util.List;

import ru.wrom.darts.game.core.api.PlayerSettings;

public class PlayerLeg {

	private final Leg leg;
	private final PlayerSettings playerSettings;
	private List<Attempt> attempts = new ArrayList<>();

	public PlayerLeg(Leg leg, PlayerSettings playerSettings) {
		this.leg = leg;
		this.playerSettings = playerSettings;
	}

	public PlayerSettings getPlayerSettings() {
		return playerSettings;
	}

	public List<Attempt> getAttempts() {
		return attempts;
	}

	public void addAttempt(Attempt attempt) {
		attempt.setPlayerLeg(this);
		this.attempts.add(attempt);
	}

	public void removeLastAttempt() {
		if (!attempts.isEmpty()) {
			attempts.remove(attempts.size() - 1);
		}
	}
}



