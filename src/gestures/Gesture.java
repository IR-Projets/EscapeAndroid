package gestures;

import entities.ships.Player;
import entities.ships.enemies.Action.ActionType;
import game.Variables;
import gestures.filters.Filter;
import gestures.filters.Filters;
import gestures.filters.Looping;
import gestures.filters.Move;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.common.Vec2;

import android.graphics.Canvas;
import android.view.MotionEvent;
/**
 * The Gesture is the class which manage the Gesture effect, associated with the player.
 * He manage the control of all gesture launch by the player, and launch the correction action (move or shoot).
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
public class Gesture {

	/**
	 * Our TraceStack, which represents all Trace created by the player during the game.
	 */
	private final TraceStack traceStack;
	
	/**
	 * Represents all filters uses for knows if a trace is correctly set.
	 */
	private final List<Filter> filters;
	
	/**
	 * An enum for represents the kind of gesture the player wants : He wants to move, or to shoot.
	 * @see ActionType
	 */
	private ActionType actionType;

	/**
	 * The player who's gonna move or shoot depending on where he has begin his gesture
	 */
	private final Player player;
	/**
	 * Public constructor. You have to precize a player to instantiate the gesture.
	 * @param player - the player associated with the gesture
	 */
	public Gesture(Player player){
		this.player = player;
		traceStack = new TraceStack();
		filters = initFilters();
	}

	/**
	 * Initialize all filters, which implements Filter Interface.
	 * @return the list of filter initialize with all filters.
	 */
	public List <Filter> initFilters(){
		List<Filter> filtersList = new ArrayList<Filter>();
		filtersList.add(new Looping());
		filtersList.add(new Move());
		return filtersList;
	}

	/**
	 * Display the Gesture, which is a trace of the movement printing by the mouse.
	 * @param graphics - Graphics2D graphics.
	 */
	public void render(Canvas canvas){
		if(traceStack.isEmpty()){// if we have finish the movement, we ask the player to stop
			player.stop();
			return;
		}
		traceStack.render(canvas);
	}

	/**
	 * Shoot the weapon created by the player, which are in his lists of weapons.
	 */
	private void shootWeapon(){
		List<Vec2> traceWeapon = traceStack.getCurrentTrace().getTrace();
		double angle;
		if(traceWeapon.size() <=1)//we shoot in top by default
			angle = 90;
		else
			angle = Filters.getAngle(traceWeapon);
		player.shootWeapon(angle, Variables.SHIP_BULLET_VELOCITY);
		traceStack.getCurrentTrace().setValid(true);
	}

	/**
	 * The event launched by the mouse, which is described by zen2 Libraries.
	 * @param event - the event of the mouse.
	 */
	public void event(MotionEvent event){
		switch(event.getAction()){	
		case MotionEvent.ACTION_UP : // End of the gesture
			if(actionType == ActionType.MOVE){//the player want to move, so we check if he has make a good gesture, and apply the movement
				for(Filter filter : filters)
					if(traceStack.check(filter)){
						filter.apply(player);
						break;
					}
			}
			else if (actionType == ActionType.SHOOT){
				shootWeapon();
			}
			traceStack.finishCurrentTrace();
			break;

		case MotionEvent.ACTION_DOWN : // Begin of the gesture
			if(player.isOnSprite(new Vec2(event.getX(), event.getY()))){//the player want to shoot, so we load the weapon.
				if(actionType != ActionType.SHOOT ){
					actionType = ActionType.SHOOT;
				}
				else if(!player.getWeapons().isEmpty()){
					player.getWeapons().removeCurrentItem();
					player.loadWeapon();
					actionType = ActionType.LOAD;
				}
			}
			else
				actionType = ActionType.MOVE;
			break;

		case MotionEvent.ACTION_MOVE :
			if(traceStack.size() <= 3)//limit the number max of gesture display by 3
				traceStack.getCurrentTrace().addPoint(new Vec2(event.getX(), event.getY()));
			break;

		default:
			break;
		}
	}
}