package entities.weapons;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import factories.WeaponFactory.WeaponType;
/**
 * This class represents a list of Item, which contains the main function for manage the event with it.
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
public class WeaponItems {

	/**
	 * Our list of items.
	 */
	private final List<WeaponItem> weaponItems;


	/**
	 * Default constructor.
	 */
	public WeaponItems() {
		weaponItems = new LinkedList<WeaponItem>();
	}
	
	/**
	 * Return the WeaponItem which is at the first position of our list.
	 * @return the current WeaponItem
	 */
	public WeaponItem getCurrentWeaponItem(){
		return weaponItems.get(0);
	}

	/**
	 * Returns true if this list contains no elements
	 * @return true if this list contains no elements
	 */
	public boolean isEmpty() {
		return weaponItems.isEmpty();
	}

	/**
	 * Return an iterator of WeaponItem.
	 * @return the iterator of the WeaponItem
	 */
	public Iterator<WeaponItem> iterator() {
		return weaponItems.iterator();
	}

	/**
	 * Return the size of the list of WeaponItems.
	 * @return the size of the list of WeaponItems
	 */
	public int size() {
		return weaponItems.size();
	}
	
	/**
	 * Add a weaponType, associated with his quantity, to our list of WeaponItems.
	 * If we already have this items, this only increase his quantity.
	 * @param weaponType - the weaponType to add
	 * @param quantity - the quantity to add
	 */
	public void addWeaponItem(WeaponType weaponType, int quantity){
		for(WeaponItem item: weaponItems){
			if(item.weaponType==weaponType){
				item.addQuantity(quantity);
				return;
			}
		}
		add(new WeaponItem(weaponType, quantity));
	}

	/**
	 * Remove the CurrentItem, by removing the quantity by One, or removing totally the item, depending on his quantity
	 * Be care, we continue to add the Missile if we doesn't have more item, to force the player to continue to play.
	 * @return the Item remove
	 */
	public WeaponItem removeCurrentItem() {
		WeaponItem weaponItem = weaponItems.get(0);
		if(weaponItem.getQuantity()>0)
			weaponItem.removeQuantity();
		else{
			weaponItems.remove(0);
			if (weaponItems.size() == 0)
				weaponItems.add(new WeaponItem(WeaponType.Missile, 0));
		}
		return weaponItem;
	}

	/**
	 * Put at the position indexNew the WeaponItem associated at the the position indexOld. 
	 * @param indexOld - the index of the old value.
	 * @param indexNew - the index of the new value.
	 */
	public void setIndexInList(int indexOld, int indexNew){
		weaponItems.add(indexNew, weaponItems.remove(indexOld));
	}

	/**
	 * Add a weaponItem to the list of weapons.
	 * @param weaponItem - the weaponItem to add
	 */
	public void add(WeaponItem weaponItem) {
		weaponItems.add(weaponItem);
	}
}
