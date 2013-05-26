package editor;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

public class Enemy implements Comparable<Object>{
	String name;
	View view;
	Bitmap bitmap;
	float posX, posY;
	
	public Enemy(View view, String name, float f, float g){
		this.view = view;
		this.name = name;
		this.posX = f;
		this.posY = g;		
	}
	
	public Enemy(Bitmap bitmap, String name, float f, float g){
		this.bitmap = bitmap;
		this.name = name;
		this.posX = f;
		this.posY = g;		
	}
	
	
	void draw(Canvas canvas, float offsetY){
		Bitmap image;
		if(bitmap==null)
			image = loadBitmapFromView(view);
		else
			image = bitmap;
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


	@Override
	public int compareTo(Object enemy) {
		return name.compareTo(((Enemy) enemy).name);
	}

}
