package com.insthyd.practice;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yangfeng01 on 2018/1/17.
 */

public class MyCustomBehavior extends CoordinatorLayout.Behavior {

	private int id;

	public MyCustomBehavior(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.getResources().obtainAttributes(attrs, R.styleable.MyCustomStyle);
		id = a.getResourceId(R.styleable.MyCustomStyle_anchor_id, -1);
		a.recycle();
	}

	@Override
	public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
		return dependency.getId() == id;
	}

	@Override
	public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
		child.setTranslationY(-dependency.getTop());
		return true;
	}
}
