package ru.wrom.darts.game.android.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.wrom.darts.game.android.R;
import ru.wrom.darts.game.android.Settings;
import ru.wrom.darts.game.engine.api.AddAttemptResult;
import ru.wrom.darts.game.engine.api.Attempt;
import ru.wrom.darts.game.engine.api.GameSettings;
import ru.wrom.darts.game.engine.api.IGameController;
import ru.wrom.darts.game.engine.controller.GameControllerFactory;

public class GameActivity extends ActionBarActivity {

	private int attendScore;
	private IGameController gameController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_game, menu);
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

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		setTitle(getTitle(Settings.getInstance().getGameSettings()));
		gameController = GameControllerFactory.getController(Settings.getInstance().getGameSettings());
		gameController.init(Settings.getInstance().getGameSettings());
		updateView();
	}

	private void onClickNumber(int number) {
		int newCurrentAttendScore = attendScore * 10 + number;
		if (newCurrentAttendScore <= 180) {
			attendScore = newCurrentAttendScore;
			updateCurrentAttendScore();
		}
	}

	private void updateView() {
		updateMainTable();
		updateCurrentAttendScore();
	}


	private void updateMainTable() {
		TextView textView = (TextView) findViewById(R.id.attend_count);
		textView.setText(String.valueOf(gameController.getGameStatus().getPlayerStatuses().get(0).getAttempts().size()));
		textView = (TextView) findViewById(R.id.score);
		textView.setText(String.valueOf(gameController.getGameStatus().getPlayerStatuses().get(0).getTotalScore()));
		textView = (TextView) findViewById(R.id.previous_attempt);
		List<Attempt> attempts = gameController.getGameStatus().getPlayerStatuses().get(0).getAttempts();
		if (attempts.isEmpty()) {
			textView.setText("");
		} else {
			textView.setText(String.valueOf(attempts.get(attempts.size() - 1).getTotalScore()));
		}

		textView = (TextView) findViewById(R.id.hints);
		List<String> hints = gameController.getHints();
		if (hints.isEmpty()) {
			textView.setText("");
		} else {
			textView.setText(hints.get(0));
		}
	}

	private void updateCurrentAttendScore() {
		TextView currentAttendScoreTextView = (TextView) findViewById(R.id.attend_score);
		currentAttendScoreTextView.setText(String.valueOf(attendScore));
	}


	public void onClickNumber1(View view) {
		onClickNumber(1);
	}

	public void onClickNumber2(View view) {
		onClickNumber(2);
	}

	public void onClickNumber0(View view) {
		onClickNumber(0);
	}

	public void onClickEnter(View view) {
		Attempt attempt = new Attempt(attendScore);
		AddAttemptResult result = gameController.addAttempt(attempt);
		switch (result) {
			case ATTEMPT_ADDED:
				Toast toast = Toast.makeText(getApplicationContext(), String.valueOf(attendScore), Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				break;
			case INVALID_ATTEMPT:
				AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
				dlgAlert.setMessage(String.valueOf(attendScore) + " is invalid score");
				dlgAlert.setNegativeButton("OK", null);
				dlgAlert.create().show();
		}
		attendScore = 0;
		updateView();
	}


	public void onClickDelete(View view) {
		attendScore = 0;
		updateCurrentAttendScore();
	}

	public void onClickNumber6(View view) {
		onClickNumber(6);
	}

	public void onClickNumber5(View view) {
		onClickNumber(5);
	}

	public void onClickNumber4(View view) {
		onClickNumber(4);
	}

	public void onClickNumber3(View view) {
		onClickNumber(3);
	}

	public void onClickNumber8(View view) {
		onClickNumber(8);
	}

	public void onClickNumber9(View view) {
		onClickNumber(9);
	}

	public void onClickNumber7(View view) {
		onClickNumber(7);
	}

	private String getTitle(GameSettings gameSettings) {
		switch (gameSettings.getGameType()) {
			case SECTOR_ATTEMPT:
				return "Sector " + gameSettings.getGameTypeParam();
			case ALL_DOUBLE:
				return "All double";
			case GAME_X01:
				return gameSettings.getGameTypeParam();
			case BIG_ROUND:
				return "Big round";
		}

		return "";
	}

}
