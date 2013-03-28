package effects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.graphics.Canvas;

/**
 * This abstract class represents all effects launch during the scrolling of one map.
 * He managed all effects which are this sub class, and launch the render and compute of each.
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

public abstract class Effects {

	/**
	 * Number maximum of layers of our Effects.
	 */
	private static final int MAX_LAYERS = 10;
	
	/**
	 * A tab of list of effects. A tab is used for represents differents layers of our Effects.
	 */
	@SuppressWarnings("unchecked")
	private static List <Effects> layers[] = new ArrayList[MAX_LAYERS];;
	
	/**
	 * Launch the render of each effects of our list, for each layers.
	 * @param graphics
	 */
	public static void render(Canvas canvas){
		for(List<Effects> effects : layers){
			if(effects!=null){
				for(Effects effect : effects){
					effect.renderEffect(canvas);
				}
			}
		}
	}
	
	/**
	 * Launch the compute of each effects of our list, for each layers.
	 */
	public static void compute(){
		for(List<Effects> effects : layers){
			if(effects!=null){
				Iterator<Effects> ite = effects.iterator();	
				while(ite.hasNext()){
					Effects effect = ite.next();
					effect.computeEffect();
					if(effect.terminated()){
						ite.remove();
					}
				}
			}
		}
	}
	
	/**
	 * Add an effect to our list, at the layer indicate.
	 * @param layer - the layer where the effect will be
	 * @param effect - the effect to add
	 */
	public static void addEffect(int layer, Effects effect){
		if(layers[layer]==null)
			layers[layer] = new ArrayList<Effects>();
		layers[layer].add(effect);
	}
	
	/**
	 * Add a lit of effect, to the primary layer.
	 * @param effect - the effect to add
	 */
	public static void addEffect(Effects effect){
		addEffect(0, effect);
	}
	
	/**
	 * The renderEffect that each Effect needs to implements, for the drawing.
	 * @param graphics - the graphics to draw on
	 */
	public abstract void renderEffect(Canvas canvas);
	
	/**
	 * The computeEffect that each Effect needs to implements, for do some compute.
	 */
	public abstract void computeEffect();
	
	/**
	 * A boolean for indicate when the effect is finish.
	 */
	/**
	 * Return a boolean for indicate when the effect is finished.
	 * @return  a boolean for indicate when the effect is finished
	 */
	public abstract boolean terminated();
}
