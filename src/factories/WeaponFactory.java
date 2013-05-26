package factories;

import android.graphics.Bitmap;

import com.example.escapeandroid.R;

import entities.Entities;
import entities.weapons.Fireball;
import entities.weapons.Missile;
import entities.weapons.Shiboleet;
import entities.weapons.ShiboleetExtended;
import entities.weapons.Shuriken;
import entities.weapons.Weapon;
import game.Ressources;

/**
 * This class is a factory of weapon, and make all weapons of ships.
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

public class WeaponFactory extends EntityFactory{

	/**
	 * Default constructor.
	 * Initialize our factory with the world entities.
	 * @param entities - class which represents our world
	 */
	public WeaponFactory(Entities entities) {
		super(entities);
	}

	/**
	 * An enum that represent all type of wepaon.
	 */
	public enum WeaponType{
		Shuriken,
		Fireball, 
		Shiboleet, 
		ShiboleetExtended, 
		Missile,
		Null;
		/**
		 * Return the weaponType corresponding to the name given as parameter.
		 * @param weaponName - the String of the weapon we want the association
		 * @return the weaponType corresponding to the name given as parameter.
		 */
		public static WeaponType convert(String weaponName){
			if(weaponName.equals("Shuriken"))
				return Shuriken;
			if(weaponName.equals("Fireball"))
				return Fireball;
			if(weaponName.equals("Shiboleet"))
				return Shiboleet;
			if(weaponName.equals("ShiboleetExtended"))
				return ShiboleetExtended;
			if(weaponName.equals("Missile"))
				return Missile;
			return null;
		}
			
		/**
		 * Return the image corresponding to the Weapon.
		 * @return the image corresponding to the weapon
		 */
		public Bitmap getImage(){
			switch(this){
			case Shuriken: return Ressources.getImage(R.drawable.shuriken);
			case Fireball: return Ressources.getImage(R.drawable.fire);
			case Shiboleet: 
			case ShiboleetExtended: return Ressources.getImage(R.drawable.shiboleet);
			case Missile: return Ressources.getImage(R.drawable.missile);
			case Null : return Ressources.getImage(R.drawable.error);
			default: return null;
			}
		}
		
		@Override
		public String toString(){
			switch(this){
			case Shuriken: return "Shuriken";
			case Fireball: return "Fireball";
			case Shiboleet: 
			case ShiboleetExtended: return "Shiboleet";
			case Missile: return "Missile";
			case Null : return "No weapon";
			default: return null;
			}
		}
		
	};
	
	/**
	 * Create a Weapon into our world. 
	 * @param type - the WeaponType type create
	 * @param x - the coordinate associated with x position
	 * @param y - the coordinate associated with y position
	 * @param firedbyPlayer - If the weapon is fired by player
	 * @return the new weapon created
	 */
	public Weapon createWeapon(WeaponType type, int x, int y, boolean firedbyPlayer) {
		Weapon weapon = null;
		
		switch(type){
		case Shuriken:
			weapon =new Shuriken(getEntities(), x, y, firedbyPlayer);
			break;
		case Fireball:
			weapon = new Fireball(getEntities(), x, y, firedbyPlayer);
			break;
		case Missile:
			weapon = new Missile(getEntities(), x, y, firedbyPlayer);
			break;
		case Shiboleet:
			weapon = new Shiboleet(getEntities(), x, y, firedbyPlayer);
			break;
		case ShiboleetExtended:
			weapon = new ShiboleetExtended(getEntities(), x, y, firedbyPlayer,4);
			break;
		default:
			break;
		}
		getEntities().addEntity(weapon);
		return weapon;		
	}	
}
