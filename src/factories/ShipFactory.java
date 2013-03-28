package factories;

import android.graphics.Bitmap;
import entities.Entities;
import entities.ships.Player;
import entities.ships.enemies.Boss;
import entities.ships.enemies.Enemy;
import entities.ships.enemies.EnemyBehavior;

/**
 * This class is a factory of ship, used for create Enemy, Player and Boss.
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

public class ShipFactory extends EntityFactory {

	/**
	 * Default constructor.
	 * Initialize our factory with the world entities.
	  * @param entities - class which represents our world
	 */
	public ShipFactory(Entities entities) {
		super(entities);
	}

	/**
	 * Create a player into our world.
	 * @return the player create
	 */
	public Player createPlayer(){
		Player player = new Player(getEntities());
		createEntity(player);
		return player;
	}

	/**
	 * Create an enemy into our world.
	 * @param image - the image of the weapon
	 * @param x - the coordinate associated with x position
	 * @param y - the coordinate associated with y position
	 * @param life - the life of the enemy
	 * @param behavior - the enemyBehavior associated with this enemy
	 * @return the new enemy create
	 */
	public Enemy createEnnemy(Bitmap image, int x, int y, int life, EnemyBehavior behavior) {
		Enemy enemy = new Enemy(getEntities(), image, x, y, life, behavior);
		createEntity(enemy);
		return enemy;
	}
	
	/**
	 * Create a Boss into our world.
	 * @param image - the image of the weapon
	 * @param x - the coordinate associated with x position
	 * @param y - the coordinate associated with y position
	 * @param life - the life of the enemy
	 * @param behavior - the enemyBehavior associated with this enemy
	 * @return the new enemy create
	 */
	public Boss createBoss(Bitmap image, int x, int y, int life, EnemyBehavior behavior) {
		Boss boss = new Boss(getEntities(), image, x, y, life, behavior);
		createEntity(boss);
		return boss;
	}

}
