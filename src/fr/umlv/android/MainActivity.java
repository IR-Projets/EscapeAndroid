package fr.umlv.android;

import java.util.Locale;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.escapeandroid.R;

public class MainActivity extends Activity {

	private static MainActivity instance;

	private Button french_button, english_button;
	private Button play_button, edit_button;
	public Locale myLocale;


	public static MainActivity get(){
		return instance;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
		setContentView(R.layout.activity_main);	
		addButtonListener();		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	private void addButtonListener() {
		french_button = (Button)findViewById(R.id.french_button);
		english_button = (Button)findViewById(R.id.english_button);
		play_button = (Button)findViewById(R.id.play_button);
		edit_button = (Button)findViewById(R.id.edit_button);


		french_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				myLocale = Locale.FRENCH;
				changeLanguage();
			}
		});

		english_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				myLocale = Locale.ENGLISH;
				changeLanguage();
			}
		});

		play_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setContentView(R.layout.activity_game);	
			}
		});
		
		edit_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setContentView(R.layout.activity_editor);	
			}
		});

	}

	private void changeLanguage (){
		Locale.setDefault(myLocale);
		Configuration config = new Configuration();
		config.locale = myLocale;
		getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

		// refresh UI - get values from localized resources
		((TextView) findViewById(R.id.play_button)).setText(R.string.play_button);
		((TextView) findViewById(R.id.edit_button)).setText(R.string.edit_button);
		((TextView) findViewById(R.id.credits_button)).setText(R.string.credits_button);  
	}

}