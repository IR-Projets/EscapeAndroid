package entities.weapons;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import factories.WeaponFactory.WeaponType;
import game.Variables;
/**
 * This class represents an item, which contains the main function for initialize it and show it on a Graphics.
 * An item is represents by a quantity, an image and a kind of weapon, because all of our items are weapons.
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
public class WeaponItem {
	
	/**
	 * The image associated with the item
	 */
	private final BufferedImage image;
	
	/**
	 * the number of item
	 */
	private int quantity;
	
	/**
	 * The type of Weapon the item is : for associated a weapon to an item
	 */
	WeaponType weaponType;
	
	/**
	 * The default constructor
	 * @param weaponType - the type of the weapon associated with the item
	 * @param quantity - the quantity of the number of item
	 */
	public WeaponItem(WeaponType weaponType, int quantity) {
		this.weaponType = weaponType;
		this.image=weaponType.getImage();
		this.quantity=quantity;
	}

	/**
	 * Return the type of weapon of the item
	 * @return the type of weapon of the item
	 */
	public WeaponType getWeaponType(){
		return weaponType;
	}
	
	/**
	 * Return the Image of the Item
	 * @return the Image of the Item
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * Return the number of item
	 * @return the number of item
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Add a quantity to this item
	 * @param quantity for know how many we increase it
	 */
	public void addQuantity(int quantity){
		this.quantity+=quantity;
	}
	
	/**
	 * Decrease the quantity of the item
	 */
	public void removeQuantity(){
		quantity --;
	}
	/**
	 * Draw the item, at the position x and y. The drawing show : The image of item, with a frame,
	 * the quantity at the bottom right of the frame, and the name of the item, at right of the image.
	 * @param graphics the graphics2D to print on
	 * @param x the begin of the drawing of the item, at position x
	 * @param y the begin of the drawing of the item, at position y
	 */
	public void drawItem(Graphics2D graphics, int x, int y){
		int widthItem = getImage().getWidth(), heighItem = getImage().getHeight();
		
		graphics.drawImage(getImage(), x, y, null);//display image of the item
		graphics.setColor(Variables.RED);
		graphics.drawRect(x+2, y, widthItem-4, heighItem-1);//border of the item image
		graphics.setColor(Variables.ORANGE);
		graphics.drawString(weaponType.toString(), x+26, y+15);//Name of the item
		
		graphics.setColor(Variables.WHITE);
		graphics.drawString(String.valueOf(getQuantity()), x+20, y+23);//display the amount of the item
		
	}
}
