package com.example.android.surfaceview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceView;

public class GameSampleWithSurfaceView extends Activity {
	MySurfaceView view;
	Thread mainLoop;
	
	class MySurfaceView extends SurfaceView implements Runnable {
		Bitmap bitmap;
		boolean u, d, l, r;
		int left, top;
		Paint paint = new Paint();
		long time;
		int tx, ty;

		public MySurfaceView(Context context) {
			super(context);
			setFocusable(true); // キーイベントを使うために必須
			requestFocus(); // フォーカスを当てないとキーイベントを拾わない
			bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.droid);
			paint.setColor(Color.BLUE);
			paint.setTextSize(12);
			tx = bitmap.getWidth();
			ty = bitmap.getHeight() / 2;
			mainLoop = new Thread(this);
			mainLoop.start();
		}

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			Log.d("TEST", "onKeyDown");
			switch (keyCode) {
			case KeyEvent.KEYCODE_DPAD_UP:
				u = true;
				break;
			case KeyEvent.KEYCODE_DPAD_DOWN:
				d = true;
				break;
			case KeyEvent.KEYCODE_DPAD_LEFT:
				l = true;
				break;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				r = true;
				break;
			default:
				return false;
			}
			return true;
		}
		
		@Override
		public boolean onKeyUp(int keyCode, KeyEvent event) {
			Log.d("TEST", "onKeyDown");
			switch (keyCode) {
			case KeyEvent.KEYCODE_DPAD_UP:
				u = false;
				break;
			case KeyEvent.KEYCODE_DPAD_DOWN:
				d = false;
				break;
			case KeyEvent.KEYCODE_DPAD_LEFT:
				l = false;
				break;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				r = false;
				break;
			default:
				return false;
			}
			return true;
		}
		
		void doDraw() {
			Canvas canvas = getHolder().lockCanvas();
			if (canvas != null) {
				canvas.drawColor(Color.WHITE);
				if (u) top--;
				if (d) top++;
				if (l) left--;
				if (r) left++;
				canvas.drawBitmap(bitmap, left * 10, top * 10, null);
				long now = System.currentTimeMillis();
				Log.d("TEST", "" + (now - time));
				canvas.drawText("" + 1000f / (now - time) + "fps", tx, ty, paint);
				time = now;
				getHolder().unlockCanvasAndPost(canvas);
			}
		}

		@Override
		public void run() {
			while (true) {
				doDraw();
			}
		}
	}
	
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new MySurfaceView(this);
		setContentView(view);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Thread.interrupted();
	}
}
