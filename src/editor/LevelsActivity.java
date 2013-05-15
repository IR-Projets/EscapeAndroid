package editor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
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
	
	
	private void addLevel(View v){
		if(nbLevel > 6 )
			return;
		
		nbLevel++;
		
		Button myButton = new Button(this);
		myButton.setText("Level "+nbLevel);
		myButton.setId(nbLevel);
		
		myButton.setWidth(v.getWidth());
		myButton.setX(v.getWidth()/2);
		myButton.setY((nbLevel+3)*v.getHeight());
		
		
		final LevelsActivity levelsActivity = this;
		myButton.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(levelsActivity, EditorActivity.class);
				startActivity(intent);
			}
		});

		RelativeLayout rl = (RelativeLayout)findViewById(R.id.levels_layout_top);
		/*LayoutParams lp = new LayoutParams(rl.getLayoutParams());
		lp.addRule(RelativeLayout.CENTER_IN_PARENT);*/
		rl.addView(myButton);
		
	}
	
	private void removeLevel(View v){
		if(nbLevel < 1)
			return;
		
		
		RelativeLayout rl = (RelativeLayout)findViewById(R.id.levels_layout_top);
		Button buttonToRemove = (Button)findViewById(nbLevel);
		rl.removeView(buttonToRemove);
		nbLevel--;
	}
	
	
	
}
