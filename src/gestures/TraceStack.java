package gestures;

import game.Variables;
import gestures.filters.Filter;

import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import android.graphics.Canvas;
/**
 * The TraceStack is compose by a list of Trace.
 * This class does the render of all trace, and manages their states.
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
public class TraceStack {
	
	/**
	 * We stock a List of trace, which represents all traces who has not finish to been removed
	 */
	private List <Trace> oldTraces;
	
	/**
	 * The currentTrace
	 */
	private Trace currentTrace;

	/**
	 * Initialize the Trace and our list of trace
	 */
	public TraceStack(){
		oldTraces = new LinkedList<Trace>();
		currentTrace = new Trace();
	}
	
	/**
	 * Draw all Traces
	 * @param graphics
	 */
	public void render(Canvas canvas) {
		//canvas.setColor(Variables.BLUE);
		currentTrace.render(canvas);
		
		Iterator <Trace> tracesIte = oldTraces.iterator();
		
		while(tracesIte.hasNext()){
			Trace trace = tracesIte.next();
			
			if(trace.isValid()==true)
				graphics.setColor(Variables.GREEN);
			else
				graphics.setColor(Variables.RED);
			
			trace.render(graphics);
			
			trace.removeLastPoint();
			if(trace.isEmpty())
				tracesIte.remove();		//Nothing to remove : we remove the trace
		}
	}

	/**
	 * Check if there are no Trace in process
	 * @return true is there are no trace in process, else false
	 */
	public boolean isEmpty() {
		return currentTrace.isEmpty() && oldTraces.isEmpty();
	}
	
	/**
	 * Return the current Trace
	 * @return the current Trace
	 */
	public Trace getCurrentTrace(){
		return currentTrace;
	}

	/**
	 * Check if the current trace is valid, by checking if the trace is valid for this filter
	 * @param filter
	 * @return true if it's valid, else false
	 */
	public boolean check(Filter filter){
		return currentTrace.checkTrace(filter);
	}
	
	/**
	 * Finish the current Trace
	 */
	public void finishCurrentTrace() {
		oldTraces.add(currentTrace);
		currentTrace = new Trace();
	}

	/**
	 * Return the size of the number of trace in process, including the current Trace.
	 * @return the size of the number of trace in process
	 */
	public int size() {
		if(!currentTrace.isEmpty())
			return oldTraces.size() + 1 ;
		else
			return 0;//No currentTrace, so no OldTrace
	}
	
	
}
