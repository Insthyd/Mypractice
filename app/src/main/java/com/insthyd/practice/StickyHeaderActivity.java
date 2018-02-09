package com.insthyd.practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangfeng01 on 2018/1/19.
 */

public class StickyHeaderActivity extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sticky_header);
		final StickyTitleLayout stickyNavLayout = (StickyTitleLayout) findViewById(R.id.sticky_layout);

		final RecyclerView rv = (RecyclerView)findViewById(R.id.id_recycler_view);
		final View vAlways = findViewById(R.id.v_always);

		rv.setLayoutManager(new LinearLayoutManager(this));

		List<String> data = new ArrayList<>();
		for(int i = 0; i < 50; i++)
		{
			data.add("item" + i);
		}
		rv.setAdapter(new MyAdapter(this, data));

		vAlways.post(new Runnable() {
			@Override
			public void run() {
				stickyNavLayout.setPlaceholderTitle(vAlways, vAlways.getPaddingTop());
				Log.i("yf", "main recyclerview hieght == " + rv.getHeight());
			}
		});
	}
}
