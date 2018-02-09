package com.insthyd.practice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yangfeng01 on 2018/1/18.
 */

public class HeaderTextBehavior extends CoordinatorLayout.Behavior<TextView> {

	private boolean upReach;
	private boolean downReach;
	private int lastPosition = -1;

	public HeaderTextBehavior() {
	}

	public HeaderTextBehavior(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(CoordinatorLayout parent, TextView child, MotionEvent ev) {
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				upReach = false;
				downReach = false;
				break;
		}
		return super.onInterceptTouchEvent(parent, child, ev);
	}

	@Override
	public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull
			View directTargetChild, @NonNull View target, int axes, int type) {
		return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
	}

	@Override
	public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View
			target, int dx, int dy, @NonNull int[] consumed, int type) {
		super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
		if (target instanceof RecyclerView) {
			RecyclerView list = (RecyclerView) target;
			int pos = ((LinearLayoutManager) list.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
			if (pos == 0 && pos < lastPosition) {
				downReach = true;
			}

			if (canScroll(child, dy) && pos == 0) {
				float finalY = child.getTranslationY() - dy;
				if (finalY < -child.getHeight()) {
					finalY = -child.getHeight();
					upReach = true;
				} else if (finalY > 0) {
					finalY = 0;
				}
				child.setTranslationY(finalY);
				consumed[1] = dy;
			}
			lastPosition = pos;
		}
	}

	private boolean canScroll(View child, float scrollY) {
		if (scrollY > 0 && child.getTranslationY() == -child.getHeight() && !upReach) {
			return false;
		}
		if (downReach) {
			return false;
		}
		return true;
	}
}
