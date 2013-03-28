package fr.umlv.android;

import java.io.IOException;

import game.Game;
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

public class GraphicsView extends View {

	Toast toast = null;
	
	Game game;
	
	Bitmap bitmap;
	Matrix matrix;
	
	Paint paint;
	RectF oval;
	int size = 50;


	
	public GraphicsView(Context context, AttributeSet attrs) throws IOException 
	{
		super(context, attrs);
		init();		
	}

	
	private void init() throws IOException 
	{
		paint = new Paint(0);
		paint.setColor(Color.argb(255, 0, 0, 0));
		paint.setMaskFilter(new BlurMaskFilter(5, BlurMaskFilter.Blur.NORMAL));
		matrix = new Matrix();
		
		game = new Game();
	}

	
	@Override
	protected void onDraw(Canvas canvas) 
	{
		super.onDraw(canvas);
		canvas.drawBitmap(bitmap, matrix, paint);
		
		game.run(canvas);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) 
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		 
		if(bitmap==null)
			bitmap = Bitmap.createBitmap(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec), Bitmap.Config.ARGB_8888);	
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{				
		
		if(toast==null)
			toast = Toast.makeText(getContext(), "", Toast.LENGTH_SHORT);
		toast.setText("x: " + event.getX() + ", y: " + event.getY());
		toast.show();
		
		
		
		
		invalidate();
		
		return true;	
	}
}
