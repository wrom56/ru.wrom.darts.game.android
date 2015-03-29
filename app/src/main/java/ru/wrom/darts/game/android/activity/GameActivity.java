package ru.wrom.darts.game.android.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import ru.wrom.darts.game.android.R;

public class GameActivity extends ActionBarActivity {

	private int attendScore;
	private int attendNumber = 0;
	private int score;

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

	private void onClickNumber(int number) {
		int newCurrentAttendScore = attendScore * 10 + number;
		if (newCurrentAttendScore <= 180) {
			attendScore = newCurrentAttendScore;
			onUpdateCurrentAttendScore();
		}
	}

	private void updateMainTable() {
		TextView textView = (TextView) findViewById(R.id.attend_count);
		textView.setText(String.valueOf(attendNumber));
		textView = (TextView) findViewById(R.id.score);
		textView.setText(String.valueOf(score));
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
		score += attendScore;
		attendScore = 0;
		attendNumber++;
		onUpdateCurrentAttendScore();
		updateMainTable();
	}

	private void onUpdateCurrentAttendScore() {
		TextView currentAttendScoreTextView = (TextView) findViewById(R.id.attend_score);
		currentAttendScoreTextView.setText(String.valueOf(attendScore));
	}

	public void onClickDelete(View view) {
		attendScore = attendScore / 10;
		onUpdateCurrentAttendScore();
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

	public void onClickNewGame(View view) {
		attendScore = 0;
		attendNumber = 0;
		score = 0;
		updateMainTable();
		onUpdateCurrentAttendScore();
	}


}
