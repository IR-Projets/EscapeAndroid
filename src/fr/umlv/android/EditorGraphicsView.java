package fr.umlv.android;

import java.io.IOException;

import com.example.escapeandroid.R;

import maps.Earth;
import maps.EditorMap;
import maps.Map;

import game.Game;
import game.Variables;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.MeasureSpec;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class EditorGraphicsView extends View{
	
	EditorMap map;
	float scrollY;
	float lastPoint;
	
	public EditorGraphicsView(Context context, AttributeSet attrs) throws IOException 
	{
		super(context, attrs);
		//setupViews();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) 
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);	
		Variables.SCREEN_WIDTH = MeasureSpec.getSize(widthMeasureSpec);
		Variables.SCREEN_HEIGHT = MeasureSpec.getSize(heightMeasureSpec);
		
		if(map == null){
			map = new EditorMap(BitmapFactory.decodeResource(getResources(), R.drawable.earth));
			invalidate();
		}
	}
	
	
	
	@Override
	protected void onDraw(Canvas canvas) 
	{
		super.onDraw(canvas);
		map.render(canvas);
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
	
	
	
	/*
	 * FOR DRAG AND DROP IN EDITOR...
	 */
	
	private void setupViews() {
		ImageView i1 = (ImageView) findViewById (R.id.imageView1);
		ImageView i2 = (ImageView) findViewById (R.id.imageView2);
		
		OnDragListener listener = new View.OnDragListener() {
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
		        	   break;

		           case DragEvent.ACTION_DRAG_ENDED:
		        	   break;

		           default:
		              Log.e("DragDrop Example","Unknown action type received by OnDragListener.");
		              break;
		        }
				return false;
			}
		};
		
		i1.setOnDragListener(listener);
		i2.setOnDragListener(listener);
	}

	
}
