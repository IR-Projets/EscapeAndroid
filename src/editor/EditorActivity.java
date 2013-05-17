package editor;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import maps.EditorMap;
import maps.Jupiter;
import maps.Map;
import maps.Moon;

import com.example.escapeandroid.R;

import entities.Entities;
import entities.ships.Player;
import entities.ships.enemies.EnemiesLoader;
import factories.ShipFactory;
import fr.umlv.android.GameActivity;
import fr.umlv.android.GameGraphicsView;
import game.Environnement;
import game.Variables;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.MeasureSpec;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class EditorActivity extends Activity{
	
	private String scriptFile = "scripts/EnemiesJupiter.xml";
	public static Environnement environnement;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editor);
		setupViews();
	}	
	
	
	public void play(View view){
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		
		Variables.SCREEN_WIDTH = width;
		Variables.SCREEN_HEIGHT = height;		
		
		World world = new World(new Vec2(Variables.WORLD_GRAVITY_X, Variables.WORLD_GRAVITY_Y), false);
		world.setAllowSleep(false);
		
		Entities entities = new Entities(world);
		EnemiesLoader ennemyloader = new EnemiesLoader(entities, scriptFile);//xml of ennemies of the moon

		ShipFactory factory = new ShipFactory(entities);
		Player playerShip = factory.createPlayer();

		Map map = new Jupiter();
		environnement = new Environnement(entities, map, playerShip, ennemyloader);		
		
		GameGraphicsView.game = null;
		
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
	}
	
	public void save(View view){
		
	}
	
	
	
	/*
	 * FOR DRAG AND DROP IN EDITOR...
	 */	
	private void setupViews() {		
		/*
		OnDragListener dragListener = new View.OnDragListener() {		
			Drawable enterShape = getResources().getDrawable(R.drawable.asteroide_small);
			Drawable normalShape = getResources().getDrawable(R.drawable.asteroide_mid);
			
			@Override
			public boolean onDrag(View v, DragEvent event) {
				// Defines a variable to store the action type for the incoming event
		        final int action = event.getAction();

		        // Handles each of the expected events
		        switch(action) {

		            case DragEvent.ACTION_DRAG_STARTED:
		                break;

		            case DragEvent.ACTION_DRAG_ENTERED: 
		            	v.setBackgroundDrawable(enterShape);
		            	break;

		            case DragEvent.ACTION_DRAG_LOCATION:
		            	break;

		            case DragEvent.ACTION_DRAG_EXITED:
		            	v.setBackgroundDrawable(normalShape);
		            	break;

		           case DragEvent.ACTION_DROP:
		               // Dropped, reassign View to ViewGroup
		               View view = (View) event.getLocalState();
		               ViewGroup owner = (ViewGroup) view.getParent();
		               owner.removeView(view);
		               LinearLayout container = (LinearLayout) v;
		               container.addView(view);
		               view.setVisibility(View.VISIBLE);
		        	   break;

		           case DragEvent.ACTION_DRAG_ENDED:
		        	   v.setBackgroundDrawable(normalShape);
		        	   break;

		           default:
		              Log.e("DragDrop","Unknown action type received by OnDragListener.");
		              break;
		        }
				return false;
			}
		};
		
		View view1 = (View) findViewById(R.id.editorGraphicView);
		View view2 = (View) findViewById(R.id.horizontalScrollView1);
		view1.setOnDragListener(dragListener);
		view2.setOnDragListener(dragListener);
		
		*/
		
		
		
		
		OnTouchListener touchListener = new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
					ClipData data = ClipData.newPlainText("", "");
					DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
					view.startDrag(data, shadowBuilder, view, 0);
					//view.setVisibility(View.INVISIBLE);
					return true;
				} else {
					return false;
				}
			}
		};

		ImageView i1 = (ImageView) findViewById(R.id.imageView1);
		ImageView i2 = (ImageView) findViewById(R.id.imageView2);
		ImageView i3 = (ImageView) findViewById(R.id.imageView3);
		ImageView i4 = (ImageView) findViewById(R.id.imageView4);
		ImageView i5 = (ImageView) findViewById(R.id.imageView5);
		ImageView i6 = (ImageView) findViewById(R.id.imageView6);
		ImageView i7 = (ImageView) findViewById(R.id.imageView7);
		ImageView i8 = (ImageView) findViewById(R.id.imageView8);
		
		i1.setOnTouchListener(touchListener);
		i2.setOnTouchListener(touchListener);
		i3.setOnTouchListener(touchListener);
		i4.setOnTouchListener(touchListener);
		i5.setOnTouchListener(touchListener);
		i6.setOnTouchListener(touchListener);
		i7.setOnTouchListener(touchListener);
		i8.setOnTouchListener(touchListener);
	}
	
	
}
