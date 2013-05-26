package game;

import hud.Button;
import hud.Button.ButtonType;

import java.io.IOException;
import java.util.List;

import listeners.EnvironnementListener;
import maps.Map;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;


/**
 * This class represents our Game, which launch all levels, and manage the state of the player (if he died, kill a boss, ...)
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

public class EditorGame extends Game implements EnvironnementListener {

	/**
	 * The environment of the game.
	 */
	private List<Environnement> environnements;

	/**
	 * For the double buffered method
	 */
	//private BufferedImage offscreen;
	//private Graphics2D bufferGraphics; 

	/**
	 * The number of fps for refresh the rate.
	 */
	private final int fps_refreshRate = 20;
	
	/**
	 * The number of fps.
	 */
	private double fps;
	
	/**
	 * the time of the game.
	 */
	private double time = 0;
	
	/**
	 * the number of loop for the FPS.
	 */
	private int loop = 0;
	
	/**
	 * Next iteration to do the render.
	 */
	private long next_game_tick;
	
	/**
	 * A boolean is the game is paused
	 */
	private boolean paused;
	
	/**
	 * A boolean to know if the game is finished
	 */
	public boolean finished;

	/**
	 * The current level of the game.
	 */
	private List<Map> levels;
	
	/**
	 * The pause Button
	 */
	private Button pauseButton;

	
	/**
	 * FOR EDITOR ONLY
	 * @throws IOException
	 */
	public EditorGame(List<Environnement> env, List<Map> levels) throws Exception{	
		this.levels = levels;
		environnements = env;
		environnements.get(0).addListener(this);
		paused=false;
		finished=false;
		next_game_tick = -1;
		pauseButton=new Button(ButtonType.PAUSE, Variables.SCREEN_WIDTH/2-32, 10){
			@Override
			public void pressed() {
				pause();
			}			
		};
	}

	Exception exc = null;
	@Override
	public void stateChanged(GameState state) {
		switch(state){
		case Loose:
			finished=true;
			environnements.get(0).removeListener(this);
			break;
		case Win:
			if(!levels.isEmpty()){
				levels.remove(0);
				environnements.remove(0);
			}
			else
				finished = true;
			environnements.get(0).removeListener(this);
			try{
				environnements.get(0).addListener(this);
			}catch(Exception e){
				exc = e;
			}
			break;
		}

	}
	/**
	 * Launch the game by render it.
	 * @param graphics - the graphics to print on
	 * @throws Exception 
	 */
	public void run(Canvas canvas) throws Exception {	
		if(exc != null)
			throw exc;
		
		if(finished){
			System.exit(0);
		}
		else{
			if(!paused){
				if(next_game_tick==-1)
					next_game_tick = System.currentTimeMillis();

				int loops = 0;
				long currentTime = System.currentTimeMillis();
				while(currentTime > next_game_tick && loops < Variables.MAX_FRAMESKIP) {
					environnements.get(0).compute();
					next_game_tick += Variables.SKIP_TICKS;
					loops++;
				}
			}
			environnements.get(0).render(canvas);
			pauseButton.render(canvas);
		}

		//graphics.drawImage(offscreen, 0, 0, null);
		if(Variables.DEBUG){
			drawFPS(canvas);
		}

	}

	/**
	 * Do a break on the game.
	 */
	public void pause(){
		paused = paused ? false : true;
		next_game_tick=-1;		
	}

	/**
	 * Draw a frame per second into the graphics
	 * @param graphics - the graphics to print on
	 */
	@SuppressWarnings("deprecation")
	public void drawFPS(Canvas canvas){
		loop++;
		if(loop>fps_refreshRate){	//1er image: On recupere le temps
			loop=0;
			time = System.nanoTime();
		}
		else if(loop==1){			//2e image: On calcul le temps ecoule depuis la 1er image
			double now = System.nanoTime();
			fps = 1000000000 / (now-time);
			time = now;
		}	

		//canvas.setColor(Variables.RED);
		//canvas.scale(2, 2);
		Paint paint = new Paint(0);
		paint.setColor(Color.argb(255, 0, 0, 0));
		canvas.drawPosText("fps: " + (int)fps, new float[]{10, 10}, paint);
		//canvas.scale(0.5, 0.5);	
	}

	/**
	 * The event of the game, to launch the game or the environment.
	 * @param event - the MotionEvent, to launch the environment or the story with the event
	 */
	public void event(MotionEvent event) {
		environnements.get(0).event(event);
		pauseButton.event(event);
	}
}
