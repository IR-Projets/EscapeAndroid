package entities.ships.enemies;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jbox2d.common.Vec2;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import entities.ships.enemies.Action.ActionType;
import game.Ressources;

/**
 * This class is used for load an XML files into an EnemyDef class.
 * This is a XML Parser, adapted to our class
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
public class LoaderXml{
	
	//Little inner class for facility the work of the handler
	
	private enum EnemyType{Enemy, Boss};

	private class EnemyProperties{
		public BufferedImage image=null;
		public int life=-1;
		public int repeatTime=-1;
		public List<Action> actions=null;
		List<AppearTime> appearListTmp = null;
		EnemyType enemyType;
	}

	private class AppearTime{
		int time=-1;
		Vec2 position=new Vec2(-1,-1);
	}

	//XML Handler
	/**
	 * Default Handler uses for load XML Files into our data
	 * @author kiouby
	 *
	 */
	public class EnemyHandler extends DefaultHandler {
		private StringBuilder sb;
		List<EnemyProperties> listEnemyProperties=null;
		EnemyProperties enemyPropertiesTmp=null;
		Action actionTmp=null;
		AppearTime appearTmp = null;


		@Override
		public void characters(char[] argv,int start, int length) throws SAXException{
			String read = new String(argv,start,length);
			if(sb != null) sb.append(read);       
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
			if (qName.equals("enemy") || qName.equals("boss")){
				if (enemyPropertiesTmp!=null){
					throw new SAXException("Enemy or Boss missformated, you can only init enemy one by one");
				}
				else{
					enemyPropertiesTmp = new EnemyProperties();
					if(qName.equals("enemy"))
						enemyPropertiesTmp.enemyType=EnemyType.Enemy;
					else
						enemyPropertiesTmp.enemyType=EnemyType.Boss;
				}
				return;
			}
			if (qName.equals("life")){
				if (enemyPropertiesTmp == null || enemyPropertiesTmp.life !=-1){
					throw new SAXException("Enemy tag not specified or life field already init");
				}
				sb = new StringBuilder();
				return;
			}
			if (qName.equals("image")){
				if (enemyPropertiesTmp == null || enemyPropertiesTmp.image !=null){
					throw new SAXException("Enemy tag not specified or image field already init");
				}
				sb = new StringBuilder();
				return;
			}

			if (qName.equals("actions")){
				if (enemyPropertiesTmp==null  || enemyPropertiesTmp.repeatTime !=-1)
					throw new SAXException("Enemy tag not specified or repeatTime attribute already init");
				try {
					enemyPropertiesTmp.repeatTime=Integer.parseInt(attributes.getValue("repeattime"));
					enemyPropertiesTmp.actions = new LinkedList<Action>();
				} catch (Exception e){
					throw new SAXException("Error while parsing actions: repeattime");
				}
				return;
			}

			if (qName.equals("move")){
				if (enemyPropertiesTmp.repeatTime==-1 || enemyPropertiesTmp.actions == null) 
					throw new SAXException("Actions undefined: define the actions first or specifty the attribute repeatTime");
				try {
					actionTmp = new Action();
					actionTmp.setBeg(Integer.parseInt(attributes.getValue("beg")));
					actionTmp.setEnd(Integer.parseInt(attributes.getValue("end")));
					actionTmp.setType(ActionType.MOVE);
				} catch (Exception e){
					throw new SAXException("Error while parsing actions move: beg or end attributes");
				}
				return;
			}

			if (qName.equals("fire")){
				if (enemyPropertiesTmp.repeatTime==-1 || enemyPropertiesTmp.actions == null) 
					throw new SAXException("Actions undefined: define the actions first or specifty the attribute repeatTime");
				try {
					actionTmp = new Action();
					actionTmp.setBeg(Integer.parseInt(attributes.getValue("beg")));
					actionTmp.setEnd(Integer.parseInt(attributes.getValue("end")));
					actionTmp.setType(ActionType.SHOOT);
				} catch (Exception e){
					throw new SAXException("Error while parsing actions move: beg or end attributes");
				}
				return;
			}

			if (qName.equals("angle")){
				if (enemyPropertiesTmp == null || actionTmp == null || actionTmp.getAngle() !=Integer.MAX_VALUE){
					throw new SAXException("Enemy tag undefined, actions tag undefined, or angle already defined");
				}
				sb = new StringBuilder();
				return;
			}

			if (qName.equals("velocity")){
				if (enemyPropertiesTmp == null || actionTmp == null || actionTmp.getVelocity() !=Integer.MAX_VALUE){
					throw new SAXException("Enemy tag undefined, actions tag undefined, or velocity already defined");
				}
				sb = new StringBuilder();
				return;
			}

			if (qName.equals("name")){
				if (enemyPropertiesTmp == null || actionTmp == null || actionTmp.getName() !=null){
					throw new SAXException("Enemy tag undefined, actions tag undefined, or name already defined");
				}
				sb = new StringBuilder();
				return;
			}

			if (qName.equals("appear")){
				if (enemyPropertiesTmp == null ){
					throw new SAXException("Enemy tag undefined");
				}
				try {
					if(enemyPropertiesTmp.appearListTmp == null)
						enemyPropertiesTmp.appearListTmp = new LinkedList<AppearTime>();
						appearTmp = new AppearTime();
						appearTmp.time = Integer.parseInt(attributes.getValue("time"));
				}catch (Exception e){
					throw new SAXException("Error while parsing appear : time, posX or posY attributes");
				}
				return;
			}

			if (qName.equals("posX")){
				if (enemyPropertiesTmp == null || appearTmp == null || appearTmp.position.x != -1){
					throw new SAXException("Enemy tag, appear time or posX already defined");
				}
				sb = new StringBuilder();
				return;
			}	

			if (qName.equals("posY")){
				if (enemyPropertiesTmp == null || appearTmp == null || appearTmp.position.y != -1){
					throw new SAXException("Enemy tag, appear time or posY already defined");
				}
				sb = new StringBuilder();
				return;
			}	

			if (qName.equals("enemies"))
				return;
			throw new SAXException("Unknown XML attribute: " + qName);
		}



		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException{

			if (qName.equals("enemy") || qName.equals("boss")){
				if (enemyPropertiesTmp == null || enemyPropertiesTmp.life==-1 || enemyPropertiesTmp.image==null || enemyPropertiesTmp.actions==null || enemyPropertiesTmp.appearListTmp == null ){
					throw new SAXException("Enemy, life, image, actions or appear fields are not correctly set ");
				}
				else{
					if((qName.equals("enemy") && enemyPropertiesTmp.enemyType!= EnemyType.Enemy)  || (qName.equals("boss") && enemyPropertiesTmp.enemyType!= EnemyType.Boss))
						throw new SAXException("Boss or Enemy tag missformated");
					if(listEnemyProperties == null)
						listEnemyProperties = new LinkedList<EnemyProperties>();
						listEnemyProperties.add(enemyPropertiesTmp);
						enemyPropertiesTmp=null;
				}
				return;
			}

			if (qName.equals("life")){
				if (enemyPropertiesTmp == null || enemyPropertiesTmp.life !=-1)
					throw new SAXException("Enemy tag not specified or life field already init");
				try{
					enemyPropertiesTmp.life = Integer.parseInt(sb.toString());
				} catch (Exception e){
					throw new SAXException(qName+" tag should contain an integer value");
				}
				return;
			}

			if (qName.equals("image")){
				if (enemyPropertiesTmp == null || enemyPropertiesTmp.image !=null)
					throw new SAXException("Enemy tag not specified or image field already init");
				try{
					enemyPropertiesTmp.image = Ressources.getImage(sb.toString());
				} catch (Exception e){
					throw new SAXException(qName+" tag should contain an image url");
				}
				return;
			}

			if (qName.equals("actions")){
				if (enemyPropertiesTmp==null  || enemyPropertiesTmp.actions == null || enemyPropertiesTmp.actions.isEmpty())
					throw new SAXException("No action specified for this enemy");
				return;
			}

			if (qName.equals("move")){
				if (enemyPropertiesTmp == null || actionTmp.getAngle() == Integer.MAX_VALUE || actionTmp.getVelocity()==Integer.MAX_VALUE|| actionTmp.getBeg() == -1 || actionTmp.getEnd() == -1 || actionTmp.getType() != ActionType.MOVE) 
					throw new SAXException("Move tag missformated : angle, velocity, beg or end not renseigned");
				enemyPropertiesTmp.actions.add(actionTmp);
				actionTmp=null;
				return;
			}

			if (qName.equals("fire")){
				if (enemyPropertiesTmp == null || actionTmp.getAngle() == Integer.MAX_VALUE || actionTmp.getVelocity()==Integer.MAX_VALUE|| actionTmp.getName() == null || actionTmp.getBeg() == -1 || actionTmp.getEnd() == -1 || actionTmp.getType() != ActionType.SHOOT) 
					throw new SAXException("Move tag missformated : angle, velocity, beg or end not renseigned");
				enemyPropertiesTmp.actions.add(actionTmp);
				actionTmp=null;
				return;
			}

			if (qName.equals("angle")){
				if (enemyPropertiesTmp == null || actionTmp == null || actionTmp.getAngle() !=Integer.MAX_VALUE)
					throw new SAXException("Enemy tag not specified or actions tag not specified or angle field already init");
				try{
					actionTmp.setAngle(Double.parseDouble(sb.toString()));
				} catch (Exception e){
					throw new SAXException(qName+" angle should contain a double value");
				}
				return;
			}

			if (qName.equals("velocity")){
				if (enemyPropertiesTmp == null || actionTmp == null || actionTmp.getVelocity() !=Integer.MAX_VALUE)
					throw new SAXException("Enemy tag not specified or actions tag not specified or velocity field already init");
				try{
					actionTmp.setVelocity(Integer.parseInt(sb.toString()));
				} catch (Exception e){
					throw new SAXException(qName+" velocity should contain an Integer value");
				}
				return;
			}

			if (qName.equals("name")){
				if (enemyPropertiesTmp == null || actionTmp == null || actionTmp.getName() !=null)
					throw new SAXException("Enemy tag not specified or actions tag not specified or name field already init");
				actionTmp.setName(sb.toString());
				return;
			}

			if (qName.equals("appear")){
				if (enemyPropertiesTmp == null || appearTmp == null || appearTmp.position == null || appearTmp.position.x ==-1 || appearTmp.position.y ==-1 || appearTmp.time ==-1){
					throw new SAXException("Enemy, appear tag or time, posX and posY are not correctly set");
				}
				enemyPropertiesTmp.appearListTmp.add(appearTmp);
				appearTmp=null;
				return;
			}	

			if (qName.equals("posX")){
				if (enemyPropertiesTmp == null || appearTmp == null || appearTmp.position.x != -1){
					throw new SAXException("Enemy tag undefined, or appear time undefined");
				}
				appearTmp.position.x = Integer.parseInt(sb.toString());
				return;
			}	

			if (qName.equals("posY")){
				if (enemyPropertiesTmp == null || appearTmp == null || appearTmp.position.y != -1){
					throw new SAXException("Enemy tag undefined, or appear time undefined");
				}
				appearTmp.position.y = Integer.parseInt(sb.toString());
				return;
			}	

			if (qName.equals("enemies"))
				return;

			throw new SAXException("Unknown XML attribute: " + qName);
		}

		@Override
		public void endDocument() throws SAXException {
			sb = null;
		}

	}


	/**
	 * Return a list of EnemyDef which contains all data for launch the creation of enemies
	 * @param filename - the emplacement of the XML file to load into our data
	 * @return - the list of EnemyDef which contains all data for launch the creation of enemies
	 */
	public List<EnemyDef> getEnemysFromXml(String filename) {

		EnemyHandler eh = new EnemyHandler();
		List<EnemyDef> listEnemies = new LinkedList<EnemyDef>();
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setValidating(true);
			SAXParser parser = factory.newSAXParser();

			parser.parse(Ressources.getFile(filename), eh);
		} catch (ParserConfigurationException pce) {
			System.err.println("An error occured during parsing");
			System.exit(1);
		} catch (SAXException se){
			System.err.println("Parsing error : "+se.getMessage());
			System.exit(1);
		}  catch (IOException ioe) {
			System.err.println("Read/Write error : "+filename);
			System.exit(1);
		}
		for(EnemyProperties enemyProperties : eh.listEnemyProperties){
			for(AppearTime appearTime : enemyProperties.appearListTmp){
				EnemyBehavior enemyBehavior = new EnemyBehavior(enemyProperties.actions, enemyProperties.repeatTime);
				boolean isBoss;
				if(enemyProperties.enemyType==EnemyType.Boss)
					isBoss=true;
				else
					isBoss=false;
				listEnemies.add(new EnemyDef(enemyProperties.image,  enemyBehavior, (int)appearTime.position.x, (int)appearTime.position.y, enemyProperties.life, appearTime.time, isBoss));
			}
		}
		return listEnemies;
	}
}