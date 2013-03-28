package gestures.filters;

import game.Variables;

import java.util.Iterator;
import java.util.List;

import org.jbox2d.common.Vec2;

/** This class is composed by several methods uses for the implementation of the interface Filter.
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
public class Filters{

	/** 
	 * Return the length of the droite represents by the two points pA AND Pb
	 * @param pA
	 * @param pB
	 * @return double value
	 */
	public static double LengthNormalize(Vec2 pA, Vec2 pB){
		return Math.sqrt(Math.pow(pA.x-pB.x, 2)+Math.pow(pA.y-pB.y, 2));
	}

	/**
	 * Return the angle of a Trace
	 * @param trace
	 * @return return the double value in degree (0-360) where 0 is a right to the right, and 180 a right ro the left 
	 */
	public static double getAngle(List<Vec2> trace){
		return getAngle(trace.get(0), trace.get(trace.size()-1));
	}

	/**
	 * The same Method, with two points
	 * @param pBegin
	 * @param pEnd
	 * @return double
	 */
	public static double getAngle(Vec2 pBegin, Vec2 pEnd){
		Vec2 pReal = pEnd.sub(pBegin);

		double angle = Math.atan2(pReal.y, pReal.x);
		if(angle < 0)
			angle = Math.abs(angle);
		else
			angle = 2*Math.PI - angle;
		//System.out.println(Math.toDegrees(angle));
		return Math.toDegrees(angle);
	}

	/**
	 * Check if the angle is in between angleLimit +- bornes
	 * @param angleLimit
	 * @return true if is in the invervalle, else false
	 */
	/**
	 * Check if the angle is in between angleLimit +- bornes
	 * @param angle actual angle to check
	 * @param angleLimit
	 * @param bornes
	 * @return true if is in the invervalle, else false
	 */
	public static boolean checkAngleBornes(double angle, double angleLimit, int bornes){
		if(angle >= angleLimit-bornes && angle <= angleLimit+bornes )
			return true;
		return false;
	}
	
	/**
	 * Check if a right is affine, with a calcul between the difference angle does by the trace
	 * @param trace
	 * @return boolean 
	 */
	public static boolean isAffine(List<Vec2> trace){
		if(trace == null)
			throw new IllegalArgumentException("Trace can't be null");
		
		if(trace.size()<Variables.TRACE_LENGTH_MIN)
			return false;

		Iterator<Vec2> it = trace.iterator();
		boolean firstLoop = true;
		double angleMin =0, angleMax = 0 ;
		Vec2 pActual = null, pLast = it.next();
		while(it.hasNext()){
			int i;
			for(i=0; i<3 && it.hasNext(); i++){// calcul by interval of 4 points
				pActual = it.next();
			}
			double angleActual = getAngle(pLast, pActual);
			if(firstLoop){
				angleMin = angleMax = angleActual;
				firstLoop = false;
			}
			
			angleMin = Math.min(angleActual, angleMin);
			angleMax = Math.max(angleActual, angleMax);
			//System.out.println("Variation" + (angleMax-angleMin ));
			if(angleMax - angleMin>Variables.TRACE_VARIATION_MAX)
				return false;
		}
		return true;
	}	
}
