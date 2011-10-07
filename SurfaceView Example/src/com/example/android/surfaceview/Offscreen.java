package com.example.android.surfaceview;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Offscreen extends Activity {
	Thread drawThread;
	
	class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
		Bitmap osb;
		Canvas osc;
		
		public MySurfaceView(Context context) {
			super(context);
			getHolder().addCallback(this);
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
			Log.d("TEST", "surfaceChanged");
		}

		@Override
		public void surfaceCreated(final SurfaceHolder holder) {
			Log.d("TEST", "surfaceCreated");
			final Paint paint = new Paint();
			paint.setColor(Color.RED);
			paint.setAntiAlias(true);
			paint.setTextSize(24);
			final Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.eclair);
			drawThread = new Thread() {
				public void run() {
					try {
						while (true) {
							Canvas canvas = holder.lockCanvas();
							if (osb == null) {
								osb = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.RGB_565);
								osc = new Canvas(osb);
							}
							osc.drawColor(Color.WHITE);
							Calendar cal = Calendar.getInstance();
							String s = String.format("%02d:%02d:%02d.%03d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND), cal.get(Calendar.MILLISECOND));
							osc.drawText(s, 0, paint.getTextSize(), paint);
							osc.drawBitmap(bitmap, 0, paint.getTextSize(), null);
							canvas.drawBitmap(osb, 0, 0, null);
							holder.unlockCanvasAndPost(canvas);
							Thread.sleep(10);
						}
					} catch (Exception e) {
					}
				}
			};
			drawThread.start();
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			Log.d("TEST", "surfaceDestroyed");
		}
	}
	
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MySurfaceView(this));
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (drawThread != null && drawThread.isAlive()) {
			drawThread.interrupt();
		}
	}
}
