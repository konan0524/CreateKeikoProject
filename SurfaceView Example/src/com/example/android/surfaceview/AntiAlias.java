package com.example.android.surfaceview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AntiAlias extends Activity {
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
			Canvas canvas = holder.lockCanvas();
			Paint paint = new Paint();
			canvas.drawColor(Color.LTGRAY);
			paint.setColor(Color.BLUE);
			paint.setTextSize(24);

			canvas.drawText("Hello, SurfaceView!", 0, paint.getTextSize(), paint);
			Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cupcake);
			RectF dist = new RectF();
			dist.left = 0;
			dist.top = paint.getTextSize() * 2;
			dist.right = bitmap.getWidth() * 4;
			dist.bottom = bitmap.getHeight() * 4;
			canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), dist, paint);
			canvas.drawBitmap(bitmap, (getWidth() - bitmap.getWidth()) / 2, dist.top, paint);
			dist.left = getWidth() - dist.right;
			dist.right = getWidth();
			paint.setAntiAlias(true);
			paint.setFilterBitmap(true);
			canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), dist, paint);
			canvas.drawText("Hello, SurfaceView!", 0, paint.getTextSize() * 2, paint);
			
			holder.unlockCanvasAndPost(canvas);
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
}
