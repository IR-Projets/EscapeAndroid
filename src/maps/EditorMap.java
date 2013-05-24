package maps;

import game.Variables;
import android.graphics.Bitmap;

public class EditorMap extends Map{

	public EditorMap(Bitmap ground) {
		super(ground, 0);
	}

	public void move(float y){
		if(posY+y<0)
			return;
		if(posY+y+Variables.SCREEN_HEIGHT>ground.getHeight())
			return;
		
		posY += y;
	}
	
	public float getY(){
		return posY;
	}
	
	public int getHeight(){
		return ground.getHeight();
	}
	
	/**
	 * Useless....
	 */
	@Override
	public void computeMap() {
		// TODO Auto-generated method stub		
	}

}
