package gestures.filters;

import java.util.List;

import org.jbox2d.common.Vec2;

import entities.ships.Player;
import game.Variables;
import static gestures.filters.Filters.checkAngleBornes;
/**
 * The ArrowMovement is a movement vertical (from left to right or right to left), or a movement which goes to the top of the screen.
 * 
 * @author Quentin Bernard et Ludovic Feltz
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
public class ArrowMovement implements Filter{

	private double angle;

	/**
	 * Check if the angle of the Right is correctly for an Arrow Movement : (0,90,180) degree +- FILTER_BORNES
	 * @return true if the angle is correct
	 */
	private boolean checkAngle() {
		int bornes = FILTER_BORNES;
		return checkAngleBornes(angle, 0d, bornes) || checkAngleBornes(angle, 90d, bornes) || checkAngleBornes(angle, 180d, bornes);
	}

	@Override
	public boolean check(List<Vec2> trace) {
		if(!trace.isEmpty()){
			angle = Filters.getAngle(trace);
			if(Filters.isAffine(trace) && checkAngle())
				return true;
		}
		return false;
	}

	@Override
	public void apply(Player ship) {
		
		
		int vitesse=Variables.SHIP_VELOCITY;
		if(angle < 45)
			ship.setVelocity(vitesse, 0);
		else if (angle > 45 && angle < 135)
			ship.setVelocity(0, vitesse);
		else
			ship.setVelocity(-vitesse, 0);
	}

}
