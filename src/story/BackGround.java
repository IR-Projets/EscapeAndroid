package story;

import game.Ressources;
import game.Variables;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;




public class BackGround {

	private static long TIME_SKIP = 20;	
	private int loop;
	private BufferedImage image;
	private int imageX;
	private int imageY;
	
	public BackGround(String imageURL) {
		loop=0;
		image = Ressources.getImage(imageURL);
		
		imageX=Variables.SCREEN_WIDTH - image.getWidth();
		imageY=Variables.SCREEN_HEIGHT - image.getHeight();
	}
	
	public void render(Graphics2D graphics){
		loop++;
		if(loop>TIME_SKIP){
			if(imageX<image.getWidth())
				imageX++;
			loop=0;
		}
		graphics.drawImage(image, imageX, imageY,  null);

	}
}
