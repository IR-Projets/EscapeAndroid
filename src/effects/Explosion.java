package effects;

import game.Ressources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.escapeandroid.R;

/**
 * The Explosion effects implements the class Effects, and do the explosion effect.
 * This class is used for the effect of each destruction of ship.
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

public class Explosion extends Effects {

	/**
	 * the time between the changement of image during an explosion.
	 */
	private static int TIME_SLOW = 5;
	
	/**
	 * A tab of images, for doing the render with different sprite.
	 */
	private Bitmap[] images;
	
	/**
	 * the index of the current image to render.
	 */
	private int currentImage;
	
	/**
	 * the index of the current iteration, for avoid change of sprite too speedy.
	 */
	private int currentIte;
	
	/**
	 * the coordinate associated with x position.
	 */
	private int x;
	
	/**
	 * the coordinate associated with y position.
	 */
	private int y;
	
	/**
	 * Default constructor of an explosion, which initialise a tab of 4 images, with different size of explosions.
	 * @param x - the coordinate associated with x position
	 * @param y - the coordinate associated with y position
	 */
	public Explosion(int x, int y){
		this.x=x;
		this.y=y;
		currentImage = currentIte = 0;
		images = new Bitmap[4];
		images[0] = Ressources.getImage(R.drawable.fire0);
		images[1] = Ressources.getImage(R.drawable.fire1);
		images[2] = Ressources.getImage(R.drawable.fire2);
		images[3] = Ressources.getImage(R.drawable.fire3);
	}
	
	
	@Override
	public void renderEffect(Canvas canvas) {		
		canvas.drawBitmap(images[currentImage], x, y, null);
	}

	/**
	 * Change the image of the explosion.
	 */
	@Override
	public void computeEffect() {
		currentIte = ++currentIte % TIME_SLOW;
		if(currentIte==0){
			currentImage++;
		}
	}
	
	@Override
	public boolean terminated() {
		return currentImage==images.length-1 && currentIte==TIME_SLOW-1;
	}

}
