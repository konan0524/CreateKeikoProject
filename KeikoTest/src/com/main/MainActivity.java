package com.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView text;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.i("test4", "test4");
		text = (TextView) findViewById(R.id.text_content);
		String str = (String) text.getText();
		// button.setOnClickListener(this);

	}

	public void onClickTestButton(View v) {
		Log.i("test3", "test3");
		// Intent intent = new Intent(this, ResponseResultActivity.class);
		// intent.putExtra(Constants.IntentParam.METHOD,
		// Constants.Request.METHOD_TEST_ECHO);
		// startActivity(intent);
		// String resultJson = CloudAccessManager.testEcho(null);
		// text.setText(resultJson);

		Intent intent = new Intent(this, Main2Activity.class);
		intent.putExtra("com.suddenAngerSystem.displayString", "a");
		Log.i("test", "test");
		startActivity(intent);
		Log.i("test2", "test2");

	}
}
