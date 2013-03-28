package entities.weapons;

import entities.Entities;
import factories.WeaponFactory.WeaponType;
import game.Variables;

/**
 * This class represents a fireball. This weapon can grow, depending of the time of load.
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

public class Fireball extends Weapon {

	private int loop;//local variable use for launch the creation during all the game
	
	private int increase;//the number of increase 
	
	/**
	 * Default constructor of a fireball.
	 * @param entities - class which represents our world
	 * @param x - the coordinate associated with x position
	 * @param y - the coordinate associated with y position
	 * @param firedByPlayer - a boolean, which is true if the weapon is fired by the player
	 */
	public Fireball(Entities entities, int x, int y, boolean firedByPlayer) {
		super(entities, EntityShape.Circle, WeaponType.Fireball.getImage(), x, y, 8,  firedByPlayer);
		increase=0;
	}

	/**
	 * Do the growth of the fireball, if the weapon is not launch
	 */
	@Override
	public void compute() {
		if(isLaunch())
			return;
		loop++;
		
		if(loop>Variables.LOOP_SKIP/4){			
			loop=0;
			if(increase <3){
				setImage(Weapon.resize(getImage(), 1.5f));
				increase++;
				setDamage(getDamage()*2);
			}
		}
		
	}
}
