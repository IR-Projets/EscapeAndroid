package entities.weapons;

import org.jbox2d.common.Vec2;

import entities.Entities;
import factories.WeaponFactory.WeaponType;

/**
 * This class represents a Missile. This weapon can is a classic weapon of the player, which is unlimited, because he does the less damage
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
public class Missile extends Weapon {

	/**
	 * Default constructor of a Missile.
	 * @param entities - class which represents our world
	 * @param x - the coordinate associated with x position
	 * @param y - the coordinate associated with y position
	 * @param firedByPlayer - a boolean, which is true if the weapon is fired by the player
	 */
	public Missile(Entities entities, int x, int y, boolean firedByPlayer) {
		super(entities, EntityShape.Circle, WeaponType.Missile.getImage(), x, y, 10, firedByPlayer);
		setImage(resize(getImage(), 2f));
		if(!firedByPlayer)
			getBody().setTransform(new Vec2(x,y), (float) Math.toRadians(180));
	}

	@Override
	public void compute() {
		return;
	}
}
