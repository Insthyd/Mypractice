package com.insthyd.practice;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yangfeng01 on 2018/1/17.
 */

public class ScrollToolbarBehavior extends CoordinatorLayout.Behavior<TextView> {

	private float deltaY;

	public ScrollToolbarBehavior() {
	}

	public ScrollToolbarBehavior(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
		return dependency instanceof RecyclerView;
	}

	@Override
	public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
		if (deltaY == 0) {
			deltaY = dependency.getY() - child.getHeight();
		}

		float dy = dependency.getY() - child.getHeight();
		dy = dy < 0 ? 0 : dy;

		float y = -(dy / deltaY) * child.getHeight();
		Log.d("yf", "deltaY == " + deltaY);
		Log.i("yf", "setTranslationY : " + y);
		Log.w("yf", "child height == " + child.getHeight());
		Log.e("yf", "dependency y == " + dependency.getY());
		child.setTranslationY(y);

		//float alpha = 1 - (dy / deltaY);
		//child.setAlpha(alpha);

		return true;
	}
}
