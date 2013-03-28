package hud;

import com.example.escapeandroid.R;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import game.Ressources;


/**
 * This class represents our Button, used to allow the player to do a break during the game.
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

public abstract class Button {
	private Bitmap image;
	private int posX;
	private int posY;

	/**
	 * Enum class for recognize all kind of button.
	 *
	 */
	public enum ButtonType{
		SKIP,
		PAUSE,
		PLAY
	}
	private ButtonType type;

	/**
	 * Default constructor, which initialize a button depending of his type, and his coordinate.
	 * @param type - the type of the button
	 * @param posX - the pos X of the button
	 * @param posY - the pos Y of the button
	 */
	public Button(ButtonType type, int posX, int posY){
		setButtonType(type);
		this.posX=posX;
		this.posY=posY;		
	}

	/**
	 * Set the current Image button depending of his type
	 * @param type
	 */
	private void setButtonType(ButtonType type){
		this.type=type;
		switch(type){
		case PAUSE:
			this.image = Ressources.getImage(R.drawable.button_pause);
			break;
		case PLAY:
			this.image = Ressources.getImage(R.drawable.button_play);
			break;
		case SKIP:
			this.image = Ressources.getImage(R.drawable.button_skip);
			break;
		}
	}
	
	/**
	 * Render of the button.
	 * @param graphics - the graphics to draw on
	 */
	public void render(Canvas canvas){
		canvas.drawBitmap(image, posX, posY, null);		
	}

	/**
	 * Abstract method, to know if the button is pressed.
	 */
	public abstract void pressed();

	/**
	 * Do the event of the button, depending of the event.
	 * @param event - the motionEvent associated.
	 */
	public void event(MotionEvent event) {
		if( event.getAction()==MotionEvent.ACTION_DOWN && 
				event.getX()>posX && event.getX()<posX+image.getWidth() &&
				event.getY()>posY && event.getY()<posY+image.getHeight() ){
			
			switch(type){
			case PAUSE:
				setButtonType(ButtonType.PLAY);
				break;	
			case PLAY:
				setButtonType(ButtonType.PAUSE);
				break;	
			default:
				break;
			}
			pressed();
		}
	}
}
