package entities.weapons;

import java.awt.Graphics2D;

import entities.Entities;
import factories.WeaponFactory.WeaponType;
/**
 * This class represents a Shuriken. This weapon rotate, and do more damage.
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
public class Shuriken extends Weapon{

	
	 /** Default constructor of a Shuriken.
	 * @param entities - class which represents our world
	 * @param x - the coordinate associated with x position
	 * @param y - the coordinate associated with y position
	 * @param firedByPlayer - a boolean, which is true if the weapon is fired by the player
	 */
	public Shuriken(Entities entities, int x, int y, boolean firedByPlayer) {
		super(entities, EntityShape.Circle, WeaponType.Shuriken.getImage(), x, y, 20, firedByPlayer);
		setImage(resize(getImage(), 2f));
	}
	
	@Override
	public void compute() {
		return;
	}
	
	@Override
	public void render(Graphics2D graphics) {
		super.render(graphics);
		getBody().setAngularVelocity( 3f);
	}
	
}
