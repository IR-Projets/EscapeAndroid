package entities.weapons;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entities.Entities;
import entities.Entity;
import factories.WeaponFactory.WeaponType;
import game.Variables;

/**
 * This class represents a Bonus, which is created when an enemy died.
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

public class Bonus extends Entity {

	/**
	 * the image of the Bonus.
	 */
	private final BufferedImage imageItem;
	
	/**
	 * The WeapontItem associated with the Bonus, which contains a quantity and a WeaponType
	 * @see WeaponItem
	 * @see WeaponType
	 */
	private final WeaponItem weaponItem;
	
	/**
	 * Default constructor of a Bonus.
	 * @param entities - class which represents our world
	 * @param weaponItem - the WeaoponItem associated with this bonus
	 * @param x - the coordinate associated with x position
	 * @param y - the coordinate associated with y position
	 */
	public Bonus(Entities entities, WeaponItem weaponItem, int x, int y) {
		super(entities, EntityShape.Square.get(entities, x, y, weaponItem.getImage().getWidth(), weaponItem.getImage().getHeight()));
		this.weaponItem = weaponItem;
		
		BufferedImage imageTmp = weaponItem.getImage();
		imageItem =  new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = imageItem.createGraphics();
		
		graphics.drawImage(imageTmp, 0, 2, null);
		graphics.setColor(Variables.BLACK);
		graphics.drawOval(0, 0, 29, 29);
		
		graphics.setColor(Variables.WHITE);
		graphics.drawString(String.valueOf(getQuantity()), 20, 23);//display the amount of the item
		
		setCollisionGroup(EntityType.Bonus);
	}

	@Override
	public EntityType getEntityType() {
		return EntityType.Bonus;
	}

	@Override
	public int getDamage() {
		return 0;
	}

	/**
	 * Only be destruct when collide with the world, or be catch by the player.
	 */
	@Override
	public void collision(Entity entity, EntityType type) {
		if(type == EntityType.WorldLimit)
			getEntities().removeEntitie(this);
	}

	@Override
	public BufferedImage getImage() {
		return imageItem;
	}

	@Override
	public void compute() {
	}

	/**
	 * Return the type of weapon represented by the bonus
	 * @return the type of weapon represented by the bonus
	 */
	public WeaponType getWeaponType() {
		return weaponItem.getWeaponType();
	}

	/**
	 * Return the quantity that this Bonus will be add.
	 * @return the quantity that this Bonus will be add.
	 */
	public int getQuantity() {
		return weaponItem.getQuantity();
	}

}
