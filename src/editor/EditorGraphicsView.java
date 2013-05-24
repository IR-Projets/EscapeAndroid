package editor;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.example.escapeandroid.R;

import maps.EditorMap;

import game.Environnement;
import game.Variables;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.View.OnDragListener;

public class EditorGraphicsView extends View implements OnDragListener{
	
	public static EditorGraphicsView instance;
	
	public static EditorMap map;
	public static int mapDrawable = R.drawable.earth;
	public static boolean mapChanged;
	
	float scrollY;
	float lastPoint;
	
	float vaissX, vaissY;
	public static List<Enemy> vaisseaux;
	
	public static EditorGraphicsView get(){
		return instance;
	}
	
	public EditorGraphicsView(Context context, AttributeSet attrs) throws IOException 
	{
		super(context, attrs);
		instance = this;
		mapChanged = false;
		this.setOnDragListener(this);
		vaisseaux = new LinkedList<Enemy>();
		//setupViews();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) 
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);	
		Variables.SCREEN_WIDTH = MeasureSpec.getSize(widthMeasureSpec);
		Variables.SCREEN_HEIGHT = MeasureSpec.getSize(heightMeasureSpec);
		
		mapChanged = true;
	}
	
	@Override
	protected void onDraw(Canvas canvas) 
	{
		super.onDraw(canvas);
		
		if(mapChanged == true){
			mapChanged = false;
			map = new EditorMap(BitmapFactory.decodeResource(getResources(), mapDrawable));
			invalidate();
		}
		
		map.render(canvas);
		
		for(Enemy enemy : vaisseaux){
			enemy.draw(canvas, map.getY());
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{	
	    int eventAction = event.getAction();

	    if(eventAction==MotionEvent.ACTION_DOWN){
	    	lastPoint = event.getY();
	    }	    
	    else if(eventAction==MotionEvent.ACTION_MOVE){
	    	scrollY = lastPoint - event.getY();
	    	map.move(scrollY);
	    	
	    	lastPoint = event.getY();
	    }
	    
	    invalidate();
	    
	    return true;	
	}

	@Override
	public boolean onDrag(View v, DragEvent event) {
		// Defines a variable to store the action type for the incoming event
        final int action = event.getAction();

        // Handles each of the expected events
        switch(action) {

            case DragEvent.ACTION_DRAG_STARTED:
                break;

            case DragEvent.ACTION_DRAG_ENTERED: 
            	break;

            case DragEvent.ACTION_DRAG_LOCATION:
            	break;

            case DragEvent.ACTION_DRAG_EXITED:
            	break;

           case DragEvent.ACTION_DROP:
        	   View view = (View) event.getLocalState();
        	   vaisseaux.add(new Enemy(view, view.getContentDescription().toString(), event.getX(), event.getY() + map.getY()));
        	   invalidate();
        	   break;

           case DragEvent.ACTION_DRAG_ENDED:
        	   break;

           default:
              Log.e("DragDrop","Unknown action type received by OnDragListener.");
              break;
        }
		return true;
	}	
}
