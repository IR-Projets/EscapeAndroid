package entities;

import org.jbox2d.dynamics.Body;

import listeners.CollisionListener.EntityType;

/**
 * This class contains all parameters of collision group.
 * His main method set the collision group of a body.
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
public class CollisionGroup {

	/**
	 * Player collision group.
	 */
	public final static int PLAYER_COLLISION = 1;
	
	/**
	 * Enemy collision group.
	 */
	public final static int ENEMY_COLLISION = 2;
	
	/**
	 * Weapon player collision group.
	 */
	public final static int WEAPON_PLAYER_COLLISION = 4;
	
	/**
	 * Weapon enemy collision group.
	 */
	public final static int WEAPON_ENEMY_COLLISION = 8;
	
	/**
	 * Boss collision group.
	 */
	public final static int BOSS_COLLISION = 16;
	
	/**
	 * Bonus collision group.
	 */
	public final static int BONUS_COLLISION = 32;
	
	/**
	 * World limit collision group.
	 */
	public final static int WORLD_LIMIT_COLLISION = 64;
	
	/**
	 * This method set the group collision of a body, using the maskBits and categoryBits of Jbox.
	 * @param body - the body to set at the good group collision
	 * @param entityType - the type of group that the body will be added
	 */
	public static void setCollisionGroup(Body body, EntityType entityType){
		switch (entityType) {
		case Joueur:
			body.getFixtureList().getFilterData().categoryBits = PLAYER_COLLISION;
			body.getFixtureList().getFilterData().maskBits = ENEMY_COLLISION | BOSS_COLLISION | WEAPON_ENEMY_COLLISION | BONUS_COLLISION | WORLD_LIMIT_COLLISION;
			break;
		case Enemy:
			body.getFixtureList().getFilterData().categoryBits = ENEMY_COLLISION;
			body.getFixtureList().getFilterData().maskBits = PLAYER_COLLISION | WEAPON_PLAYER_COLLISION | WORLD_LIMIT_COLLISION;
			break;			
		case WeaponPlayer:
			body.getFixtureList().getFilterData().categoryBits = WEAPON_PLAYER_COLLISION;
			body.getFixtureList().getFilterData().maskBits = ENEMY_COLLISION | BOSS_COLLISION | WEAPON_ENEMY_COLLISION | WORLD_LIMIT_COLLISION;
			break;				
		case WeaponEnnemy:
			body.getFixtureList().getFilterData().categoryBits = WEAPON_ENEMY_COLLISION;
			body.getFixtureList().getFilterData().maskBits = PLAYER_COLLISION | WEAPON_PLAYER_COLLISION | WORLD_LIMIT_COLLISION;
			break;
		case Boss:
			body.getFixtureList().getFilterData().categoryBits = BOSS_COLLISION;
			body.getFixtureList().getFilterData().maskBits = PLAYER_COLLISION | WEAPON_PLAYER_COLLISION;
			break;
		case Bonus:
			body.getFixtureList().getFilterData().categoryBits = BONUS_COLLISION;
			body.getFixtureList().getFilterData().maskBits = PLAYER_COLLISION | WORLD_LIMIT_COLLISION;
			break;
		case WorldLimit:
			body.getFixtureList().getFilterData().categoryBits = WORLD_LIMIT_COLLISION;
			body.getFixtureList().getFilterData().maskBits = PLAYER_COLLISION | ENEMY_COLLISION | WEAPON_PLAYER_COLLISION | WEAPON_ENEMY_COLLISION | BONUS_COLLISION;
			break;
		default:
			break;
		}
	}
}
