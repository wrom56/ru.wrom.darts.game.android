package ru.wrom.darts.game.android.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.List;

import ru.wrom.darts.game.android.MatchActivityHelper;
import ru.wrom.darts.game.android.R;
import ru.wrom.darts.game.android.Settings;
import ru.wrom.darts.game.core.api.AddAttemptResult;
import ru.wrom.darts.game.core.api.IAttempt;
import ru.wrom.darts.game.core.api.IMatchController;
import ru.wrom.darts.game.core.api.IPlayerMatchStatus;
import ru.wrom.darts.game.core.api.Player;
import ru.wrom.darts.game.core.engine.controller.MatchControllerBuilder;

public class MatchActivity extends ActionBarActivity {

	IMatchController matchController;
	private int attemptScore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_match);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_match, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_cancel_last_attempt) {
			//TODO
			//cancelLastAttempt();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		setTitle(MatchActivityHelper.getTitle(Settings.getInstance().getMatchSettings()));
		matchController = MatchControllerBuilder.buildController(Settings.getInstance().getMatchSettings());
		updateView();
	}

	private void updateView() {
		updateScoreTable();
		updateCurrentAttemptScore();
	}

	private void updateScoreTable() {
		Player player = matchController.getPlayerSettingsList().get(0).getPlayer();
		updateTextView(R.id.player, player.getName());

		IPlayerMatchStatus playerMatchStatus = matchController.getPlayerMatchStatus(player);
		updateTextView(R.id.set_win, playerMatchStatus.setWin());
		updateTextView(R.id.leg_win, playerMatchStatus.legWin());
		updateTextView(R.id.leg_score, matchController.getPlayerLegStatus(player).getScore());


		List<? extends IAttempt> attempts = matchController.getPlayerLegStatus(getCurrentPlayer()).getAttempts();


		updateTextView(R.id.last_attempt1, attempts.isEmpty() ? "" : attempts.get(attempts.size() - 1).getTotalScore());
		updateTextView(R.id.last_attempt2, attempts.size() < 2 ? "" : attempts.get(attempts.size() - 2).getTotalScore());
		updateTextView(R.id.last_attempt3, attempts.size() < 3 ? "" : attempts.get(attempts.size() - 3).getTotalScore());

		List<String> hints = matchController.getPlayerLegStatus(getCurrentPlayer()).getHints();
		updateTextView(R.id.hints, hints.isEmpty() ? "" : hints.get(0));
	}

	private Player getCurrentPlayer() {
		return matchController.getCurrentPlayer();
	}

	private void updateTextView(int textViewId, Object text) {
		((TextView) findViewById(textViewId)).setText(String.valueOf(text));
	}


	private void onClickNumber(int number) {
		int newCurrentAttendScore = attemptScore * 10 + number;
		if (newCurrentAttendScore <= 180) {
			attemptScore = newCurrentAttendScore;
			updateCurrentAttemptScore();
			if (matchController.canSubmitScore(attemptScore)) {
				submitScore(attemptScore, null);
			}
		}
	}

	private void updateCurrentAttemptScore() {
		updateTextView(R.id.attempt_score, attemptScore);
	}

	public void onClickNumber0(View view) {
		onClickNumber(0);
	}

	public void onClickNumber1(View view) {
		onClickNumber(1);
	}

	public void onClickNumber2(View view) {
		onClickNumber(2);
	}

	public void onClickNumber3(View view) {
		onClickNumber(3);
	}

	public void onClickNumber4(View view) {
		onClickNumber(4);
	}

	public void onClickNumber5(View view) {
		onClickNumber(5);
	}

	public void onClickNumber6(View view) {
		onClickNumber(6);
	}

	public void onClickNumber7(View view) {
		onClickNumber(7);
	}

	public void onClickNumber8(View view) {
		onClickNumber(8);
	}

	public void onClickNumber9(View view) {
		onClickNumber(9);
	}

	public void onClickDelete(View view) {
		attemptScore = attemptScore / 10;
		updateCurrentAttemptScore();
	}

	public void onClickEnter(View view) {
		submitScore(attemptScore, null);
	}

	private void submitScore(int attemptScore, Integer dartCount) {
		AddAttemptResult result = matchController.addAttempt(attemptScore, dartCount);
		AlertDialog.Builder dlgAlert;
		switch (result) {
			case NEXT_ATTEMPT:
				onAddAttempt();
				showScoreToast(attemptScore == 0 ? "No score" : String.valueOf(attemptScore));
				break;
			case INVALID_ATTEMPT:
				dlgAlert = new AlertDialog.Builder(this);
				dlgAlert.setMessage(String.valueOf(attemptScore) + " is invalid score");
				dlgAlert.setNegativeButton("OK", null);
				dlgAlert.create().show();
				break;
			case NEED_DART_COUNT_1:
				showDartCountDialog(1);
				break;
			case NEED_DART_COUNT_2:
				showDartCountDialog(2);
				break;
			case LEG_OVER:
				onAddAttempt();
				dlgAlert = new AlertDialog.Builder(this);
				dlgAlert.setMessage("Leg over");
				dlgAlert.setNegativeButton("OK", null);
				dlgAlert.create().show();
		}

	}

	private void onAddAttempt() {
		attemptScore = 0;
		updateView();
	}


	private void showScoreToast(String text) {
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toast_layout_root));
		((TextView) layout.findViewById(R.id.toast_text)).setText(text);
		Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.TOP, 0, 300);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(layout);
		toast.show();
	}

	private void showDartCountDialog(final int minDartCount) {
		List<String> variants = new ArrayList<>();
		if (minDartCount == 1) {
			variants.add("1");
		}
		variants.add("2");
		variants.add("3");


		new AlertDialog.Builder(this).setTitle("Dart count?")
				.setItems(variants.toArray(new String[variants.size()]), new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						submitScore(attemptScore, minDartCount == 1 ? which + 1 : which + 2);
					}
				}).create().show();
	}

}
