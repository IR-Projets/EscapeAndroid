package listeners;

import entities.Entity;
/**
 * This class represents our Collision listener, use to manage each collisions.
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

public interface CollisionListener {
	/**
	 * Defin an enum, to dissociate each entity.
	 *
	 */
	public enum EntityType{
		Enemy,
		Boss,
		Joueur,
		Item,
		WeaponPlayer,
		WeaponEnnemy, 
		WorldLimit, 
		Bonus;		
	}
	
	/**
	 * Return the king of entity the entity is.
	 * @return the king of entity the entity is.
	 */
	public EntityType getEntityType();
	
	/**
	 * Return the number of damage the collision provides.
	 * @return the number of damage the collision provides.
	 */
	public int getDamage();
	
	/**
	 * Method call during a collision
	 * @param entity - the entity which is collide.
	 * @param type - the type of Entity that entity collide.
	 */
	public void collision(Entity entity, EntityType type);
	
}
