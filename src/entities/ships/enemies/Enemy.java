package entities.ships.enemies;

import java.util.Random;

import org.jbox2d.common.Vec2;

import android.graphics.Bitmap;

import entities.Entities;
import entities.Entity;
import entities.ships.Ship;
import entities.weapons.Bonus;
import entities.weapons.WeaponItem;
import factories.WeaponFactory.WeaponType;
import game.Variables;
import hud.Hud;

/**
 * the Enemy class represents an Entity for JBox, which is an enemy ship that the player has to kill.
 * He can also drop item.
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
public class Enemy extends Ship{

	private final EnemyBehavior behavior;
	private final Entities entities;
	private final Random rand = new Random();
	private WeaponItem bonusToDrop;
	
	/**
	 * Our Main constructor, which instantiate an enemy with the EntityShape given as parameter.
	 * @param entities - class which represents our world
	 * @param shape - the shape of the enemy
	 * @param image - the image of the weapon
	 * @param x - the coordinate associated with x position
	 * @param y - the coordinate associated with y position
	 * @param life - the life of the enemy
	 * @param behavior - the enemyBehavior associated with this enemy
	 * @see EnemyBehavior
	 */
	public Enemy(Entities entities, EntityShape shape, Bitmap image, int x, int y, int life, EnemyBehavior behavior){	
		super(entities, shape, image, x, y, life);
		this.behavior=behavior;
		this.entities=entities;
		getBody().setTransform(new Vec2(x,y), (float) Math.toRadians(180));
		getBody().setFixedRotation(true);
		setCollisionGroup(EntityType.Enemy);
	}
	
	/**
	 * Our default constructor, which instantiate an enemy with the EntityShape Square.
	 * @param entities - class which represents our world
	 * @param image - the image of the weapon
	 * @param x - the coordinate associated with x position
	 * @param y - the coordinate associated with y position
	 * @param life - the life of the enemy
	 * @param behavior - the enemyBehavior associated with this enemy
	 * @see EnemyBehavior
	 */
	public Enemy(Entities entities, Bitmap image, int x, int y, int life, EnemyBehavior behavior){	
		this(entities, EntityShape.Square, image, x, y, life, behavior);
	}

	/**
	 * DropItem method, which try to create a new randomly item.
	 * Be case, more is high, and less objects appear.
	 * @param proba - the proba applied for know if we drop an item.
	 */
	private void dropItem(int proba){
		if(rand.nextInt() % proba == 0){
			int quantity = rand.nextInt(6)+1;//Random quantity, between 1 and 6
			switch(rand.nextInt()%4){
			case 0:
				bonusToDrop = new WeaponItem(WeaponType.Fireball, quantity);
				break;
			case 1:
				bonusToDrop = new WeaponItem(WeaponType.Missile, quantity);
				break;
			case 2:
				bonusToDrop = new WeaponItem(WeaponType.ShiboleetExtended, quantity);
				break;
			case 3:
				bonusToDrop = new WeaponItem(WeaponType.Shuriken, quantity);
				break;
			}
		}	
	}
	
	
	@Override
	public EntityType getEntityType() {
		return EntityType.Enemy;
	}
	
	@Override
	public int getDamage() {
		return 5;
	}
	
	/**
	 * Create a bonus randomly.
	 */
	@Override
	public void explode(){
		super.explode();
		dropItem(1);//50% of luck to win an object
	}
	
	@Override
	public void collision(Entity entity, EntityType type) {
		switch (type) {
		case WeaponPlayer:
		case Joueur:
			Hud.get().increaseScore(50);
			setLife(getLife()-entity.getDamage());
			if(getLife() <= 0)
				explode();
			break;	
		case WorldLimit:
			Vec2 pos = getScreenPostion();
			if(pos.y+getImage().getHeight()>=Variables.SCREEN_HEIGHT)
				explode();
			break;
		default:
			break;
		}
	}

	@Override
	public void compute() {
		behavior.control(this);
		Vec2 pos = getPositionNormalized();
		if(bonusToDrop!=null){
			Bonus bonusTmp = new Bonus(entities, bonusToDrop, (int)pos.x, (int)pos.y);
			entities.addEntity(bonusTmp);
			bonusTmp.setVelocity(0, -Variables.SHIP_BULLET_VELOCITY);
			bonusToDrop=null;
		}
	}

}
