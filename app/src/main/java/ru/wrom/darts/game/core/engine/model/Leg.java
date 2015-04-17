package ru.wrom.darts.game.core.engine.model;

import java.util.ArrayList;
import java.util.List;

import ru.wrom.darts.game.core.api.PlayerSettings;

public class Leg {

	private final Set set;
	private final List<PlayerLeg> playerLegs = new ArrayList<>();


	public Leg(Set set, List<PlayerSettings> playerSettingsList) {
		this.set = set;
		for (PlayerSettings playerSettings : playerSettingsList) {
			playerLegs.add(new PlayerLeg(this, playerSettings));
		}
	}

	public List<PlayerLeg> getPlayerLegs() {
		return playerLegs;
	}
}
