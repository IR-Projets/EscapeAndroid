package editor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

public class Enemy {
	View view;
	float posX, posY;
	
	public Enemy(View view, float f, float g){
		this.view = view;
		this.posX = f;
		this.posY = g;		
	}
	
	
	void draw(Canvas canvas, float offsetY){
		Bitmap image = loadBitmapFromView(view);
		canvas.drawBitmap(image,
				new Rect(0, 0, image.getWidth(), image.getHeight()),
				new Rect((int)posX - image.getWidth()/2, (int)(posY - image.getHeight()/2 - offsetY), (int)posX + image.getWidth()/2, (int)(posY + image.getHeight()/2 - offsetY)),
				null );
	}
	
	public static Bitmap loadBitmapFromView(View v) {
	    Bitmap b = Bitmap.createBitmap( v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);                
	    Canvas c = new Canvas(b);
	    
	    float oldX = v.getLeft();
	    float oldY = v.getTop();
	    float oldW = v.getRight();
	    float oldH = v.getBottom();	    
	    v.layout(0, 0, v.getWidth(), v.getHeight());
	    v.draw(c);
	    v.layout((int)oldX, (int)oldY, (int)oldW, (int)oldH);
	    return b;
	}

}
