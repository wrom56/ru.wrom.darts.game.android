package ru.wrom.darts.game.core.engine.model;

import java.util.ArrayList;
import java.util.List;

import ru.wrom.darts.game.core.api.Dart;
import ru.wrom.darts.game.core.api.Player;

public class PlayerGame {

	private Game game;

	private Player player;

	private Dart dart;

	private List<Attempt> attempts;


	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Dart getDart() {
		return dart;
	}

	public void setDart(Dart dart) {
		this.dart = dart;
	}

	public List<Attempt> getAttempts() {
		if (attempts == null) {
			attempts = new ArrayList<>();
		}
		return attempts;
	}

	public void setAttempts(List<Attempt> attempts) {
		this.attempts = attempts;
	}
}



