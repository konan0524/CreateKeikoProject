package com.example.android.surfaceview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class TouchAndTrackballEvent extends Activity {
	class MySurfaceView extends SurfaceView {
		float left;
		float top;
		Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.droid);

		public MySurfaceView(Context context) {
			super(context);
			setFocusable(true); // タッチイベントとトラックボールイベントを使うために必須
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			Log.d("TEST", "onTouchEvent");
			left = event.getX();
			top = event.getY();
			doDraw();
			return true;
		}
		
		@Override
		public boolean onTrackballEvent(MotionEvent event) {
			Log.d("TEST", "onTrackballEvent");
			left = event.getX();
			top = event.getY();
			doDraw();
			return true;
		}
		
		void doDraw() {
			Canvas canvas = getHolder().lockCanvas();
			canvas.drawBitmap(bitmap, left, top, null);
			getHolder().unlockCanvasAndPost(canvas);
		}
	}
	
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MySurfaceView(this));
	}
}
