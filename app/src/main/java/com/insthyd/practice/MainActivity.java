package com.insthyd.practice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.navigation).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity.this, StickyHeaderActivity.class));
			}
		});

		// Example of a call to a native method
		//TextView tv = (TextView) findViewById(R.id.sample_text);
		//tv.setText(stringFromJNI());
	}


	/**
	 * A native method that is implemented by the 'native-lib' native library,
	 * which is packaged with this application.
	 */
	public native String stringFromJNI();

	// Used to load the 'native-lib' library on application startup.
	static {
		System.loadLibrary("native-lib");
	}
}
