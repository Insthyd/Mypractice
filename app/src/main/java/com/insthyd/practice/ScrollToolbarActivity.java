package com.insthyd.practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangfeng01 on 2018/1/17.
 */

public class ScrollToolbarActivity extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_scroll_toolbar);

		RecyclerView recyclerView = findViewById(R.id.recycler_view);
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			list.add(i + 1 + "");
		}
		TypeAdapter adapter = new TypeAdapter(this, list, false);
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setAdapter(adapter);
	}
}
