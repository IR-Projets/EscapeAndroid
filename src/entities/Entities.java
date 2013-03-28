package entities;

import game.Variables;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import listeners.EntitiesListener;
import listeners.CollisionListener.EntityType;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;

import android.graphics.Canvas;


/** This class represents our world, which contains all entity of the Jbox World.
 * He prevents the environment of each delete of entity by the EntitiesListener.
 * Entities -> World (Jbox)
 * Entity -> Body (Jbox)
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

public class Entities {
	/**
	 * The Jbox World.
	 */
	private final World world;
	
	/**
	 * Our worldLimit, represents by a Jbox Body.
	 */
	private final Body worldLimit;
	
	/**
	 * A map for associated one body to one entity, which is our body, in our world.
	 */
	private final Map<Body, Entity> entities = new Hashtable<Body, Entity>();

	/**
	 * The list of Entity to delete.
	 */
	private final List<Entity> entitiesToDelete;	//All entities
	
	/**
	 * The list of Entity to add.
	 */
	private final List<Entity> entitiesToAdd;	//All entities
	
	/**
	 * The entitiesListener, use for prevents all destructions of body.
	 */
	private EntitiesListener entitiesListener;
	
	/**
	 * Default constructor. Init a our world, which is an Entities, with a Jbox World.
	 * @param world - the Jbox World.
	 */
	public Entities(World world){
		entitiesToDelete = new LinkedList<Entity>();
		entitiesToAdd = new LinkedList<Entity>();
		this.world = world;
		worldLimit = setWorldLimit(world);
		world.setContactListener(new ContactListener() {
			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
				Body bodyA = contact.getFixtureA().getBody();
				Body bodyB = contact.getFixtureB().getBody();
							
				Entity entityA = getEntitie(bodyA);
				Entity entityB = getEntitie(bodyB);
				
				//Collision with world limit
				if(bodyA==worldLimit && entityB!=null)
					entityB.collision(null, EntityType.WorldLimit);
				if(bodyB==worldLimit && entityA!=null)
					entityA.collision(null, EntityType.WorldLimit);
					
				//Collision between two entity
				if(entityA!=null && entityB!=null){
					entityA.collision(entityB, entityB.getEntityType());
					entityB.collision(entityA, entityA.getEntityType());
				}
			}			
			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
			}			
			@Override
			public void endContact(Contact contact) {
			}			
			@Override
			public void beginContact(Contact contact) {
			}
		});
	}
	
	/**
	 * Return the Jbox world associated with our world.
	 * @return the Jbox world associated with our world
	 */
	public World getWorld(){
		return world;
	}
	
	/**
	 * Return the body, which is our world Limit.
	 * @return the body, which is our world Limit
	 */
	public Body getWorldLimit(){
		return worldLimit;
	}
	
	/*
	 * Method concerning our collecionts of entity.
	 */
	/**
	 * Return the Entity associated with the body given as parameter.
	 * @param body - the body associated with the entity to return
	 * @return - the entity associated
	 */
	public Entity getEntitie(Body body){
		return entities.get(body);
	}
	
	/**
	 * Add an entity to our world.
	 * @param entity - tthe entity to add.
	 */
	public void addEntity(Entity entity){
		entitiesToAdd.add(entity);
	}
	
	/**
	 * Remove an entity to our world.
	 * @param entity - the entity to remove.
	 */
	public void removeEntitie(Entity entity){
		entitiesToDelete.add(entity);
	}

	/**
	 * Add an entities listener, which will be prevents for each destruction of body.
	 * @param listener - the listener to add.
	 */
	public void addEntitiesListener(EntitiesListener listener) {
		this.entitiesListener = listener;
	}
	
	
	/*
	 * Method of render and compute.
	 */
	
	/**
	 * Do the render of each entity in our world.
	 * @param graphics - graphics the graphics2D to print on
	 */
	public void render(Canvas canvas) {
		for(Entity entitie : entities.values())
			entitie.render(canvas);		
	}

	/**
	 * Launch the compute of each entity in our world.
	 */
	public void compute() {
		for(Entity entitie : entities.values())
			entitie.compute();
	}	
	
	/**
	 * Does a step for the Jbox world. We also add and remove the entity added and removed during the last step.
	 * @param timeStep - the amount of time to simulate of Jbox
	 * @param velocityIteration - the velocity constraint solver of Jbox
	 * @param positionIteration - the position constraint solver of Jbox
	 */
	public void step(float timeStep, int velocityIteration, int positionIteration){
		/*
		 * Delete entities that were collided
		 */
		for(Entity entity : entitiesToAdd)
			entities.put(entity.getBody(), entity);
		entitiesToAdd.clear();
		
		/*
		 * Add entities that were temporaly added
		 */
		for(Entity entity : entitiesToDelete){
			world.destroyBody(entity.getBody());
			entities.remove(entity.getBody());
			entitiesListener.entityRemoved(entity.getEntityType());
		}
		entitiesToDelete.clear();
		world.step(timeStep, velocityIteration, positionIteration);
	}
	
	/**
	 * Set the limit of the body world Limit.
	 * @param world - the Jbox world to set the world limit.
	 * @return the body, which is the world limit.
	 */
	private static Body setWorldLimit(World world){
		BodyDef bd = new BodyDef();
		Body ground = world.createBody(bd);		

		float worldWidth = Variables.SCREEN_WIDTH;
		float worldHeight = Variables.SCREEN_HEIGHT;
		float bordure = Variables.WORLD_BORDER;
		
		PolygonShape shape = new PolygonShape();
		//0,0->width,0
		shape.setAsEdge(new Vec2(-bordure, -bordure), new Vec2(worldWidth+bordure, -bordure));
		ground.createFixture(shape, 0.0f);
		CollisionGroup.setCollisionGroup(ground, EntityType.WorldLimit);
		//Width,0->width,height
		shape.setAsEdge(new Vec2(worldWidth+bordure, -bordure), new Vec2(worldWidth+bordure, worldHeight+bordure));
		ground.createFixture(shape, 0.0f);
		CollisionGroup.setCollisionGroup(ground, EntityType.WorldLimit);
		//width,height->0,height
		shape.setAsEdge(new Vec2(worldWidth+bordure, worldHeight+bordure), new Vec2(-bordure, worldHeight+bordure));
		ground.createFixture(shape, 0.0f);
		CollisionGroup.setCollisionGroup(ground, EntityType.WorldLimit);
		//0,height->0,0
		shape.setAsEdge(new Vec2(-bordure, worldHeight+bordure), new Vec2(-bordure, -bordure));
		ground.createFixture(shape, 0.0f);
		CollisionGroup.setCollisionGroup(ground, EntityType.WorldLimit);
		
		return ground;
	}	
}
