package jp.main;

/**
 * Game��.
 *
 * @author shuji
 */
/**
 * Game��.
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
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	private class SurfaceCallback implements SurfaceHolder.Callback {
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {

		}

		@Override
		public void surfaceCreated(SurfaceHolder arg0) {
			// TODO �����������ꂽ���\�b�h�E�X�^�u
			Log.d("test", "surfaceCreatetest");

		}

		@Override
		public void surfaceDestroyed(SurfaceHolder arg0) {
			// TODO �����������ꂽ���\�b�h�E�X�^�u
			Log.d("test", "surfaceDestroyedtest");

		}

		void repaint() {

		}
	}

}
