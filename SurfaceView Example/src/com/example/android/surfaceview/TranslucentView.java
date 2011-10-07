package com.example.android.surfaceview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

public class TranslucentView extends Activity {
	MyView view;
	Handler handler = new Handler();
	Thread mainLoop;
	
	class MyView extends View implements Runnable {
		Bitmap bitmap;
		boolean u, d, l, r;
		int left, top;
		Paint paint = new Paint();
		long time;
		int tx, ty;

		public MyView(Context context) {
			super(context);
			setFocusable(true);
			requestFocus();
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
		
		@Override
		protected void onDraw(Canvas canvas) {
			Log.d("TEST", "onDraw");
			if (canvas != null) {
// テーマの背景色で塗りつぶされるため、以下は必要ない
//				canvas.drawColor(Color.WHITE);
				if (u) top--;
				if (d) top++;
				if (l) left--;
				if (r) left++;
				canvas.drawBitmap(bitmap, left * 10, top * 10, null);
				long now = System.currentTimeMillis();
				Log.d("TEST", "" + (now - time));
				canvas.drawText("" + 1000f / (now - time) + "fps", tx, ty, paint);
				time = now;
			}
		}
		
		@Override
		public void run() {
			while (true) {
				handler.post(new Runnable() {
					@Override
					public void run() {
						invalidate();
					}
				});
			}
		}
	}
	
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new MyView(this);
		setContentView(view);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mainLoop.interrupt();
	}
}
