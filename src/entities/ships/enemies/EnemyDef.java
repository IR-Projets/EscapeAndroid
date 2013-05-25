package entities.ships.enemies;

import android.graphics.Bitmap;


/**
 * The EnemyDef class represents is used for load all the elements of the XML file.
 * This classes is used by EnemyLoader for load the creation of enemies, and LoaderXML for initialize all data
 * 
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
public class EnemyDef {
	public final String name;
	public final String map;
	public final Bitmap image;
	private final EnemyBehavior behavior;
	public final int x, y, life;
	private final int time;
	private final boolean isBoss;

	/**
	 * Initialize our class which represents all data for instantiate an enemy, with his behavior and his moment and position to appear
	 * @param image - the image of the enemy
	 * @param behavior - the behavior of the enemy
	 * @param x - the coordinate associated with x position
	 * @param y - the coordinate associated with y position
	 * @param life - the life of the enemy
	 * @param time - the time before the enemy appear
	 * @param isBoss - a boolean for knows if the enemy is a boss
	 */
	public EnemyDef(String name, String map, Bitmap image, EnemyBehavior behavior, int x, int y, int life, int time, boolean isBoss){
		this.name = name;
		this.map = map;
		this.image = image;
		this.behavior = behavior;
		this.x = x;
		this.y = y;
		this.life = life;
		this.time = time;
		this.isBoss = isBoss;
	}

	/**
	 * Returns the image of the enemy
	 * @return the image of the enemy
	 */
	public Bitmap getImage() {
		return image;
	}

	/**
	 * Returns the behavior of the enemy
	 * @return the behavior of the enemy
	 */
	public EnemyBehavior getBehavior() {
		return behavior;
	}

	/**
	 * Returns the X coordinate where the enemy will appear
	 * @return the X coordinate where the enemy will appear
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the Y coordinate where the enemy will appear
	 * @return the Y coordinate where the enemy will appear
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns the life of the enemy
	 * @return the life of the enemy
	 */
	public int getLife() {
		return life;
	}

	/**
	 * Returns the time when the enemy will appear
	 * @return the time when the enemy will appear
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Returns true if the enemy is a boss
	 * @return true if the enemy is a boss
	 */
	public boolean isBoss(){
		return isBoss;
	}
}
