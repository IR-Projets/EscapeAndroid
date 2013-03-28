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
		
		for(int idPtr=0; idPtr<event.getPointerCount(); idPtr++)
		{
			float x = event.getX(idPtr);
			float y = event.getY(idPtr);
			
			int alpha = 255;
			int red = 0;
			int green = 0;
			int blue = 0;
			
			//Color depends of the pointer (the finger)
			switch(idPtr)
			{
				case 1:
					red=255;
					break;
				case 2:
					green=255;
					break;
				case 3:
					blue=255;
					break;
				case 4:
					red=255;
					blue=255;
					break;
				case 5:
					red=255;
					green=255;
					break;		
				case 6:
					green=255;
					blue=255;
					break;	
				case 7:
					red=100;
					green=255;
					blue=255;
					break;	
				case 8:
					red=200;
					green=200;
					blue=200;
					break;	
				case 9:
					red=100;
					green=200;
					blue=300;
					break;	
				case 10:
					red=300;
					green=200;
					blue=100;
					break;	
			}			
								
			Canvas canvas = new Canvas(bitmap);
			oval = new RectF(x-size/2, y-size/2, x+size/2, y+size/2);
			
			paint.setColor(Color.argb(alpha, red, green, blue));
			canvas.drawOval(oval, paint);				
		}
		
		
		
		invalidate();
		
		return true;	
	}
}
