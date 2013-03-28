package entities.ships.enemies;

/**
 * the Action class is used to determined what kind of action that an Enemy can do. 
 * In our case, we have only Two actions possible, Shoot and Move.
 * Moreover this class keep the angle, velocity and the step of begin and end of action and eventually the name for recognize a weapon.
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

public class Action {
	/**
	 * Enum type, for knows what kind of actions we have : Shoot or Move.
	 *
	 */
	public static enum ActionType{
		SHOOT,
		MOVE
	};

	private int beg;
	private int end;
	private int velocity;
	private double angle;
	private ActionType type;
	private String name;

	/**
	 * Construct an Action, with parameter initialize with wrong value.
	 * (begin and end time to -1)
	 */
	public Action(){
		setBeg(-1);
		setEnd(-1);
		setVelocity(Integer.MAX_VALUE);
		setAngle(Integer.MAX_VALUE);
		setType(null);
		setName(null);
	}

	/**
	 * Returns the begin step of this action.
	 * @return the begin step of this action
	 */
	public int getBeg() {
		return beg;
	}

	/**
	 * Set the begin step of this action.
	 * @param beg - the new begin step of this action
	 */
	public void setBeg(int beg) {
		this.beg = beg;
	}

	/**
	 * Returns the end step of this action.
	 * @return the end step of this action
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * Set the end step of this action.
	 * @param end - the new end step of this action
	 */
	public void setEnd(int end) {
		this.end = end;
	}

	/**
	 * Returns the velocity of this action.
	 * @return the velocity of this action
	 */
	public int getVelocity() {
		return velocity;
	}

	/**
	 * Set the velocity of this action.
	 * @param velocity - the new velocity of this action
	 */
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	/**
	 * Returns the angle of this action, in degree.
	 * @return the angle of this action, in degree
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * Set the angle of this action.
	 * @param angle - the new angle of this action
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}

	/**
	 * Returns the type of the action.
	 * @return the type of the action
	 */
	public ActionType getType() {
		return type;
	}

	/**
	 * Set the type of the action.
	 * @param type - the new type of the action
	 */
	public void setType(ActionType type) {
		this.type = type;
	}

	/**
	 * Returns the name of this action, if it's a Shoot, so the name of the weapon. Be care, return null if the action is a movement.
	 * @return the name of the weapon if the action is a movement, else return null
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the action, only uses for Shoot. Don't use it for movement.
	 * @param name - the new name of the action
	 */
	public void setName(String name) {
		this.name = name;
	}
}