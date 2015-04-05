package ru.wrom.darts.game.android.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import ru.wrom.darts.game.android.MatchActivityHelper;
import ru.wrom.darts.game.android.R;
import ru.wrom.darts.game.android.Settings;
import ru.wrom.darts.game.core.api.IMatchController;
import ru.wrom.darts.game.core.engine.controller.MatchControllerBuilder;

public class MatchActivity extends ActionBarActivity {

	IMatchController matchController;

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
	}

	private void updateScoreTable() {
		((TextView) findViewById(R.id.player)).setText(matchController.getPlayerSettingsList().get(0).getPlayer().getName());


		/*
		private void updateMainTable() {
		((TextView) findViewById(R.id.score)).setText(String.valueOf(gameController.getPlayerLegStatus(gameController.getCurrentPlayer()).getTotalScore()));

		List<? extends IAttempt> attempts = gameController.getPlayerLegStatus(gameController.getCurrentPlayer()).getAttempts();
		((TextView) findViewById(R.id.last_attempt1)).setText(attempts.isEmpty() ? "" : String.valueOf(attempts.get(attempts.size() - 1).getTotalScore()));
		((TextView) findViewById(R.id.last_attempt2)).setText(attempts.size() < 2 ? "" : String.valueOf(attempts.get(attempts.size() - 2).getTotalScore()));
		((TextView) findViewById(R.id.last_attempt3)).setText(attempts.size() < 3 ? "" : String.valueOf(attempts.get(attempts.size() - 3).getTotalScore()));


		List<String> hints = gameController.getPlayerLegStatus(gameController.getCurrentPlayer()).getHints();
		((TextView) findViewById(R.id.hints)).setText(hints.isEmpty() ? "" : hints.get(0));
	}
		 */
	}


	private void onClickNumber(int i) {

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
		/*
				attemptScore = attemptScore / 10;
		updateCurrentAttemptScore();

		 */
	}

	public void onClickEnter(View view) {
		/*
		submitScore(attemptScore, null);
		 */
	}


}
