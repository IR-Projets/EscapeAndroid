package fr.umlv.android;

import java.io.IOException;

import editor.EditorActivity;

import game.Game;
import game.Variables;
import android.app.Activity;
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

	public static Game game;

	
	public GameGraphicsView(Context context, AttributeSet attrs) throws IOException 
	{
		super(context, attrs);	
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) 
	{
		super.onDraw(canvas);
		initGame();
		
		if(game != null){
			try{
				game.run(canvas);
			}catch(Exception e){
				e.printStackTrace();
				Activity host = (Activity)getContext();
				host.finish();
			}
			if(game.finished){
				game = null;
				EditorActivity.environnement = null;
				Activity host = (Activity)getContext();
				host.finish();				
			}
			invalidate();	
		}
	}

	private void initGame(){
		if(game == null || game.finished){
			try {
				if(EditorActivity.environnement != null)
					game = new Game(EditorActivity.environnement);
				else
					game = new Game();
			} catch (Exception e) {
				e.printStackTrace();
				Activity host = (Activity)getContext();
				host.finish();
			}
			invalidate();
		}
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) 
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);	
		Variables.SCREEN_WIDTH = MeasureSpec.getSize(widthMeasureSpec);
		Variables.SCREEN_HEIGHT = MeasureSpec.getSize(heightMeasureSpec);
		
		initGame();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{	
		game.event(event);
		return true;	
	}
	
}
