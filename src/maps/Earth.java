package maps;

import effects.Effects;
import effects.basicEffect;
import game.Ressources;

import java.util.Random;

import com.example.escapeandroid.R;

/**
 * This class represents earth map.
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


public class Earth extends Map {
	
	int loop;
	Random rand;

	/**
	 * Default constructor.
	 */
	public Earth(){
		super(Ressources.getImage(R.drawable.earth, 1), 0.2f);
		loop=0;
		rand = new Random();
	}

	@Override
	public void computeMap() {
		loop++;
		if(loop>LOOP_SKIP){
			loop=0;
			
			float randVal = rand.nextFloat();
			if (randVal<0.5)
				randVal+=0.5;
			
			switch(rand.nextInt(3)){
				case 0:
					Effects.addEffect(3, new basicEffect(R.drawable.cloud_small, randVal*2));//addLayer(new Layer(imageBigCloud, posY - imageBigCloud.getWidth()/2, 2f));
					break;
				case 1:
					Effects.addEffect(2, new basicEffect(R.drawable.cloud_mid, randVal));//addLayer(new Layer(imageMidCloud, posY - imageMidCloud.getWidth()/2, 0.5f));
					break;
				case 2:
					Effects.addEffect(1, new basicEffect(R.drawable.cloud_big, randVal/0.5f));//addLayer(new Layer(imageSmallCloud, posY - imageSmallCloud.getWidth()/2, 0.1f));
					break;
			}			
		}	
	}
	
	
	
}
