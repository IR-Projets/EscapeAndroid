package gestures.filters;


import entities.ships.Player;
import entities.ships.Player.Direction;
import game.Variables;

import java.util.Iterator;
import java.util.List;

import org.jbox2d.common.Vec2;
/**
 * The Looping is a circle movement, accept with rate of error.
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

public class Looping implements Filter {

	/**
	 * bornes of the diameter Maximum of a circle
	 */
	public static final int TRACE_CIRCLE_BORNES_MAX = 250;

	/**
	 * bornes of the diameter Minimum of a circle
	 */
	public static final int TRACE_CIRCLE_BORNES_MIN = 20;

	/**
	 * rate of error that we accept for the circle
	 */
	public static final double TRACE_CIRCLE_RATE_PERCENTAGE = 0.25;

	/**
	 * bornes of the diameter we accept for variation of point
	 */
	public static final double TRACE_CIRCLE_BORNES = 25;

	/**
	 * number of coordate of difference we accept between the begin and end of the circle
	 */
	public static final double TRACE_CIRCLE_RATE_CLOSED = 80;

	/**
	 * For know the direction of the circle (right or left)
	 */
	private Direction direction;

	/**
	 * Return the direction of a circle, depending of the angle of the firsts points of the trace
	 * @param trace
	 * @return the direction of a circle
	 */
	public Direction getDirection(List<Vec2> trace){
		if(trace.size() < 1)
			return null;
		double angle = Filters.getAngle(trace.get(0), trace.get(1));
		if(angle <= 90 || angle >= 270)
			return Direction.RIGHT;
		else
			return Direction.LEFT;
	}

	/**
	 * Return the Vec2 which represents the Vec2 the farthest from the first point of the Trace 
	 * Use for know the centre and the diamater of a trace
	 * @param trace
	 * @return the Vec2 which represents the Vec2 the farthest from the first point of the Trace 
	 */
	public Vec2 vecDistMax(List<Vec2> trace){
		Iterator<Vec2> it = trace.iterator();
		Vec2 vecBegin = null, vecMax = null;
		if(it.hasNext())
			vecBegin = it.next();

		double lengthMax = 0;
		while(it.hasNext()){
			Vec2 vecActual = it.next();
			if(vecMax == null)
				vecMax = vecActual;
			if(Filters.LengthNormalize(vecBegin, vecActual) >= lengthMax){
				lengthMax = Filters.LengthNormalize(vecBegin, vecActual);
				vecMax=vecActual;
			}
		}
		return vecMax;
	}

	/**
	 * Check if a trace is correctly a circle, with approximation.
	 * All the parameters uses for approximate are the static variables of this class.
	 */
	@Override
	public boolean check(List<Vec2> trace) {
		if(trace.size() < Variables.TRACE_LENGTH_MIN)
			return false;

		Vec2 pDeb = trace.get(0), pDistMax = vecDistMax(trace);

		Vec2 pCenterOrigin = new Vec2((pDistMax.x+pDeb.x)/2, (pDistMax.y+pDeb.y)/2);//pDistMax.sub(pDeb);
		double rayonNormal = Filters.LengthNormalize(pCenterOrigin, pDeb);
		int nbErreur=0;

		Iterator<Vec2> it = trace.iterator();

		while(it.hasNext()){
			Vec2 vecActual = it.next();
			double rayonActual = Filters.LengthNormalize(pCenterOrigin, vecActual);
			if(rayonActual > TRACE_CIRCLE_BORNES_MAX || rayonActual < TRACE_CIRCLE_BORNES_MIN || rayonActual > rayonNormal+TRACE_CIRCLE_BORNES || rayonActual < rayonNormal-TRACE_CIRCLE_BORNES)
				nbErreur++;

			if(nbErreur>trace.size()*TRACE_CIRCLE_RATE_PERCENTAGE){
				return false;
			}
			if(!it.hasNext())
				if(Filters.LengthNormalize(pDeb,vecActual) >= TRACE_CIRCLE_RATE_CLOSED)// If we don't finish properly the circle
					return false;

		}
		direction = getDirection(trace);
		return true;
	}

	@Override
	public void apply(Player ship) {
		switch(direction){
		case LEFT:
			ship.setLooping(Player.Direction.LEFT);
			break;
		case RIGHT:
			ship.setLooping(Player.Direction.RIGHT);
			break;
		default:
			break;

		}
	}

}
