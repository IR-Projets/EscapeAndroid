package gestures.filters;

import java.util.List;

import org.jbox2d.common.Vec2;

import entities.ships.Player;
import game.Variables;
/**
 * The Backoff is a back movement, which accept a trace with gradient between 260 and 280 degrees
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


public class Backoff implements Filter {
	
	/**
	 * Check if the angle of the Right is correct for backoff
	 * @param angle
	 * @return true if the angle is correct
	 */
	private boolean checkAngle(double angle){
		if((angle >= 270-FILTER_BORNES && angle <= 270+FILTER_BORNES ))
			return true;
		return false;
	}
	
	@Override
	public boolean check(List<Vec2> trace){
		if(!trace.isEmpty()){
			double angle = Filters.getAngle(trace);
			if(Filters.isAffine(trace) && checkAngle(angle))
				return true;
			return false;
		}
		return false;
	}

	@Override
	public void apply(Player ship) {
		ship.setVelocity(0, -Variables.SHIP_VELOCITY);
	}
	
}
