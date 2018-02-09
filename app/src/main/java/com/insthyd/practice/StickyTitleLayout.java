package com.insthyd.practice;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.TextView;

/**
 * Created by yangfeng01 on 2018/1/19.
 */

public class StickyTitleLayout extends LinearLayout implements NestedScrollingParent {

	private ImageView headerImage;
	private TextView tvTitle;
	private RecyclerView recyclerView;
	private View placeholderTitle;

	private int maxScrollLength;
	private int statusBarHeight;
	private int imageHeight;

	private OverScroller scroller;

	public StickyTitleLayout(Context context) {
		super(context);
		init();
	}

	public StickyTitleLayout(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public StickyTitleLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		scroller = new OverScroller(getContext());
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		imageHeight = headerImage.getMeasuredHeight();
	}

	@Override
	protected void onFinishInflate() {
		headerImage = findViewById(R.id.id_header_image);
		tvTitle = findViewById(R.id.id_title);
		recyclerView = findViewById(R.id.id_recycler_view);
		super.onFinishInflate();
	}

	@Override
	public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
		return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
	}

	@Override
	public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
		boolean consumeScrollUp = dy > 0 && getScrollY() < maxScrollLength;
		boolean consumeScrollDown = dy < 0 && getScrollY() > 0 && !ViewCompat.canScrollVertically(recyclerView, -1);
		if (consumeScrollUp || consumeScrollDown) {
			scrollBy(0, dy);
			consumed[1] = dy;
		}
	}

	@Override
	public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
		super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
	}

	@Override
	public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
		boolean consumeScrollUp = velocityY > 0 && getScrollY() < maxScrollLength;
		boolean consumeScrollDown = velocityY < 0 && getScrollY() > 0;
		if (consumeScrollUp) {
			flinging((int) velocityY, maxScrollLength);
			return true;
		}
		if (consumeScrollDown) {
			flinging((int) velocityY, 0);
		}
		return false;
	}

	private void flinging(int velocityY, int maxY) {
		scroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, maxY);
		invalidate();
	}

	@Override
	public void computeScroll() {
		if (scroller.computeScrollOffset()) {
			scrollTo(0, scroller.getCurrY());
		}
		int[] locations = new int[2];
		tvTitle.getLocationOnScreen(locations);
		if (locations[1] <= statusBarHeight) {
			placeholderTitle.setVisibility(VISIBLE);
		} else {
			placeholderTitle.setVisibility(INVISIBLE);
		}
	}

	@Override
	public void scrollTo(@Px int x, @Px int y) {
		if (y < 0) {
			y = 0;
		}
		if (y > maxScrollLength) {
			y = maxScrollLength;
		}
		if (y != getScrollY()) {
			super.scrollTo(x, y);
		}
	}

	@Override
	public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
		return false;
	}

	public void setPlaceholderTitle(View placeholderTitle, int statusBarHeight) {
		this.placeholderTitle = placeholderTitle;
		this.statusBarHeight = statusBarHeight;
		maxScrollLength = headerImage.getHeight() - statusBarHeight;
	}

}
