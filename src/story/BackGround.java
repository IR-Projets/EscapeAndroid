package story;

import game.Ressources;
import game.Variables;
import android.graphics.*;





public class BackGround {

	private static long TIME_SKIP = 20;	
	private int loop;
	private Bitmap image;
	private int imageX;
	private int imageY;
	
	public BackGround(int idImage) {
		loop=0;
		image = Ressources.getImage(idImage);
		
		imageX=Variables.SCREEN_WIDTH - image.getWidth();
		imageY=Variables.SCREEN_HEIGHT - image.getHeight();
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
