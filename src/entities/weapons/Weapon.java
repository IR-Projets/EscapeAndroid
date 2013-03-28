package entities.weapons;

import org.jbox2d.common.Vec2;

import android.graphics.Bitmap;

import effects.Effects;
import effects.Explosion;
import entities.Entities;
import entities.Entity;

/**
 * This class represents a Weapon. This represents methods use for manage all weapons, for player and enemies
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
public abstract class Weapon extends Entity{
	
	private Bitmap image;
	private int damage;
	private boolean isLaunch;
	private final boolean firedByPlayer;

	/**
	 * Default constructor of a weapon.
	 * @param entities - class which represents our world
	 * @param bodyForm - the EntityShape of the weapon, which is used for create the body into Jbox
	 * @param image - the image of the weapon
	 * @param x - the coordinate associated with x position
	 * @param y - the coordinate associated with y position
	 * @param damage - the damage that cause the conflict of the weapon
	 * @param firedByPlayer - a boolean, for know if the weapon is fired by player
	 */
	public Weapon(Entities entities, EntityShape bodyForm, Bitmap image, int x, int y, int damage, boolean firedByPlayer) {
		super(entities, bodyForm.get(entities, x, y, image.getWidth(), image.getHeight()));
		this.image = image;
		this.firedByPlayer=firedByPlayer;
		isLaunch=false;
		this.damage=damage;
		if(firedByPlayer)
			setCollisionGroup(EntityType.WeaponPlayer);
		else
			setCollisionGroup(EntityType.WeaponEnnemy);
		getBody().isBullet();
	}

	@Override
	public Bitmap getImage(){
		return image;
	}

	/**
	 * Set the image of the weapon.
	 * @param image - the new image of the weapon
	 */
	public void setImage(Bitmap image) {
		this.image = image;
	}
	
	/**
	 * Return true if the weapon has been launched.
	 * @return true if the weapon has been launched
	 */
	public boolean isLaunch() {
		return isLaunch;
	}
	
	/**
	 * Return the damage created by the weapon.
	 * @return damage - the damage of the weapon
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * Set the damage inflicted by the weapon.
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	@Override
	public EntityType getEntityType() {
		if(!firedByPlayer)
			return EntityType.WeaponEnnemy;
		return EntityType.WeaponPlayer;
	}

	/**
	 * By default, we destruct the weapon at the first collision.
	 */
	@Override
	public void collision(Entity entity, EntityType type) {
		Vec2 pos = getScreenPostion();
		Effects.addEffect(new Explosion((int)pos.x, (int)pos.y));
		getEntities().removeEntitie(this);
	}
	
	/**
	 * Resize an image, depending of the coefSize
	 * @param image - the image to resize
	 * @param coefSize - the coefficient for do the resize
	 * @return the image resize
	 */
	public static Bitmap resize(Bitmap image, float coefSize){
//		BufferedImage imageRezise = new BufferedImage((int)(image.getWidth()*coefSize), (int)(image.getHeight()*coefSize), image.getType());
//		AffineTransform at = new AffineTransform();
//		at.scale(coefSize, coefSize);
//		AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
//		return (scaleOp.filter(image, imageRezise));
		return Bitmap.createScaledBitmap(image, (int)(image.getWidth()*coefSize), (int)(image.getWidth()*coefSize), false);
	}

	/**
	 * Shoot the weapon into the world, depending to an angle, and the velocity of the weapon
	 * @param angle - the angle for knows where the weapon will be shooted
	 * @param velocity - the velocity of the weapon
	 */
	public void shoot(double angle, int velocity) {
		float vitX = (float) (Math.cos(Math.toRadians(angle))*velocity);
		float vitY = (float) (Math.sin(Math.toRadians(angle))*velocity);
		setVelocity(vitX, vitY);
		isLaunch = true;
	}

}
