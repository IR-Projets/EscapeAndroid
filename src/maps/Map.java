package maps;

import game.Variables;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;


/**
 * This class represents an Abstract map, which render the scroll of the map.
 * Has to be implements to in order to generate map.
 * 
 * @author Quentin Bernard et Ludovic Feltz
 */


/* <This program is an Shoot Them up space game, called Escape-IR, made by IR students.>
 *  Copyright (C) <2012>  <BERNARD Quentin & FELTZ Ludovic>

 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

public abstract class Map{

	private final Bitmap ground;
	private float posY;
	private int subImgW;
	private int subImgH;
	private float velocity;

	/**
	 * The time of a loop for the method computeMap.
	 */
	public static final int LOOP_SKIP = 300;

	/**
	 * Initialise a map and his scrolling, depending of an image, and a velocity.
	 * @param ground - image to scrolling
	 * @param velocity - velocity of scrolling
	 */
	public Map(Bitmap ground, float velocity){
		this.ground = ground;
		this.velocity=velocity;
		float ratio = 1f; //Doit etre inferieur a 1
		
		subImgW=(int)(Variables.SCREEN_WIDTH * ratio);
		if(subImgW>ground.getWidth())
			subImgW=ground.getWidth();
		
		subImgH=(int)(Variables.SCREEN_HEIGHT * ratio);
		if(subImgH>ground.getHeight())
			subImgH=ground.getHeight();
		
		posY = ground.getHeight() - Variables.SCREEN_HEIGHT;
		if(posY+subImgH>ground.getHeight())
			posY=0;
	}

	/**
	 * Do the scroll of the map.
	 * @param graphics - the graphics to draw on
	 */
	public void render(Canvas canvas){	
		if(posY<0)
			posY=0;

		canvas.drawBitmap(ground, new Rect(0, (int)posY, subImgW, subImgH), new Rect(0, 0, Variables.SCREEN_WIDTH, Variables.SCREEN_HEIGHT), null);
	}
	

	/**
	 * the compute method call to decrease the position in Y coordinate.
	 */
	public void compute() {
		posY-=velocity;
		computeMap();
	}
	
	public abstract void computeMap();
}