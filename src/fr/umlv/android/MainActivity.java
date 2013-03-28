package fr.umlv.android;

import game.Variables;

import com.example.escapeandroid.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.view.Menu;

public class MainActivity extends Activity {

	private static MainActivity instance;
	
	public static MainActivity get(){
		return instance;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
