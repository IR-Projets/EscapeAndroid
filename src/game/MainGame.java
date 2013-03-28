package game;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.JFrame;

import fr.umlv.zen2.Application;
import fr.umlv.zen2.ApplicationCode;
import fr.umlv.zen2.ApplicationContext;
import fr.umlv.zen2.ApplicationRenderCode;
import fr.umlv.zen2.MotionEvent;



/**
 * This class represents our mainGame, which launch the game.
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


public class MainGame {

	/**
	 * Main which launch the game.
	 * @param args
	 */
	public static void main(String[] args) {
		final Game game;
		try {
			game = new Game();
		} catch (IOException e1) {
			System.out.println("Launch failure");
			e1.printStackTrace();
			return;
		}
		Application.run(Variables.APPLICATION_NAME, Variables.SCREEN_WIDTH, Variables.SCREEN_HEIGHT, new ApplicationCode() {

			@Override
			public void run(final ApplicationContext context) {   
				/*
				 * Initialisation
				 */
				context.render(
						new ApplicationRenderCode() {  
							@Override
							public void render(Graphics2D graphics) {
								JFrame.getFrames()[0].setLocationRelativeTo(null);
							}
						}
				);
				
				/*
				 * Main loop
				 */
				for(;;) {
					
					/*
					 * Events
					 */
					MotionEvent event;
					while((event=context.pollMotion()) != null) {
						game.event(event);
					}
					
					/*
					 * Render
					 */
					context.render(
							new ApplicationRenderCode() {
								@Override
								public void render(Graphics2D graphics) {
									game.run(graphics);
								}
							}
					);
				}

			}
		});

	}
}
