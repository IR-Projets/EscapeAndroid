package entities.ships.enemies;

import java.util.Iterator;
import java.util.List;

import org.omg.CORBA.Environment;

import entities.Entities;
import factories.ShipFactory;
import game.Variables;

/**
 * Have the responsibilities to create all enemies during the game. 
 * Use the EnemyDef class for reap all informations in the XML files.
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
public class EnemiesLoader {

	
	private int loop, step;//local variables uses for launch the creation during all the game

	
	private final List<EnemyDef> enemysDef;
	private final ShipFactory shipFactory;

	/**
	 * Default constructor, which make the EnemiesLoader associate with an XML files which contains EnemyDef definition.
	 * @param entities - class which represents our world
	 * @param filename - the location of the XML file to load
	 */
	public EnemiesLoader(Entities entities, String filename) {
		loop=step=0;
		enemysDef = new LoaderXml().getEnemysFromXml(filename);
		shipFactory = new ShipFactory(entities);
	}

	/**
	 * Default method launch by the environment for create the enemy
	 * The compute method use a clock counter for know when create the enemy
	 * @see Environment
	 */
	public void compute(){
		loop++;
		
		if(loop>Variables.LOOP_SKIP){			
			loop=0;
			step++;
			
			Iterator <EnemyDef> it = enemysDef.iterator();
			while(it.hasNext()){
				EnemyDef enemyLoad = it.next();
				if(step > enemyLoad.getTime()){
					if(enemyLoad.isBoss())
						shipFactory.createBoss(enemyLoad.getImage(), enemyLoad.getX(), enemyLoad.getY(), enemyLoad.getLife(), enemyLoad.getBehavior());
					else
						shipFactory.createEnnemy(enemyLoad.getImage(), enemyLoad.getX(), enemyLoad.getY(), enemyLoad.getLife(), enemyLoad.getBehavior());
					it.remove();
				}
			}
		}
	}
	
	
}
