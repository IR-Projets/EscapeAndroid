package editor;

import game.Environnement;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.escapeandroid.R;

public class LevelsActivity extends Activity{

	private int nbLevel = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_levels);

		ImageView image_addButton = (ImageView)findViewById(R.id.add_button);
		image_addButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				addLevel(v);

			}
		});

		ImageView image_subButton = (ImageView)findViewById(R.id.sub_button);
		image_subButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				removeLevel(v);

			}
		});


	}

	public void click(View v){
		Button myButton = (Button) v;
		Intent intent = new Intent(this, EditorActivity.class);
		startActivity(intent);
	}

	private void addLevel(View v){
		if(nbLevel > 4 )
			return;

		nbLevel++;

		Button myButton = getButtonByLevel();

		myButton.setText("Level "+(nbLevel+1));
		myButton.setVisibility(View.VISIBLE);
		
	}

	private void removeLevel(View v){
		if(nbLevel < 1)
			return;
		
		Button myButton = getButtonByLevel();
		myButton.setVisibility(View.INVISIBLE);

		nbLevel--;
	}

	private Button getButtonByLevel() {
		Button myButton = null;
		switch(nbLevel){
		case 1 :
			myButton = (Button)findViewById(R.id.level2);
			break;
		case 2 :
			myButton = (Button)findViewById(R.id.level3);
			break;
		case 3 :
			myButton = (Button)findViewById(R.id.level4);
			break;
		case 4 :
			myButton = (Button)findViewById(R.id.level5);
			break;
		case 5 :
			myButton = (Button)findViewById(R.id.level6);
			break;
		}
		return myButton;
	}

}
