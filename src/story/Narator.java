package story;
import android.graphics.*;


public class Narator{
	
	//private static int IMAGE_SIZE = 150;
	private static int TEXT_SIZE = 20;
	private static long TIME_SKIP = 150*1000000;
	
	
	private Bitmap[] images;
	private int imageIndex;
	private int posX, posY;
	private int sizeX, sizeY;
	private int textX, textY;
	long time, lastTime;
	
	private Paint paint;
	
	public Narator(Bitmap[] images, int posX, int posY) {
		this.images = images;
		this.posX = posX;
		this.posY = posY;
		this.sizeX = images[0].getWidth();
		this.sizeY = images[0].getHeight();
		this.textX = posX-20;
		this.textY = posY+sizeY+30;
		

		paint = new Paint(0);
		paint.setColor(Color.WHITE);
		paint.setTextSize(TEXT_SIZE);
	}
	
	/*
	 * Draw the narrator with lips movements
	 */
	public void speak(Canvas canvas, String text){
		drawText(canvas, text, textX, textY);
		drawAnimatedImage(canvas);
	}
	/*
	 * Only draw figed narrator
	 */
	public void draw(Canvas canvas){
		canvas.drawBitmap(images[0], new Rect(0, 0, sizeX, sizeY), new Rect(posX, posY, posX+sizeX, posY+sizeY), null);
	}
	
	/*
	 * Private methods
	 */
	private void drawAnimatedImage(Canvas canvas){
		time=System.nanoTime();
		if(time>lastTime+TIME_SKIP){
			lastTime=time;
			imageIndex = (imageIndex+1) % images.length;
		}
		canvas.drawBitmap(images[imageIndex], new Rect(0, 0, sizeX, sizeY), new Rect(posX, posY, posX+sizeX, posY+sizeY), null);
	}	
	
	private void drawText(Canvas canvas, String string, int x, int y){
		String [] lines = string.split("\n");
		for(int i=0; i<lines.length; i++){
			canvas.drawText(lines[i], x, y+i*(TEXT_SIZE+10), paint);
		}	
	}
}
