package story;

import game.Ressources;
import game.Variables;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;





public class BackGround {

	private Bitmap image;

	public BackGround(int idImage) {
		image = Ressources.getImage(idImage);
		
	}
	
	public void render(Canvas canvas){
		//loop++;
		//if(loop>TIME_SKIP){
		//	if(imageX<image.getWidth())
		//		imageX++;
		//	loop=0;
		//}
		//canvas.drawBitmap(image, imageX, imageY,  null);
		canvas.drawBitmap(image, new Rect(0, 0, image.getWidth(), image.getHeight()), new Rect(0, 0, Variables.SCREEN_WIDTH, Variables.SCREEN_HEIGHT), null);
	}
}
