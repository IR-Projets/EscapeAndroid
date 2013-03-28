package factories;


import entities.Entities;
import entities.Entity;

/**
 * This class is an abstract entity factory, used to create entity into our world, Entities.
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

public abstract class EntityFactory {

	/**
	 * We keep an entities, which represents our world, in order to make the creation of entity.
	 */
	private Entities entities;
	
	/**
	 * Default constructor.
	 * @param entities - class which represents our world
	 */
	public EntityFactory(Entities entities){
		this.entities=entities;
	}
	
	/**
	 * Return our world.
	 * @return our world
	 */
	protected Entities getEntities(){
		return entities;
	}
	
	/**
	 * Create an entity into our world.
	 * @param entity - class which represents our world
	 */
	protected void createEntity(Entity entity){
		entities.addEntity(entity);
	}

}
