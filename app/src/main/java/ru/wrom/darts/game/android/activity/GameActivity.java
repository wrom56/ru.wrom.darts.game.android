package ru.wrom.darts.game.android.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.wrom.darts.game.android.R;
import ru.wrom.darts.game.android.Settings;
import ru.wrom.darts.game.core.api.AddAttemptResult;
import ru.wrom.darts.game.core.api.GameSettings;
import ru.wrom.darts.game.core.api.IAttempt;
import ru.wrom.darts.game.core.api.IGameController;
import ru.wrom.darts.game.core.engine.controller.GameControllerFactory;

public class GameActivity extends ActionBarActivity {

	private int attemptScore;
	private IGameController gameController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_game, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_cancel_last_attempt) {
			cancelLastAttempt();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void cancelLastAttempt() {
		gameController.cancelLastAttempt();
		attemptScore = 0;
		updateView();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		setTitle(getTitle(Settings.getInstance().getGameSettings()));
		gameController = GameControllerFactory.getController(Settings.getInstance().getGameSettings());
		gameController.init(Settings.getInstance().getGameSettings());
		createStatusBar(Settings.getInstance().getGameSettings());
		updateView();
	}

	private void createStatusBar(GameSettings gameSettings) {
		switch (gameSettings.getGameType()) {
			default:
				((TextView) findViewById(R.id.status_bar_param_name_1)).setText("darts:");
				((TextView) findViewById(R.id.status_bar_param_name_2)).setText("3da:");
				((TextView) findViewById(R.id.status_bar_param_name_3)).setText("hi:");
				((TextView) findViewById(R.id.status_bar_param_name_4)).setText("co:");
		}
	}

	private void onClickNumber(int number) {
		int newCurrentAttendScore = attemptScore * 10 + number;
		if (newCurrentAttendScore <= 180) {
			attemptScore = newCurrentAttendScore;
			updateCurrentAttemptScore();
		}
	}

	private void updateView() {
		updateMainTable();
		updateCurrentAttemptScore();
		updateStatusBar();
	}

	private void updateStatusBar() {
		((TextView) findViewById(R.id.status_bar_param_value_1)).setText(String.valueOf(gameController.getPlayerLegStatus(gameController.getCurrentPlayer()).getDartCount()));
		((TextView) findViewById(R.id.status_bar_param_value_2)).setText(String.format("%.1f", gameController.getPlayerLegStatus(gameController.getCurrentPlayer()).getAverageAttemptScore()));
	}

	private void updateMainTable() {
		((TextView) findViewById(R.id.score)).setText(String.valueOf(gameController.getPlayerLegStatus(gameController.getCurrentPlayer()).getTotalScore()));

		List<? extends IAttempt> attempts = gameController.getPlayerLegStatus(gameController.getCurrentPlayer()).getAttempts();
		((TextView) findViewById(R.id.last_attempt1)).setText(attempts.isEmpty() ? "" : String.valueOf(attempts.get(attempts.size() - 1).getTotalScore()));
		((TextView) findViewById(R.id.last_attempt2)).setText(attempts.size() < 2 ? "" : String.valueOf(attempts.get(attempts.size() - 2).getTotalScore()));
		((TextView) findViewById(R.id.last_attempt3)).setText(attempts.size() < 3 ? "" : String.valueOf(attempts.get(attempts.size() - 3).getTotalScore()));


		List<String> hints = gameController.getPlayerLegStatus(gameController.getCurrentPlayer()).getHints();
		((TextView) findViewById(R.id.hints)).setText(hints.isEmpty() ? "" : hints.get(0));
	}

	private void updateCurrentAttemptScore() {
		TextView view = (TextView) findViewById(R.id.attempt_score);
		view.setText(String.valueOf(attemptScore));
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
		AlertDialog.Builder dlgAlert;
		AddAttemptResult result = gameController.addAttempt(attemptScore);
		switch (result) {
			case ATTEMPT_ADDED:
				showScoreToast(attemptScore == 0 ? "No score" : String.valueOf(attemptScore));
				break;
			case INVALID_ATTEMPT:
				dlgAlert = new AlertDialog.Builder(this);
				dlgAlert.setMessage(String.valueOf(attemptScore) + " is invalid score");
				dlgAlert.setNegativeButton("OK", null);
				dlgAlert.create().show();
				break;
			case GAME_OVER:
				dlgAlert = new AlertDialog.Builder(this);
				dlgAlert.setMessage("Game over");
				dlgAlert.setNegativeButton("OK", null);
				dlgAlert.create().show();
		}
		attemptScore = 0;
		updateView();
	}


	public void onClickDelete(View view) {
		attemptScore = 0;
		updateCurrentAttemptScore();
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

	private void showScoreToast(String text) {
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toast_layout_root));
		((TextView) layout.findViewById(R.id.toast_text)).setText(text);
		Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(layout);
		toast.show();
	}

}
