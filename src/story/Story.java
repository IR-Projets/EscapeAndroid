package story;

import fr.umlv.zen2.MotionEvent;
import game.Ressources;
import game.Variables;
import hud.Button;
import hud.Button.ButtonType;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class Story {

	/*
	 * Cree un couple temps/render
	 * time: in ms
	 * Sert a l'affichage d'un narrateur
	 */
	public abstract class Couple{
		int time;
		public Couple(int time){ this.time=time; }
		public abstract void render(Graphics2D g);
	}
		

	//private static final int TEXT_SCALE = 20;
	private long time, lastTime;
	
	
	//The backGround
	private BackGround backGround;
	
	//La sequence
	private List<Couple> sequence;
	
	//Skip button
	private Button skipButton;
	
	
	//Images
	private BufferedImage[] imgXaroff;
	private BufferedImage[] imgSirud;
	private BufferedImage[] imgHero;
	private final Narator sirud;
	private final Narator xaroff;
	private final Narator hero;
	private boolean loaded;
	
	
	
	
	
	public Story(){	
		loaded=false;
		time = 0;
		lastTime=-1;
		
		backGround = new BackGround("story/solarSystem.jpg");
		
		//The button and the event
		skipButton = new Button(ButtonType.SKIP, Variables.SCREEN_WIDTH/2-32, 10){
			@Override
			public void pressed() {
				finish();
			}			
		};
		
		
		/*
		 * Narrator's images 
		 */
		imgXaroff = new BufferedImage[2];
		imgXaroff[0] = Ressources.getImage("story/Xaroff1.png");
		imgXaroff[1] = Ressources.getImage("story/Xaroff2.png");
		
		imgSirud = new BufferedImage[2];
		imgSirud[0] = Ressources.getImage("story/Sirud1.png");
		imgSirud[1] = Ressources.getImage("story/Sirud2.png");
		
		imgHero = new BufferedImage[1];
		imgHero[0] = Ressources.getImage("story/hero.png");
		
		
		/*
		 * Narators( image, posX, posY )
		 */
		sirud = new Narator(imgSirud, 60, 10);
		xaroff = new Narator(imgXaroff, Variables.SCREEN_WIDTH-160, 10);
		hero = new Narator(imgHero, Variables.SCREEN_WIDTH/2-50, 160);
		
		
		/*
		 *	The sequence 
		 */
		sequence = new LinkedList<Couple>();
	}
	
	
	/*
	 * 		The Begin scene
	 */
	public void loadStory1(){
		loaded = true;
		sequence.add(new Couple(1000){
			@Override
			public void render(Graphics2D g) {
				sirud.speak(g, "Reveil toi...\n");				
			}			
		});
		sequence.add(new Couple(2000){
			@Override
			public void render(Graphics2D g) {
				sirud.draw(g);		
			}			
		});
		sequence.add(new Couple(2000){
			@Override
			public void render(Graphics2D g) {
				sirud.speak(g, "Ta mission suicide sur la planete\nalien a ete un echec");		
				hero.draw(g);	
			}			
		});		
		sequence.add(new Couple(2000){
			@Override
			public void render(Graphics2D g) {
				sirud.speak(g, "Il va falloir que tu trouve un moyen de te rattraper");	
				hero.draw(g);	
			}			
		});
		sequence.add(new Couple(500){
			@Override
			public void render(Graphics2D g) {
				sirud.draw(g);
				hero.draw(g);	
			}			
		});
		sequence.add(new Couple(3000){
			@Override
			public void render(Graphics2D g) {
				sirud.speak(g, "Tu pourra te servir de l'attraction\nde mars pour augmenter ta moyenne");	
				hero.draw(g);	
			}			
		});

		sequence.add(new Couple(2000){
			@Override
			public void render(Graphics2D g) {
				xaroff.speak(g, "HaHaHA Tu ne partiras jamais\nde cette planete!");			
				hero.draw(g);	
			}			
		});
		sequence.add(new Couple(2000){
			@Override
			public void render(Graphics2D g) {
				xaroff.speak(g, "Mes Lambdas vont t'arreter!");			
				hero.draw(g);	
			}			
		});
		sequence.add(new Couple(1000){
			@Override
			public void render(Graphics2D g) {
				xaroff.speak(g, "Abandonne!");	
				hero.draw(g);	
			}			
		});
		sequence.add(new Couple(1000){
			@Override
			public void render(Graphics2D g) {
				hero.speak(g, "Jamais!");
				xaroff.draw(g);				
			}			
		});
		sequence.add(new Couple(2000){
			@Override
			public void render(Graphics2D g) {
				hero.speak(g, "meme si je dois arreter\nde dormir pour ca!");
				xaroff.draw(g);				
			}			
		});	
		sequence.add(new Couple(2000){
			@Override
			public void render(Graphics2D g) {
			}			
		});
		sequence.add(new Couple(2000){
			@Override
			public void render(Graphics2D g) {
				hero.speak(g, "Bon j'ai trouver un vaisseau...");		
			}			
		});
		sequence.add(new Couple(2000){
			@Override
			public void render(Graphics2D g) {
				hero.speak(g, "Il semble etre assez simple a utiliser");		
			}			
		});	
		sequence.add(new Couple(2000){
			@Override
			public void render(Graphics2D g) {
				hero.speak(g, "Il suffit de tracer un trait\nsur son ecran tactile...");		
			}			
		});	
		sequence.add(new Couple(500){
			@Override
			public void render(Graphics2D g) {
				hero.draw(g);
			}			
		});
		sequence.add(new Couple(2500){
			@Override
			public void render(Graphics2D g) {
				hero.speak(g, "Un trait partant du vaisseau lance une arme");		
			}			
		});
		sequence.add(new Couple(2500){
			@Override
			public void render(Graphics2D g) {
				hero.speak(g, "Un trait a cote du vaisseau lance un deplacement");		
			}			
		});	
		sequence.add(new Couple(2500){
			@Override
			public void render(Graphics2D g) {
				hero.speak(g, "On peut changer d'arme avec la liste\nderoulante en haut de l'ecran");		
			}			
		});	
		sequence.add(new Couple(500){
			@Override
			public void render(Graphics2D g) {
				hero.draw(g);
			}			
		});
		sequence.add(new Couple(2000){
			@Override
			public void render(Graphics2D g) {
				hero.speak(g, "C'est parti...");		
			}			
		});	
	}
	
	
	
	/*
	 * 		Win Jupiter
	 */
	public void loadStory_WinJupiter(){
		loaded=true;
		sequence.add(new Couple(3000){
			@Override
			public void render(Graphics2D g) {
				sirud.speak(g, "Mes felicitations tu a passe le premier grade!\n");
				hero.draw(g);
			}			
		});
		sequence.add(new Couple(3000){
			@Override
			public void render(Graphics2D g) {
				sirud.speak(g, "Niveau suivant,\nattention les partiels arrivent!\n");		
				hero.draw(g);
			}			
		});
		sequence.add(new Couple(3000){
			@Override
			public void render(Graphics2D g) {
				xaroff.speak(g, "Tu ne depasseras jamais\nla moyenne lune...\n");		
				hero.draw(g);
			}			
		});
		sequence.add(new Couple(1000){
			@Override
			public void render(Graphics2D g) {
			}			
		});
	}
	
	
	/*
	 * 		Win Moon
	 */
	public void loadStory_WinMoon(){
		loaded=true;
		sequence.add(new Couple(3000){
			@Override
			public void render(Graphics2D g) {
				sirud.speak(g, "Mes felicitations tu a passes le deuxieme grade!\n");
				hero.draw(g);
			}			
		});
		sequence.add(new Couple(3000){
			@Override
			public void render(Graphics2D g) {
				sirud.speak(g, "Niveau suivant,\nattention les partiels arrivent!\n");		
				hero.draw(g);
			}			
		});
		sequence.add(new Couple(3000){
			@Override
			public void render(Graphics2D g) {
				xaroff.speak(g, "Tu ne passera jamais\nle dernier grade");		
				hero.draw(g);
			}			
		});
		sequence.add(new Couple(1000){
			@Override
			public void render(Graphics2D g) {
			}			
		});
	}
	
	
	/*
	 * 		Win Earth
	 */
	public void loadStory_WinEarth(){
		loaded=true;
		sequence.add(new Couple(3000){
			@Override
			public void render(Graphics2D g) {
				sirud.speak(g, "Mes felicitations tu a passes le dernier grade!\n");
				hero.draw(g);
			}			
		});
		sequence.add(new Couple(3000){
			@Override
			public void render(Graphics2D g) {
				sirud.speak(g, "Tu est enfin revenu sur terre\n");		
				hero.draw(g);
			}			
		});
		sequence.add(new Couple(3000){
			@Override
			public void render(Graphics2D g) {
				xaroff.speak(g, "Aller va dormir tu l'as merite\n");		
				hero.draw(g);
			}			
		});
		sequence.add(new Couple(1000){
			@Override
			public void render(Graphics2D g) {
			}			
		});
	}
	
	
	/*
	 * 	LOOSE
	 */	
	public void loadStory_Loose() {
		loaded=true;
		sequence.add(new Couple(3000){
			@Override
			public void render(Graphics2D g) {
				sirud.speak(g, "Un protocole inconnu\na detruit ton vaisseau");
				xaroff.draw(g);
				hero.draw(g);
			}			
		});
		sequence.add(new Couple(3000){
			@Override
			public void render(Graphics2D g) {
				xaroff.speak(g, "La Thread principale\nn'a pas tenu");
				sirud.draw(g);
				hero.draw(g);
			}			
		});
		sequence.add(new Couple(1000){
			@Override
			public void render(Graphics2D g) {
				xaroff.speak(g, "Je t'ai eu!");
				sirud.draw(g);
				hero.draw(g);
			}			
		});
		sequence.add(new Couple(2000){
			@Override
			public void render(Graphics2D g) {
				xaroff.draw(g);
				sirud.draw(g);
				hero.draw(g);
			}			
		});
		sequence.add(new Couple(5000){
			@Override
			public void render(Graphics2D g) {
				sirud.speak(g, "A l'annee prochaine...");
				xaroff.speak(g, "Bye!");
				hero.draw(g);
			}			
		});
		sequence.add(new Couple(3000){
			@Override
			public void render(Graphics2D g) {
				hero.speak(g, "Bon bah...");
			}			
		});
		sequence.add(new Couple(5000){
			@Override
			public void render(Graphics2D g) {
				hero.speak(g, "Je vais aller taffer\nau McDo...");
			}			
		});
		sequence.add(new Couple(5000){
			@Override
			public void render(Graphics2D g) {
			}			
		});
	}
	
	
	/*
	 * 	Methods utils
	 */	
	
	/*
	private void drawText(Graphics2D graphics, String text, float x, float y){
		String [] lines = text.split("\n");
		for(int i=0; i<lines.length; i++){
			graphics.drawString(lines[i], x, y+i*(TEXT_SCALE+10));
		}	
	}
	*/
	public void render(Graphics2D graphics){		
		if(sequence.size()==0){
			finish();
			return;
		}
		
		backGround.render(graphics);
		skipButton.render(graphics);
		
		Couple couple = sequence.get(0);
		couple.render(graphics);
		
		time=System.nanoTime();
		if(lastTime==-1)
			lastTime=System.nanoTime();
		if(couple.time<(time-lastTime)/1000000){
			lastTime = time;
			sequence.remove(0);
		}
	}
	
	public boolean isLoaded(){
		return loaded;
	}


	public void finish() {
		loaded=false;
		sequence.clear();
		lastTime=-1;
	}


	public void event(MotionEvent event) {
		skipButton.event(event);		
	}
	
	
	
	
}
