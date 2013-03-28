package entities.weapons;

import android.graphics.Canvas;
import entities.Entities;
import factories.WeaponFactory.WeaponType;
import game.Variables;

/**
 * This class represents a Shiboleet. This weapon is only one weapon, and does not launch separates
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

public class Shiboleet extends Weapon {

	private int loop;//local variables uses for launch the creation during all the game
	private int increase;

	/**
	 * Default constructor of a Shiboleet.
	 * @param entities - class which represents our world
	 * @param x - the coordinate associated with x position
	 * @param y - the coordinate associated with y position
	 * @param firedByPlayer - a boolean, which is true if the weapon is fired by the player
	 */
	public Shiboleet(Entities entities, int x, int y, boolean firedByPlayer) {
		super(entities, EntityShape.Circle, WeaponType.Shiboleet.getImage(), x, y, 8, firedByPlayer);
		loop = 0;
		increase=0;
	}

	@Override
	public void compute() {
		if(isLaunch())
			return;
		loop++;

		if(loop>Variables.LOOP_SKIP/2){			
			loop=0;
			if(increase <2){
				setImage(Weapon.resize(getImage(), 1.5f));	
				increase++;
				setDamage(getDamage()*2);			
			}
		}
	}

	@Override
	public void render(Canvas canvas) {
		super.render(canvas);
		if(isLaunch())
			getBody().setAngularVelocity( 3f);
	}

}
