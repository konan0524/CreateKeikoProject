package com.example.android.surfaceview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceView;

public class KeyEventSample extends Activity {
	class MySurfaceView extends SurfaceView {
		Paint paint = new Paint();
		String text = "";

		public MySurfaceView(Context context) {
			super(context);
			setFocusable(true); // キーイベントを使うために必須
			requestFocus(); // フォーカスを当てないとイベントを拾わない
			paint.setColor(Color.BLUE);
			paint.setAntiAlias(true);
			paint.setTextSize(24);
		}

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			Log.d("TEST", "onKeyDown");
			if (keyCode == KeyEvent.KEYCODE_BACK) { // BACK キーは戻る処理に割り当て
				return false;
			}
			text = "onKeyDown: keycode[" + keyCode + "]";
			redraw();
			return true;
		}
		
		@Override
		public boolean onKeyUp(int keyCode, KeyEvent event) {
			Log.d("TEST", "onKeyUp");
			return true;
		}
		
		@Override
		public boolean onKeyLongPress(int keyCode, KeyEvent event) {
			Log.d("TEST", "onKeyLongPress");
			return true;
		}

		@Override
		public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
			Log.d("TEST", "onKeyMultiple");
			return true;
		}
		
		void redraw() {
			Canvas canvas = getHolder().lockCanvas();
			canvas.drawColor(Color.WHITE);
			canvas.drawText(text, 0, paint.getTextSize(), paint);
			getHolder().unlockCanvasAndPost(canvas);
		}
	}
	
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MySurfaceView(this));
	}
}
