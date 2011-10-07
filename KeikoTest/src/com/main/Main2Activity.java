package com.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Main2Activity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main2);
		Intent intent = getIntent();
		String atr = intent.getStringExtra("");

		// text = (TextView) findViewById(R.id.text_content);

	}
}
