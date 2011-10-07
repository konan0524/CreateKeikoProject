package com.example.android.surfaceview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SaveAndRestore extends Activity {
	class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
		public MySurfaceView(Context context) {
			super(context);
			getHolder().addCallback(this);
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
		
		void doDraw() {
			Canvas canvas = getHolder().lockCanvas();
			canvas.save(); // ÅöÇ±Ç±Ç≈èÛë‘Çï€ë∂
			canvas.rotate(45.0f);
			Paint paint = new Paint();
			canvas.drawColor(Color.WHITE);
			paint.setColor(Color.BLUE);
			paint.setAntiAlias(true);
			paint.setTextSize(24);
			canvas.drawText("Hello, SurfaceView!", paint.getTextSize(), 0, paint);
			canvas.restore(); // ÅöÇ±Ç±Ç≈èÛë‘Çïúå≥
			getHolder().unlockCanvasAndPost(canvas);
		}
		
	}
	
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MySurfaceView(this));
	}
}
