package game;

import android.content.res.Resources;
import android.graphics.Color;



/**
 * This class represents all our Global Variables, used during the game.
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

public class Variables {

	/**
	 * Debug mode.
	 */
	public final static boolean DEBUG = false;
	
	/*
	 * Application variables
	 */
	public static final String APPLICATION_NAME = "Escape IR";
	public static final int SCREEN_WIDTH = 500;
	public static final int SCREEN_HEIGHT = 700;
	public static final String IMAGES_URL = "images/";
	
	/*
	 * Render variables
	 */
	public static final int TICKS_PER_SECOND = 25;
	public static final int SKIP_TICKS = 350 / TICKS_PER_SECOND;
	public static final int MAX_FRAMESKIP = 20;
	public static final int LOOP_SKIP = 60;
	
	
	/*
	 * Some static colors
	 */
	public final static int GREEN = Color.GREEN;
	public final static int RED = Color.RED;
	public final static int BLUE = Color.BLUE;
	public final static int WHITE = Color.WHITE;
	public final static int BLACK = Color.BLACK;
	public final static int ORANGE = Color.MAGENTA;
	
	
	/*
	 * Main ship
	 */
	public final static int SHIP_VELOCITY = 1000;/* Speed of the main ship */
	public final static int SHIP_BULLET_VELOCITY = 1000;/* Speed of the main ship */
	public final static float SHIP_DAMPING = 0.4f;
	public final static int SHIP_LIFE = 100;
	
	/*
	 * Gesture variables
	 */
	public final static int TRACE_DELETE_RATE = 5;/*If we increase the value, the delete time of Trace become lower*/
	public final static double TRACE_VARIATION_MAX = 40;/* The limit intervalle to accept movement -> Angle Bornes */
	public static final int TRACE_LENGTH_MIN = 4;
	
	/*
	 * World variables
	 */
	public static final float WORLD_GRAVITY_X = 0;
	public static final float WORLD_GRAVITY_Y = 0;

	public static final float WORLD_TIME_STEP = 1.0f / 60.f;
	public static final int WORLD_VELOCITY_ITERATION = 10;
	public static final int WORLD_POSITION_ITERATION = 8;

	public static final float WORLD_BORDER = 50;
	
	/*
	 * Test
	 */
	public static final int TEST_LAG = 35;	
}
