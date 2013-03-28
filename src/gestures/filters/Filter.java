package gestures.filters;

import java.util.List;

import org.jbox2d.common.Vec2;

import entities.ships.Player;
import entities.ships.Ship;

/** This interface represents represents a movement, which represents two methods : 
 * apply the movement and check if the movement print by the player is good enough
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
public interface Filter {
	
	/**
	 * The bornes uses for backoff, drift and arrowmovement. 
	 */
	public static final int FILTER_BORNES = 10;/* We refuse affine courbe which increase perpendiculary, with this bornes*/
	/**
	 * Check if a list of Vec2 is correctly positioned, depends on what kind on filter we want to apply
	 * @param trace
	 * @return boolean
	 */
	public boolean check(List<Vec2> trace);

	/**
	 * Apply the movement of the Filter at the Ship
	 * @param ship
	 * @see Ship
	 */
	public void apply(Player ship);
}

