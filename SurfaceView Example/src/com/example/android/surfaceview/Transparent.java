package com.example.android.surfaceview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Transparent extends Activity {
	class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
		float left;
		float top;
		Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cupcake);
		
		public MySurfaceView(Context context) {
			super(context);
			getHolder().setFormat(PixelFormat.TRANSPARENT);
			getHolder().addCallback(this);
			setFocusable(true);
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
			Log.d("TEST", "surfaceChanged");
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			Log.d("TEST", "surfaceCreated");
			doDraw();
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			Log.d("TEST", "surfaceDestroyed");
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			Log.d("TEST", "onTouchEvent");
			left = event.getX();
			top = event.getY();
			doDraw();
			return true;
		}
		
		void doDraw() {
			Canvas canvas = getHolder().lockCanvas();
// ÉeÅ[É}ÇÃîwåiêFÇ≈ìhÇËÇ¬Ç‘Ç≥ÇÍÇÈÅAÇ∆Ç¢Ç§Ç±Ç∆ÇÕÇ»Ç¢
//			canvas.drawColor(Color.WHITE);
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
