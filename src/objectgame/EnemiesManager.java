package objectgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import userinterface.GameScreen;
import util.Resource;

public class EnemiesManager {
	
	private List<Enemy> enemies;
	private Random random;
	private BufferedImage cactusImage1;
	private BufferedImage cactusImage2;
	private MainCharacter mainCharacter;
	private GameScreen gameScreen;

	public EnemiesManager(MainCharacter mainCharacter, GameScreen gameScreen) {		// constructor, inject mainCharacter and gameScreen
		this.mainCharacter = mainCharacter;
		this.gameScreen = gameScreen;
		enemies = new ArrayList<Enemy>();
		cactusImage1 = Resource.getResourceImage("data/cactus1.png");				// render image
		cactusImage2 = Resource.getResourceImage("data/cactus2.png");				
		random = new Random();
		enemies.add(getRandomCactus());												// add randomly generated cactus into the enemies list
	}
	
	private Cactus getRandomCactus() {												// generate random cactus
		Cactus cactus = new Cactus(mainCharacter);
		cactus.setPosX(600);														// set starting x coordinate
		if (random.nextBoolean()) {
			cactus.setCactusImage(cactusImage1);
		} else {
			cactus.setCactusImage(cactusImage2);
		}
		return cactus;
	}
	
	public void draw(Graphics g) {													// draw the cactus
		for (Enemy e : enemies) {
			e.draw(g);
		}
	}
	
	public void update() {															// cactus moving and character scoring logic
		for (Enemy e : enemies) {
			e.update();
			if (e.isOver() && !e.isScored()) {										// if character jumpped over it and not logged the score yet
				gameScreen.increaseScore(20);										// increase and set score
				e.setIsScored(true);
			}
			if (e.getBound().intersects(mainCharacter.getBound())) {				// if character failed to jump over, the character dies
				mainCharacter.setIsAlive(false);
				
			}
			
		}
		Enemy firstEnemy = enemies.get(0);
		if (firstEnemy.isOutOfScreen()) {											// if the enemy is out of screen completely
			enemies.remove(firstEnemy);												// remove it from enemies list
			enemies.add(getRandomCactus());											// generate a new enemy and add it to the list
		}

	}
	
	public void reset() {															// method used when game restart, clear out current enemy
		enemies.clear();
		enemies.add(getRandomCactus());
	}
	
}
