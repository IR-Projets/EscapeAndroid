package entities.weapons;

import entities.Entities;
import factories.WeaponFactory;
import factories.WeaponFactory.WeaponType;

/**
 * This class represents a ShiboleetExtended. This weapon rotate, and do more damage.
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
public class ShiboleetExtended extends Shiboleet{

	/**
	 * A ShiboleetExtended has a tab of weapon, which represents all his child
	 */
	private final Weapon [] child;
	
	/**
	 * A ShiboleetExtended has a weaponFactory, for instantiate his child into our world which is entities
	 */
	private final WeaponFactory weaponFactory;

	/**
	 * Default constructor of a ShiboleetExtended.
	 * @param entities - class which represents our world
	 * @param x - the coordinate associated with x position
	 * @param y - the coordinate associated with y position
	 * @param firedByPlayer - a boolean, which is true if the weapon is fired by the player
	 * @param quantity - the quantity of the child the ShiboleetExtended has to make
	 */
	public ShiboleetExtended(Entities entities, int x, int y, boolean firedByPlayer, int quantity) {
		super(entities, x, y, firedByPlayer);
		weaponFactory = new WeaponFactory(entities);
		
		child = new Shiboleet[quantity];
		for(int i=0; i<quantity;i++)
			child[i] = weaponFactory.createWeapon(WeaponType.Shiboleet, x, y, firedByPlayer);
	}
	
	/**
	 * Launch the growing of each child
	 */
	@Override
	public void compute(){
		super.compute();
		for(Weapon shib : child)
			shib.compute();	
	}
	
	/**
	 * Shoot all the child of shiboleet, include the parent. The trajectories is left around the direction of the shoot initial.
	 */
	@Override
	public void shoot(double angle, int velocity) {
		if(child.length%2 == 1)
			angle-=5;
		super.shoot(angle, velocity);
		int diffAngle = 10;
		double newAngle = angle-(diffAngle * (double)((child.length)/2));
		int offset=0;
		for(int i=0;i<child.length;i++){
			if(i== child.length/2)
				offset++;
			child[i].shoot(newAngle+(i+offset)*diffAngle, velocity);
		}
		
	}

}
