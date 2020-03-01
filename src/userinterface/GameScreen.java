package userinterface;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import objectgame.Clouds;
import objectgame.EnemiesManager;
import objectgame.Land;
import objectgame.MainCharacter;
import util.Resource;


// Runnable abstract class includes: run method
// KeyListener abstract class includes: keyTyped, keyPressed, keyReleased methods
public class GameScreen extends JPanel implements Runnable, KeyListener{
	
	public static final int GAME_INITIAL_STATE = 0;										// static final variable for game state
	public static final int GAME_PLAY_STATE = 1;
	public static final int GAME_OVER_STATE = 2;
	
	public static final float GRAVITY = 0.1f;											// for gravity and ground level
	public static final float GROUNDY = 235;
	
	private Thread thread;																// independent path for executing program, created by Java Virtual Machine
	private MainCharacter mainCharacter;
	private Land land;
	private Clouds clouds;
	private EnemiesManager enemiesManager;
	private int score;
	private int gameState = GAME_INITIAL_STATE;
	private BufferedImage imageGameOver;
	private BufferedImage chicken;
	private BufferedImage chickenTitle;

	public GameScreen() {																// constructor method
		thread = new Thread(this);
		mainCharacter = new MainCharacter();
		mainCharacter.setX(50);															// character birth location
		mainCharacter.setY(100);
		land = new Land(this);
		clouds = new Clouds();
		enemiesManager = new EnemiesManager(mainCharacter, this);
		imageGameOver = Resource.getResourceImage("data/gameover_text.png");			// render game over image
		chicken = Resource.getResourceImage("data/chicken4.png");
		chickenTitle = Resource.getResourceImage("data/chicken3.jpg");
	}
	
	public void startGame() {															// start the thread
		thread.start();
	}
	
	@Override
	public void run() {
		while(true) {																	// basic game loop
			try {
				update();
//				repaint()
				repaint(100000);																// repaint will call paint and redraw the rectangle
				Thread.sleep(5);														// create delay
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void paint (Graphics g) {													// draw the screen	
		g.setColor(Color.decode("#f7f7f7"));											// set background color
		g.fillRect(0, 0, getWidth(), getHeight());										// getter methods comes with JFrame
//		g.setColor(Color.red);
//		g.drawLine(0, (int) GROUNDY, getWidth(), (int) GROUNDY);						// draw the horizon
		
		switch(gameState) {
		case GAME_INITIAL_STATE:														// before game starts
			mainCharacter.draw(g);														// draw main character
			Font fontTitle = new Font ("Courier New", 1, 30);							
			g.setFont (fontTitle);											
			g.setColor(Color.DARK_GRAY);
			g.drawString("#notmydownasaur", 150, 120);									// draw game tile
			Font fontSubtitle = new Font ("Courier New", 1, 15);							
			g.setFont (fontSubtitle);											
			g.setColor(Color.DARK_GRAY);
			g.drawString("press any key to start", 210, 160);
			g.drawImage(chickenTitle, 500, 110, null);
			break;
		case GAME_PLAY_STATE:															// when playing 
			clouds.draw(g);																// draw clouds , following order matters, from far to near
			land.draw(g);																// draw land
			mainCharacter.draw(g);														// draw character
			enemiesManager.draw(g);														// draw enemies
			g.drawString("Score " + String.valueOf(score), 500, 20);					// display score
			g.drawImage(chicken, 400, 200, null);
			break;
		case GAME_OVER_STATE:															// when game over
			clouds.draw(g);																// draw clouds
			land.draw(g);																// draw land
			mainCharacter.draw(g);														// draw character
			enemiesManager.draw(g);														// draw enemies
			g.drawImage(imageGameOver, 200, 100, null);									// draw game over image
			g.drawString("Final Score " + String.valueOf(score), 260, 140);				// display final score
			break;
		}
	}
	
	public void update() {																// integrate all update methods
		switch (gameState) {
			case GAME_PLAY_STATE:														// when playing, simultaneously invoking update methods
				mainCharacter.update();
				land.update();
				clouds.update();
				enemiesManager.update();
				if (!mainCharacter.getIsAlive()) {										// once character is isAlive is false, game over
					gameState = GAME_OVER_STATE;
				}
				break;
		}
	}
	
	public void increaseScore(int score) {												// increase score
		this.score += score;
	}
	
	private void resetGame() {															// restart game
		mainCharacter.setIsAlive(true);													// reset isAlive member variable
		mainCharacter.setX(50);															// reset character position
		mainCharacter.setY(60);
		enemiesManager.reset();															// clear out enemies
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {												// event listener method
		System.out.println(gameState);
		System.out.println(GAME_INITIAL_STATE);
		if (gameState == GAME_INITIAL_STATE) {											// press any key to start game
			gameState = GAME_PLAY_STATE;
		} else if (gameState == GAME_PLAY_STATE){										// while playing, press any key to jump
			mainCharacter.jump();
		} else if (gameState == GAME_OVER_STATE){										// press any key to restart game
			resetGame();
			score = 0;																	// score will be cleared when restart
			gameState = GAME_PLAY_STATE;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {

	}
	
}
