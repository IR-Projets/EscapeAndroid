package entities.ships;


import java.awt.image.BufferedImage;

import org.jbox2d.common.Vec2;

import effects.Effects;
import effects.Explosion;
import entities.Entities;
import entities.Entity;
import entities.weapons.Weapon;
import factories.WeaponFactory;
import factories.WeaponFactory.WeaponType;

/**
 * This class represents a ship, which can load and shoot a weapon, move and explode.
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

public abstract class Ship extends Entity{
	
	/**
	 * The life of the ship.
	 */
	private int life;
	
	/**
	 * the BufferedImage of the ship.
	 */
	private final BufferedImage image;
	
	/**
	 * The weapon of the player.
	 */
	private Weapon weapon;
	
	/**
	 * The weaponFactory used for load weapon.
	 */
	private final WeaponFactory weaponFactory;

	/**
	 * Default constructor of a Ship. 
	 * @param entities - class which represents our world
	 * @param bodyForm - the bodyForm of the ship
	 * @param image - the image of the ship
	 * @param x - the coordinate associated with x position
	 * @param y - the coordinate associated with y position
	 * @param life - the life of the ship
	 */
	public Ship(Entities entities, EntityShape bodyForm, BufferedImage image, int x, int y, int life){
		super(entities, bodyForm.get(entities, x, y, image.getWidth(), image.getHeight()));
		this.image= image;
		this.life=life;
		weapon = null;
		weaponFactory = new WeaponFactory(entities);
	}

	@Override
	public BufferedImage getImage(){
		return image;
	}

	/**
	 * Return the current life of a ship.
	 * @return the current life of a ship
	 */
	public int getLife(){
		return life;
	}
	
	/**
	 * Set the life of a ship.
	 * @param life - the new life of the ship.
	 */
	public void setLife(int life){
		this.life = life;
	}

	/**
	 * Load a weapon.
	 * @param weaponType - the Weapon type to load
	 * @param firedByPlayer - if the weapon is fired by player
	 */
	public void loadWeapon(WeaponType weaponType, boolean firedByPlayer){
		double angleRadian = getBody().getAngle();
		int normX = getImage().getWidth()/2;
		int normY = getImage().getHeight()/2;
		float posX, posY;

		if(weaponType == WeaponType.Shiboleet || weaponType == WeaponType.ShiboleetExtended){
			posX = (float) (getPositionNormalized().x+normX);
			posY = (float) (getPositionNormalized().y-normY);
		}
		else{
			posX = (float) (getPositionNormalized().x+normX+Math.cos(angleRadian+Math.PI/2)*normX);
			posY = (float) (getPositionNormalized().y-normY+Math.sin(angleRadian+Math.PI/2)*normY);
		}
		weapon = weaponFactory.createWeapon(weaponType,(int) posX,(int) posY, firedByPlayer);
	}

	/**
	 * Shoot the weapon previously load.
	 * @param angle - the angle to shoot
	 * @param velocity - the velocity of the shoot
	 */
	public void shootWeapon(double angle, int velocity){
		if(weapon != null){
			weapon.shoot(angle, velocity);
			weapon = null;
		}
	}

	/**
	 * Launch the explosion of a ship.
	 */
	public void explode(){
		Vec2 pos = getScreenPostion();
		Effects.addEffect(new Explosion((int)pos.x, (int)pos.y));
		getEntities().removeEntitie(this);
	}

	/**
	 * Launch the move of a ship.
	 * @param angle - the angle to move
	 * @param velocity - the velocity of the movement
	 */
	public void move(double angle, int velocity){
		int vitX = (int) (Math.cos(Math.toRadians(angle))*velocity);
		int vitY = (int) (Math.sin(Math.toRadians(angle))*velocity);
		setVelocity(vitX, vitY);
	}


}
