package com.insthyd.practice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by yangfeng01 on 2018/1/17.
 */

public class AbsBehavior extends CoordinatorLayout.Behavior {

	public AbsBehavior(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
		return super.layoutDependsOn(parent, child, dependency);
	}

	@Override
	public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
		return super.onDependentViewChanged(parent, child, dependency);
	}


	@Override
	public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull
			View directTargetChild, @NonNull View target, int axes) {
		return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes);
	}

	@Override
	public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View
			target, int dx, int dy, @NonNull int[] consumed) {
		super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
	}

	@Override
	public boolean onNestedFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View
			target, float velocityX, float velocityY, boolean consumed) {
		return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
	}

	@Override
	public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
		return super.onInterceptTouchEvent(parent, child, ev);
	}

	@Override
	public boolean onTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
		return super.onTouchEvent(parent, child, ev);
	}

}
