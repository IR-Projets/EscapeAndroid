package editor;

import java.util.ArrayList;
import java.util.List;

import maps.Earth;
import maps.Jupiter;
import maps.Map;
import maps.Moon;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import entities.Entities;
import entities.ships.Player;
import entities.ships.enemies.EnemiesLoader;
import factories.ShipFactory;
import fr.umlv.android.GameActivity;
import fr.umlv.android.GameGraphicsView;
import game.EditorGame;
import game.Environnement;
import game.Game;
import game.Variables;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

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
	
	
	public void run(View view){
		
		List<String> scriptFiles = new ArrayList<String>();
		for(int i=0; i<nbLevel+1; i++){
			scriptFiles.add("EnemyNew" + (i+1) + ".xml");		
		}
		
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		
		Variables.SCREEN_WIDTH = width;
		Variables.SCREEN_HEIGHT = height;		
		
		List<Environnement> envs = new ArrayList<Environnement>();
		List<Map> maps = new ArrayList<Map>();
		
		for (int i = 0; i < nbLevel+1; i++) {
			try {
				World world = new World(new Vec2(Variables.WORLD_GRAVITY_X, Variables.WORLD_GRAVITY_Y), false);
				world.setAllowSleep(false);
				
				Entities entities = new Entities(world);
				EnemiesLoader ennemyloader;
				
				ennemyloader = new EnemiesLoader(entities, scriptFiles.get(i), false);
				ShipFactory factory = new ShipFactory(entities);
				Player playerShip = factory.createPlayer();		
				String mapName = ennemyloader.enemysDef.get(0).map;
				Map map = null;
				
				if(mapName.equals("Earth")){
					map = new Earth();
					EditorGraphicsView.mapDrawable = R.drawable.earth;
				}
				if(mapName.equals("Jupiter")){
					map = new Jupiter();
					EditorGraphicsView.mapDrawable = R.drawable.jupiter;
				}
				if(mapName.equals("Moon")){
					map = new Moon();
					EditorGraphicsView.mapDrawable = R.drawable.moon;
				}
				maps.add(map);
				Environnement environnement = new Environnement(entities, map, playerShip, ennemyloader);
				envs.add(environnement);
				
			} catch (Exception e) {
				Toast.makeText(this, "Le level " + i + " ne peut pas être chargé", Toast.LENGTH_LONG).show();
				return;
			}
		}

		
		
		try {
			GameGraphicsView.game = new EditorGame(envs, maps);
		} catch (Exception e) {
			e.printStackTrace();
			finish();
		}
		
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
	}
	
	
	

	public void click(View v){
		Button myButton = (Button) v;
		//Intent intent = new Intent(this, EditorActivity.class);
		Intent intent = new Intent(this, SelectFile.class);
		intent.putExtra("level", myButton.getText());
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
