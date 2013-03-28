package effects;

import game.Ressources;
import game.Variables;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * The basic Effect class is the effect uses for create some sprites during the display of the map.
 * He implements the class Effects.
 * We can add cloud, asteroid, ... all effects which need to appear, but without real body in our world.
 *  * @author Quentin Bernard et Ludovic Feltz
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

public class basicEffect extends Effects {

	private float posX;
	private float posY;
	private float velocity;
	Bitmap image;
	
	/**
	 * Default constructor, which create a basic Effect.
	 * @param fileName - the name of the file which contains the effect
	 * @param velocity - the velocity of the effect
	 */
	public basicEffect(int idResource, float velocity){
		Random rand = new Random();
		this.velocity = velocity;		
		image = Ressources.getImage(idResource);
		posY = - image.getHeight();
		posX= rand.nextInt(Variables.SCREEN_WIDTH + 10 - image.getWidth()/2) - 10;
	}
	
	@Override
	public void renderEffect(Canvas canvas) {
		canvas.drawBitmap(image, (int)posX, (int)posY, null);
	}

	/**
	 * Decrease the position by y with the velocity.
	 */
	@Override
	public void computeEffect(){
		posY+=velocity;
	}

	@Override
	public boolean terminated() {
		return posX>Variables.SCREEN_WIDTH || posX<-image.getWidth() ||	posY>Variables.SCREEN_HEIGHT+image.getHeight();
	}

}
