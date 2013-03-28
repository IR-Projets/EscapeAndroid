package entities.ships.enemies;

import java.util.Random;

import com.example.escapeandroid.R;

import android.graphics.Bitmap;

import effects.Effects;
import effects.Explosion;
import entities.Entities;
import entities.Entity;
import game.Ressources;
import game.Variables;
import hud.Hud;

/**
 * The Boss class is used for specify an enemy as a Boss, which is uses for determine the end of a level, with his death.
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

public class Boss extends Enemy{

	private final Bitmap[] touchedImages;
	private boolean touched;
	private int step;
	private int TOUCHED_STEP = 100;
	private int currentFrame;
	
	private boolean destroyed;
	private final int destroyedStepMax = 1000;
	private int destroyedStep;
	private Random rand;
	
	/**
	 * Create a boss, which is an extension of an enemy. Be care, A boss enemy is add in the Jbox World by his factory. 
	 * @param entities - class which represents our world
	 * @param image - the image of the boss
	 * @param x - the coordinate associated with x position
	 * @param y - the coordinate associated with y position
	 * @param life - the life of the Boss
	 * @param behavior - the EnemyBehavior of a Boss, which specify his behavior during the game
	 */
	public Boss(Entities entities, Bitmap image, int x, int y, int life, EnemyBehavior behavior) {
		super(entities, EntityShape.Square, image, x, y, life, behavior);
		rand = new Random();
		destroyed=false;
		destroyedStep=0;
		touched=false;
		touchedImages = new Bitmap[3];
		touchedImages[0] = Ressources.getImage(R.drawable.boss_red1);
		touchedImages[1] = Ressources.getImage(R.drawable.boss_red2);
		touchedImages[2] = Ressources.getImage(R.drawable.boss_red3);

		
		setCollisionGroup(EntityType.Boss);
		setVelocity(0, -500);
	}
	
	/**
	 * Return an entityType view as a Boss
	 */
	@Override
	public EntityType getEntityType() {
		return EntityType.Boss;
	}
	
	@Override
	public void explode(){
		destroyed=true;
	}
	
	@Override
	public void collision(Entity entity, EntityType type) {
		switch (type) {
		case Joueur:
		case WeaponPlayer:
			touched=true;
			Hud.get().increaseScore(50);
			setLife(getLife()-entity.getDamage());
			if(getLife() <= 0)
				explode();
			break;
		default:
			break;
		}
	}
	
	
	@Override
	public Bitmap getImage() {		
		step++;
		if(touched){
			return touchedRender();
		}
		else if(destroyed){
			return destroyedRender();
		}
		else{
			return super.getImage();
		}
	}

	private Bitmap destroyedRender() {
		destroyedStep++;
		if(destroyedStep<destroyedStepMax){
			if(rand.nextInt()%4==0)
				Effects.addEffect(new Explosion(rand.nextInt(Variables.SCREEN_WIDTH)-12, rand.nextInt(150)-12));
			return touchedRender();
		}
		else{
			destroyed=false;
			super.explode();
			return getImage();
		}	
	}

	private Bitmap touchedRender() {
		Bitmap image = touchedImages[currentFrame];
		if(step>TOUCHED_STEP){
			step=0;		
			currentFrame++;
			if(currentFrame>=touchedImages.length){
				currentFrame=0;
				step=0;
				touched=false;
			}
		}
		return image;
	}

}
