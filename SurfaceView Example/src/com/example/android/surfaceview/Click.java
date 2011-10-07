package com.example.android.surfaceview;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;

public class Click extends Activity {
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.click);
		SurfaceView surfaceView = (SurfaceView)findViewById(R.id.SurfaceView01);
		surfaceView.setClickable(true);
		surfaceView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("TEST", "onClick");
				SurfaceView surfaceView = (SurfaceView)v;
				
				Canvas canvas = surfaceView.getHolder().lockCanvas();
				Paint paint = new Paint();
				canvas.drawColor(Color.WHITE);
				paint.setColor(Color.BLUE);
				paint.setAntiAlias(true);
				paint.setTextSize(24);

				canvas.drawText("Hello, SurfaceView!", 0, paint.getTextSize(), paint);
				surfaceView.getHolder().unlockCanvasAndPost(canvas);
			}
		});
	}
}
