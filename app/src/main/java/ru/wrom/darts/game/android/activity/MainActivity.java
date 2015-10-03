package ru.wrom.darts.game.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ru.wrom.darts.game.android.R;
import ru.wrom.darts.game.android.Settings;
import ru.wrom.darts.game.core.api.Dart;
import ru.wrom.darts.game.core.api.GameTypeCode;
import ru.wrom.darts.game.core.api.Player;
import ru.wrom.darts.game.core.api.PlayerSettings;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void onClickBullButton(View view) {
		startGame(GameTypeCode.SECTOR_ATTEMPT, "BULL", 5);
	}

	public void onClickAllDoubleButton(View view) {
		startGame(GameTypeCode.ALL_DOUBLE, null, 5);
	}

	public void onClick501Button(View view) {
		startGame(GameTypeCode.GAME_X01, "501", 9);
	}


	private void fillSettings(GameTypeCode gameTypeCode, String gameTypeParam, int maxLegCount) {
		PlayerSettings playerSettings = new PlayerSettings();
		Player player = new Player();
		player.setName("WroM");
		player.setCode("wrom");

		playerSettings.setPlayer(player);
		Dart dart = new Dart();
		dart.setLabel("1");
		dart.setCode("1");
		playerSettings.setDart(dart);
		Settings.getInstance().newMatchSettings();
		Settings.getInstance().getMatchSettings().setGameType(gameTypeCode, gameTypeParam);
		Settings.getInstance().getMatchSettings().getPlayersSettings().add(playerSettings);
		Settings.getInstance().getMatchSettings().setMaxLegCount(maxLegCount);
		Settings.getInstance().getMatchSettings().setMaxSetCount(1);
	}


	private void startGame(GameTypeCode gameTypeCode, String gameTypeParam, int maxLegCount) {
		Intent intent = new Intent(this, MatchActivity.class);
		fillSettings(gameTypeCode, gameTypeParam, maxLegCount);
		startActivity(intent);
	}

	public void onClickSector20Button(View view) {
		startGame(GameTypeCode.SECTOR_ATTEMPT, "20", 9);
	}

	public void onClickBigRoundButton(View view) {
		startGame(GameTypeCode.BIG_ROUND, null, 5);
	}

	public void onClickSelectButton(View view) {
		Intent intent = new Intent(this, GameSettingsActivity.class);
		startActivity(intent);
	}

	public void onClick301Button(View view) {
		startGame(GameTypeCode.GAME_X01, "301", 9);
	}
}
