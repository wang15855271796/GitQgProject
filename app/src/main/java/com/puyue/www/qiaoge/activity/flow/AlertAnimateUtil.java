package com.puyue.www.qiaoge.activity.flow;

import android.view.Gravity;

import com.puyue.www.qiaoge.R;

/**
 * Created by Sai on 15/8/9.
 */
public class AlertAnimateUtil {
	private static final int INVALID = -1;

	/**
	 * Get default animation resource when not defined by the user
	 *
	 * @param gravity
	 *            the gravity of the dialog
	 * @param isInAnimation
	 *            determine if is in or out animation. true when is is
	 * @return the id of the animation resource
	 */
	static int getAnimationResource(int gravity, boolean isInAnimation) {
		switch (gravity) {
		case Gravity.BOTTOM:
			return isInAnimation ? R.anim.slide_in_bottoms : R.anim.slide_out_bottoms;
		case Gravity.CENTER:
			return isInAnimation ? R.anim.fade_in_center : R.anim.fade_out_center;
		}
		return INVALID;
	}
}
