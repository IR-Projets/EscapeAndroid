package factories;

import entities.Entities;
import entities.ships.Player;
import entities.ships.enemies.EnemiesLoader;
import game.Environnement;
import game.Variables;
import maps.Earth;
import maps.Jupiter;
import maps.Map;
import maps.Moon;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;


/**
 * This class is a factory of environment, and make our three level : Earth, Moon and Jupiter.
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

public class EnvironnementFactory {
	
	/**
	 * An enum for dissociate each level
	 */
	public enum Level{
		Earth,
		Moon,
		Jupiter
	}

	/**
	 * A boolean for know if we do the break or not.
	 */
	private static final boolean DO_SLEEP = false;

	/**
	 * Create the environment earth, associated with a world Jbox.
	 * @param world - the world associated in Jbox
	 * @return the environment earth
	 * @throws Exception 
	 */
	private static Environnement Earth(World world) throws Exception{
		Entities entities = new Entities(world);
		EnemiesLoader ennemyloader = new EnemiesLoader(entities, "scripts/EnemiesEarth.xml");//xml of ennemies of the earth

		ShipFactory factory = new ShipFactory(entities);
		Player playerShip = factory.createPlayer();

		Map map = new Earth();
		Environnement env = new Environnement(entities, map, playerShip, ennemyloader);
		return env;
	}

	/**
	 * Create the environment moon, associated with a world Jbox.
	 * @param world - the world associated in Jbox
	 * @return the environment earth
	 * @throws Exception 
	 */
	private static Environnement Moon(World world) throws Exception{
		Entities entities = new Entities(world);
		EnemiesLoader ennemyloader = new EnemiesLoader(entities, "scripts/EnemiesMoon.xml");//xml of ennemies of the moon

		ShipFactory factory = new ShipFactory(entities);
		Player playerShip = factory.createPlayer();

		Map map = new Moon();
		Environnement env = new Environnement(entities, map, playerShip, ennemyloader);
		return env;
	}

	/**
	 * Create the environment jupiter, associated with a world Jbox.
	 * @param world - the world associated in Jbox
	 * @return the environment earth
	 * @throws Exception 
	 */
	private static Environnement Jupiter(World world) throws Exception{
		Entities entities = new Entities(world);
		EnemiesLoader ennemyloader = new EnemiesLoader(entities, "scripts/EnemiesJupiter.xml");//xml of ennemies of the jupiter

		ShipFactory factory = new ShipFactory(entities);
		Player playerShip = factory.createPlayer();

		Map map = new Jupiter();
		Environnement env = new Environnement(entities, map, playerShip, ennemyloader);
		return env;
	}
	
	/**
	 * Default factory, use for create a classic environment. Use only for JBox2d Test.
	 * @param world - the world associated in Jbox
	 * @param level - the level to create
	 * @return the environment initialize with the corresponding world and level
	 * @throws Exception 
	 */
	public static Environnement factory(World world, Level level) throws Exception{
		if(world==null){
			world = new World(new Vec2(Variables.WORLD_GRAVITY_X, Variables.WORLD_GRAVITY_Y), DO_SLEEP);
		}
		else{
			world.setGravity(new Vec2(Variables.WORLD_GRAVITY_X, Variables.WORLD_GRAVITY_Y));
		}		
		world.setAllowSleep(DO_SLEEP);

		switch(level){
		case Earth:
			return Earth(world);
		case Moon:
			return Moon(world);
		case Jupiter: 
			return Jupiter(world);
		}
		return null;
	}

	/**
	 * Default factory for the JBox2d Test.
	 * @param level the level to create
	 * @return the Environnement init with the corresponding level
	 * @throws Exception 
	 */
	public static Environnement factory(Level level) throws Exception{
		return factory(null, level);
	}



}
