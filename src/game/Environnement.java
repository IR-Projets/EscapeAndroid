package game;

import java.util.LinkedList;
import java.util.List;

import android.graphics.Canvas;
import android.view.MotionEvent;

import listeners.EntitiesListener;
import listeners.CollisionListener.EntityType;
import listeners.EnvironnementListener;
import listeners.EnvironnementListener.GameState;
import maps.Map;
import effects.Effects;
import entities.Entities;
import entities.ships.Player;
import entities.ships.enemies.EnemiesLoader;
import gestures.Gesture;
import hud.Hud;

/**
 * This class represents our environment, which is compose by all elements necessary at the game.
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

public class Environnement implements EntitiesListener {


	/**
	 * The background map
	 */
	private final Map map;
	
	/**
	 * We keep an entities, which represents our world, in order to make the creation of entity.
	 */
	private final Entities entities;
	
	/**
	 * The Gesture for manage and control all gesture traced by the player. -> Event manager
	 */
	private final Gesture gesture;		//Gesture/Event manager
	
	/**
	 * The current player
	 */
	private final Player player;
	
	/**
	 * A list of all EnvironnementListener, for indicate the game state changes.
	 * @see EnvironnementListener
	 */
	private final List<EnvironnementListener> gameListener;
	
	/**
	 * The loader of enemies, which create the enemies.
	 */
	private final EnemiesLoader enemiesLoader;
	
	/**
	 * The gameState of the environment.
	 */
	private GameState gameState;


	/**
	 * 
	 * @param world Jbox2d world
	 */
	/**
	 * Create the environment with the associated world .
	 * @param entities - our world
	 * @param map - the map associated
	 * @param player - player associated
	 * @param enemiesLoader - enemies loader associated
	 */
	public Environnement(Entities entities, Map map, Player player, EnemiesLoader enemiesLoader){
		this.gameListener = new LinkedList<EnvironnementListener>();
		this.map = map;
		this.player = player;
		this.entities=entities;
		this.enemiesLoader=enemiesLoader;
		this.gesture = new Gesture(player);
		Hud.get().setPlayer(player);
		entities.addEntitiesListener(this);
	}

	/**
	 * Add an environnementListener to the gameListener.
	 * @param listener - the listener to add
	 */
	public void addListener(EnvironnementListener listener) {
		this.gameListener.add(listener);
	}
	
	/**
	 * Remove a environnementListener to the gameListener.
	 * @param listener - the listener to remove
	 */
	public void removeListener(EnvironnementListener listener) {
		this.gameListener.remove(listener);		
	}
	
	/**
	 * Return the player of the game.
	 * @return the player of the game
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Return our world, entities.
	 * @return our world, entities
	 */
	public Entities getEntities() {
		return entities;
	}
	
	/**
	 * Entities Listener: detect the death of each Entity
	 * @see EntityType
	 */
	@Override
	public void entityRemoved(EntityType type) {
		
		switch (type){
		case Boss:
			gameState = GameState.Win;
			break;
		case Joueur:
			gameState = GameState.Loose;
			break;
		default:
			gameState=null;
			break;
		}
		if(gameState!=null){
			for(EnvironnementListener listener : gameListener){
				listener.stateChanged(gameState);
			}
		}
	}
	
	/**
	 * Render all entities associated
	 * @param graphics draw area
	 */
	public void render(Canvas canvas){
		map.render(canvas);				//The ground (the planet)
		entities.render(canvas);			//All the entities (player too)
		gesture.render(canvas);			//Gesture movements (circle)
		Effects.render(canvas);			//Effect (explosion, cloud, ..)
		Hud.get().render(canvas);			//Health, score, amo
	}

	/**
	 * Send the event to the gesture manager
	 * @param event the event to be handled
	 */
	public void event(MotionEvent event) {
		gesture.event(event);
		Hud.get().event(event);
	}

	
	/**
	 * Compute method call by TestBed: step()
	 */
	public void compute() {
		entities.compute();
		entities.step(Variables.WORLD_TIME_STEP, Variables.WORLD_VELOCITY_ITERATION, Variables.WORLD_POSITION_ITERATION);
		enemiesLoader.compute();
		map.compute();
		Effects.compute();
	}

}




