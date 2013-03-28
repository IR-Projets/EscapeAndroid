package story;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Narator{
	
	private static int IMAGE_SIZE = 100;
	private static int TEXT_SIZE = 5;
	private static long TIME_SKIP = 150*1000000;
	
	
	private BufferedImage[] images;
	private int imageIndex;
	private int posX, posY;
	private int textX, textY;
	long time, lastTime;
	
	public Narator(BufferedImage[] images, int posX, int posY) {
		this.images = images;
		this.posX = posX;
		this.posY = posY;
		this.textX = posX-20;
		this.textY = posY+IMAGE_SIZE+20;
	}
	
	/*
	 * Draw the narrator with lips movements
	 */
	public void speak(Graphics2D g, String text){
		drawText(g, text, textX, textY);
		drawAnimatedImage(g);
	}
	/*
	 * Only draw figed narrator
	 */
	public void draw(Graphics2D g){
		g.drawImage(images[0], posX, posY, IMAGE_SIZE, IMAGE_SIZE, null);
	}
	
	/*
	 * Private methods
	 */
	private void drawAnimatedImage(Graphics2D graphics){
		time=System.nanoTime();
		if(time>lastTime+TIME_SKIP){
			lastTime=time;
			imageIndex = (imageIndex+1) % images.length;
		}
		graphics.drawImage(images[imageIndex], posX, posY, IMAGE_SIZE, IMAGE_SIZE, null);
	}	
	
	private void drawText(Graphics2D graphics, String string, int x, int y){
		String [] lines = string.split("\n");
		for(int i=0; i<lines.length; i++){
			graphics.drawString(lines[i], x, y+i*(TEXT_SIZE+10));
		}	
	}
}