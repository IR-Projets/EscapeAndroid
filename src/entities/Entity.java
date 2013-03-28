package entities;

import game.Variables;

import listeners.CollisionListener;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/** This class represents an entity, which is a Body for Jbox.
 * Contains all methods uses for a body to interact with Jbox, for our entity.
 * 
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

public abstract class Entity implements CollisionListener{	

	/**
	 * Our Entities.
	 */
	private Entities entities;

	/**
	 * The Jbox body associated with our entity.
	 */
	private Body body;

	/**
	 * Initialize an entity, with an Entities and the body of the entity. 
	 * @param entities - class which represents our world
	 * @param body - the Jbox body of our entity
	 */
	public Entity(Entities entities, Body body) {
		this.entities = entities;
		this.body = body;
	}

	/**
	 *  Return the BufferedImage associated with this entity.
	 *  @return the BufferedImage associated with this entity
	 */
	public abstract Bitmap getImage();

	/**
	 * Compute method, uses for launch compute for an entity.
	 */
	public abstract void compute();

	/**
	 * Return the class which represents our world, entities.
	 * @return the class which represents our world, entities
	 */
	public Entities getEntities(){
		return entities;
	}

	/**
	 * Do the render of a entity.
	 * @param graphics - the graphics2D to print on
	 */
	public void render(Canvas canvas){
		/*AffineTransform tx = new AffineTransform();
		tx.rotate(getRotate(), getImage().getWidth()/2, getImage().getHeight()/2);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BICUBIC);
		BufferedImage imageRotated = op.filter(getImage(), null);
*/
		Vec2 pos = getScreenPostion();

		canvas.drawBitmap(getImage(),
				new Rect(0, 0, getImage().getWidth(), getImage().getHeight()),
				new Rect((int)pos.x, (int)pos.y, (int)pos.x + getImage().getWidth(), (int)pos.y + getImage().getHeight()),
				null );
	}

	/**
	 * Return the angle of the body, in radians.
	 * @return the angle of the body, in radians.
	 */
	public float getRotate(){
		return body.getAngle();
	}	

	/**
	 * Return the Jbox body associated with this entity.
	 * @return the Jbox body associated with this entity.
	 */
	public Body getBody(){
		return body;
	}

	/**
	 * Return the velocity of the entity.
	 * @return the velocity of the entity
	 */
	public Vec2 getVelocity(){
		return body.getLinearVelocity();
	}

	/**
	 * Set the collisionGroup of an entity, using CollisionGroup.
	 * @param entityType - the entityType corresponding to the good collisionGroup
	 * @see CollisionGroup
	 */
	public void setCollisionGroup(EntityType entityType){
		CollisionGroup.setCollisionGroup(body, entityType);
	}

	/**
	 * Set the velocity of our entity.
	 * @param speedX - the speed associated with x position
	 * @param speedY - the speed associated with y position
	 */
	public void setVelocity(float speedX, float speedY){
		body.setLinearVelocity(new Vec2(speedX, speedY));
	}

	/**
	 * Set the linear damping of an entity.
	 * @param linearDamping - the linear damping to set
	 */
	public void setDamping(float linearDamping){
		body.setLinearDamping(linearDamping);
	}

	/**
	 * Get the Screen Position of an entity, where the y coordinate is inversed, because of Jbox.
	 * @return the screen position of an entity, with y inverse for Jbox. 
	 */
	public Vec2 getScreenPostion(){
		return new Vec2(body.getPosition().x - getImage().getWidth()/2, 
				Variables.SCREEN_HEIGHT - (body.getPosition().y + getImage().getHeight()/2));
	}

	/**
	 * Get the Screen Position Normalized of an entity, where the y coordinate is not inversed.
	 * @return the screen position of an entity, with y normalize for Jbox.
	 */
	public Vec2 getPositionNormalized(){
		return new Vec2(body.getPosition().x - getImage().getWidth()/2, 
				body.getPosition().y + getImage().getHeight()/2);
	}

	/**
	 * Get the position of the world limit, which are our origin point.
	 * @return the position of the world limit, our origin point
	 */
	public Vec2 getWorldPosition(){
		return body.getPosition();
	}

	/**
	 * Return true is a point is on the sprite of the entity.
	 * @param point - the point to check
	 * @return true if the point is on the sprite, else false
	 */
	public boolean isOnSprite(Vec2 point){
		Vec2 position = getScreenPostion();
		return point.x>position.x && point.x<position.x+getImage().getWidth() &&
				point.y>position.y && point.y<position.y+getImage().getHeight();
	}	

	/**
	 * Stop the movement of the entity.
	 */
	public void stop(){
		setVelocity(0,0);
	}

	/*
	 * Our Body : 
	 */
	
	/**
	 * An entityShape is an enum for represents different shape of Body, for Jbox.
	 */
	public enum EntityShape{
		Square,
		Circle,
		Polygon;

		/**
		 * Return the body associated with this entityShape.
		 * 
		 * @param entities - class which represents our world
		 * @param x - the coordinate associated with x position
		 * @param y - the coordinate associated with y position
		 * @param width - the width associated with this body
		 * @param height - the width associated with this body
		 * @return the body associated with this entityShape
		 */
		public Body get(Entities entities, int x, int y, int width, int height){
			switch(this){
			case Square:
				return getSquareBody(entities, x, y, width, height);
			case Circle:
				return getCircleBody(entities, x, y, width, height);
			case Polygon:
				return getPolygonBody(entities, x, y, width, height);
			}
			return null;
		}
	}

	/**
	 * Return a Square Body associated with our world entities.
	 * 
	 * @param entities - class which represents our world
	 * @param x - the coordinate associated with x position
	 * @param y - the coordinate associated with y position
	 * @param width - the width associated with this body
	 * @param height - the width associated with this body
	 * @return the body created in our world, as a square.
	 */
	private static Body getSquareBody(Entities entities, int x, int y, int width, int height){		
		PolygonShape box = new PolygonShape();		
		box.setAsBox(width/2, height/2);
		return getBody(entities, box, x, y);
	}

	/**
	 * Return a Circle Body associated with our world entities.
	 * 
	 * @param entities - class which represents our world
	 * @param x - the coordinate associated with x position
	 * @param y - the coordinate associated with y position
	 * @param width - the width associated with this body
	 * @param height - the width associated with this body
	 * @return the body created in our world, as a circle.
	 */
	private static Body getCircleBody(Entities entities, int x, int y, int width, int height){		
		CircleShape circle = new CircleShape();
		circle.m_radius = Math.min(width, height)/2;
		return getBody(entities, circle, x, y);
	}

	/**
	 * Return a Polygon Body associated with our world entities.
	 * 
	 * @param entities - class which represents our world
	 * @param x - the coordinate associated with x position
	 * @param y - the coordinate associated with y position
	 * @param width - the width associated with this body
	 * @param height - the width associated with this body
	 * @return the body created in our world, as a polygon.
	 */
	private static Body getPolygonBody(Entities entities, int x, int y, int width, int height){	
		int w = (int) width/2;
		int h = (int) height/2;

		Vec2[] vertices = new Vec2[]
				{
				new Vec2(-w, 0),	//Left
				new Vec2(0, -h),	//Bottom
				new Vec2(w, 0),		//Right
				new Vec2(0, h)		//High
				};

		PolygonShape polygon = new PolygonShape();	
		polygon.set(vertices, 4);
		return getBody(entities, polygon, x, y);
	}

	
	/**
	 * Return a body, created in our world, with the shape corresponding
	 * @param entities - class which represents our world
	 * @param shape - the shape corresponding for manage collision
	 * @param x - the coordinate associated with x position
	 * @param y - the coordinate associated with y position
	 * @return the body created in our world
	 */
	private static Body getBody(Entities entities, Shape shape, int x, int y){

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.linearDamping=0.0f;
		bodyDef.angularDamping=0.5f;
		bodyDef.allowSleep = false;
		bodyDef.position.set(x, y);
		Body body = entities.getWorld().createBody(bodyDef);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1.0f;
		fixtureDef.friction = 0.3f;		
		body.createFixture(fixtureDef);

		return body;
	}
}
