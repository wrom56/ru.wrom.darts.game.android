package ru.wrom.darts.game.android.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import ru.wrom.darts.game.android.R;
import ru.wrom.darts.game.android.Settings;
import ru.wrom.darts.game.core.api.Dart;
import ru.wrom.darts.game.core.api.GameTypeCode;
import ru.wrom.darts.game.core.api.Player;
import ru.wrom.darts.game.core.api.PlayerSettings;

public class GameSettingsActivity extends ActionBarActivity {

	Logger logger = Logger.getLogger(GameSettingsActivity.class.getName());

	private Spinner gameTypeSpinner;
	private Spinner legCountSpinner;

	private final List<Integer> legCounts = new ArrayList<>();
	private final List<GameMode> gameTypes = new ArrayList<>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_settings);
		init();
	}

	private void init() {

		gameTypeSpinner = (Spinner) findViewById(R.id.gameTypeSpinner);

		Collections.addAll(gameTypes, GameMode.values());

		ArrayAdapter<GameMode> gameTypesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, gameTypes);
		gameTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		gameTypeSpinner.setAdapter(gameTypesAdapter);


		legCountSpinner = (Spinner) findViewById(R.id.legCountSpinner);

		legCounts.add(1);
		legCounts.add(3);
		legCounts.add(5);
		legCounts.add(7);
		legCounts.add(9);
		legCounts.add(11);
		legCounts.add(13);

		ArrayAdapter<Integer> legCountAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, legCounts);
		legCountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		legCountSpinner.setAdapter(legCountAdapter);

	}

	public void onClickStartGameButton(View view) {
		Intent intent = new Intent(this, MatchActivity.class);

		GameMode gameMode = (GameMode) gameTypeSpinner.getSelectedItem();
		GameTypeCode gameTypeCode;
		String gameTypeParam;

		switch (gameMode) {
			case BULL:
				gameTypeCode = GameTypeCode.SECTOR_ATTEMPT;
				gameTypeParam = "BULL";
				break;
			case GAME_501:
				gameTypeCode = GameTypeCode.GAME_X01;
				gameTypeParam = "501";
				break;
			case SECTOR_20:
				gameTypeCode = GameTypeCode.SECTOR_ATTEMPT;
				gameTypeParam = "20";
				break;
			default:
				gameTypeCode = GameTypeCode.valueOf(gameMode.name());
				gameTypeParam = null;
		}

		fillSettings(gameTypeCode, gameTypeParam);
		startActivity(intent);

	}

	private void fillSettings(GameTypeCode gameTypeCode, String gameTypeParam) {
		Settings.getInstance().getMatchSettings().setGameType(gameTypeCode, gameTypeParam);
		PlayerSettings playerSettings = new PlayerSettings();
		Player player = new Player();
		player.setName("WroM");
		player.setCode("wrom");

		playerSettings.setPlayer(player);
		Dart dart = new Dart();
		dart.setLabel("1");
		dart.setCode("1");
		playerSettings.setDart(dart);
		Settings.getInstance().getMatchSettings().getPlayersSettings().add(playerSettings);
		Settings.getInstance().getMatchSettings().setMaxLegCount((Integer) legCountSpinner.getSelectedItem());
		Settings.getInstance().getMatchSettings().setMaxSetCount(1);
	}


}
