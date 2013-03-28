package entities.ships.enemies;

import factories.WeaponFactory.WeaponType;
import game.Variables;

import java.util.List;

/**
 * the EnemyBehavior class represents the behavior of an enemy.
 * Each behavior is permanently launch, until the death of the enemy associated with.
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
public class EnemyBehavior {

	private int loop, step;//local variables uses for launch the creation during all the game

	private final List<Action> actions;
	private final int repeatTime;

	/**
	 * Default constructor of a behavior, whose need the list of action to apply and the repeatTime
	 * @param actions - list of actions to launch
	 * @param repeatTime - repeatTime for knows the frequency of repeat of all actions
	 */
	public EnemyBehavior(List<Action> actions, int repeatTime){
		this.actions = actions;
		this.repeatTime=repeatTime;
		step=loop=0;
	}

	/**
	 * Default method launch by the enemy for check if we do the good action
	 * @param enemy - the enemy associated with the check
	 * @see Enemy
	 */
	public void control(Enemy enemy){
		loop++;

		if(loop>Variables.LOOP_SKIP){
			loop=0;
			step=(step+1)%repeatTime;

			for(Action action : actions){
				if(step >= action.getBeg() && step <= action.getEnd()){
					switch(action.getType()){
					case SHOOT:
						enemy.loadWeapon(WeaponType.convert(action.getName()), false);
						enemy.shootWeapon(action.getAngle(), action.getVelocity());
						break;
					case MOVE:
						enemy.move(action.getAngle(), action.getVelocity());
						break;
					}
				}
			}
		}

	}
}
