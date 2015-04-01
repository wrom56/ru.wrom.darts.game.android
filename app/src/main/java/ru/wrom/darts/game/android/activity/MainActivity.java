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
import ru.wrom.darts.game.core.api.GameType;
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
		startGame(GameType.SECTOR_ATTEMPT, "BULL");
	}

	public void onClickAllDoubleButton(View view) {
		startGame(GameType.ALL_DOUBLE, null);
	}

	public void onClick501Button(View view) {
		startGame(GameType.GAME_X01, "501");
	}


	private void fillSettings(GameType gameType, String gameTypeParam) {
		Settings.getInstance().getGameSettings().setGameType(gameType);
		Settings.getInstance().getGameSettings().setGameTypeParam(gameTypeParam);
		PlayerSettings playerSettings = new PlayerSettings();
		Player player = new Player();
		player.setName("WroM");
		player.setCode("wrom");

		playerSettings.setPlayer(player);
		Dart dart = new Dart();
		dart.setLabel("1");
		dart.setCode("1");
		playerSettings.setDart(dart);
		Settings.getInstance().getGameSettings().getPlayersSettings().add(playerSettings);
	}


	private void startGame(GameType gameType, String gameTypeParam) {
		Intent intent = new Intent(this, GameActivity.class);
		fillSettings(gameType, gameTypeParam);
		startActivity(intent);
	}

	public void onClickSector20Button(View view) {
		startGame(GameType.SECTOR_ATTEMPT, "20");
	}

	public void onClickBigRoundButton(View view) {
		startGame(GameType.BIG_ROUND, null);
	}
}
