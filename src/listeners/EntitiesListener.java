package listeners;

import listeners.CollisionListener.EntityType;

/**
 * This class represents our listener of Entities, use to manage each destruction of entity.
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

public interface EntitiesListener {
	
	/**
	 * Methods call for each remove of entity in our entities.
	 * @param entity - the entity that was removed.
	 */
	public void entityRemoved(EntityType entity);	
}
