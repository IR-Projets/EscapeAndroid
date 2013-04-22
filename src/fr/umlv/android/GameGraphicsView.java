package fr.umlv.android;

import java.io.IOException;

import game.Game;
import game.Variables;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class GameGraphicsView extends View {

	Game game;

	
	public GameGraphicsView(Context context, AttributeSet attrs) throws IOException 
	{
		super(context, attrs);		
	}

	
	@Override
	protected void onDraw(Canvas canvas) 
	{
		super.onDraw(canvas);
		
		if(game != null){
			game.run(canvas);
			invalidate();	
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) 
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);	
		Variables.SCREEN_WIDTH = MeasureSpec.getSize(widthMeasureSpec);
		Variables.SCREEN_HEIGHT = MeasureSpec.getSize(heightMeasureSpec);
		
		if(game == null){
			try {
				game = new Game();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
			invalidate();
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{	
		game.event(event);
		return true;	
	}
	
}
