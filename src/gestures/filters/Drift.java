package gestures.filters;

import java.util.List;

import org.jbox2d.common.Vec2;

import entities.ships.Player;
import game.Variables;
import static gestures.filters.Filters.checkAngleBornes;;
/**
 * The drift is a movement vertical which goes to the top of the screen, to the right or the left.
 * We add the same movement, but goes to the bot of the screen, to the right and to the left.
 * Refuse the horizontal and verticals movements
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

public class Drift implements Filter{
	
	private double angle=0;
	
	/**
	 * Check if the angle of the Right is correctly for :
	 * a left drift : drift from left to right and from bottom to top
	 * a right drift : drift from right to left and from bottom to top
	 * a low left drift : drift from left to right and from top to bottom ( New movement!)
	 * a low right drift : drift from right to left and from top to bottom ( New movement!)
	 * 
	 * Refuse the horizontal and vertical angles, with the bornes TRACE_DRIFT_BORNES.
	 * @return true if the angle is correct
	 */
	public boolean checkAngle(){
		int bornes = FILTER_BORNES;
		return !checkAngleBornes(angle, 0d, bornes) && !checkAngleBornes(angle, 90d, bornes) && !checkAngleBornes(angle,180d, bornes) && !checkAngleBornes(angle, 270d, bornes);
	}

	@Override
	public boolean check(List<Vec2> trace){
		if(!trace.isEmpty()){
			angle = Filters.getAngle(trace);
			if(Filters.isAffine(trace) && checkAngle())
				return true;
		}
		return false;
	}
	
	@Override
	public void apply(Player ship) {
		int vitX, vitY;
		if(angle < 90 || angle > 270)
			vitX = Variables.SHIP_VELOCITY;
		else
			vitX = -Variables.SHIP_VELOCITY;
		if(angle <180)
			vitY = Variables.SHIP_VELOCITY;
		else
			vitY = -Variables.SHIP_VELOCITY;
		
		ship.setVelocity(vitX, vitY);		
	}
}
