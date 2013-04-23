package editor;

import java.io.IOException;

import com.example.escapeandroid.R;

import maps.EditorMap;

import game.Variables;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

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
}
