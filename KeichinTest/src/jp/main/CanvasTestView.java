package jp.main;

/**
 * Game盤.
 *
 * @author shuji
 */
/**
 * Game盤.
 *
 * @author konan
 */

import android.content.Context;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CanvasTestView extends SurfaceView {

	public CanvasTestView(Context context) {
		super(context);
		getHolder().addCallback(new SurfaceCallback());
		setFocusable(true);
		requestFocus();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	private class SurfaceCallback implements SurfaceHolder.Callback {
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {

		}

		@Override
		public void surfaceCreated(SurfaceHolder arg0) {
			// TODO 自動生成されたメソッド・スタブ
			Log.d("test", "surfaceCreatetest");

		}

		@Override
		public void surfaceDestroyed(SurfaceHolder arg0) {
			// TODO 自動生成されたメソッド・スタブ
			Log.d("test", "surfaceDestroyedtest");

		}

		void repaint() {

		}
	}

}
