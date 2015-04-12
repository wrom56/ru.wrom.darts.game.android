package ru.wrom.darts.game.core.engine.model;

import java.util.ArrayList;
import java.util.List;

public class Set {
	private final Match match;
	private final List<Leg> legs = new ArrayList<>();

	public Set(Match match) {
		this.match = match;
	}

	public Match getMatch() {
		return match;
	}

	public List<Leg> getLegs() {
		return legs;
	}

	public void newLeg() {
		legs.add(new Leg(this, match.getPlayerSettingsList()));
	}

}
