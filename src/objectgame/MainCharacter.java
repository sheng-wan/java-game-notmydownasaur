package objectgame;

import static userinterface.GameScreen.GRAVITY;
import static userinterface.GameScreen.GROUNDY;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import util.Animation;
import util.Resource;


public class MainCharacter {

	private float x = 0;                                                   	// x coordinate from top left corner
	private float y = 0;                                                   	// y coordinate
	private float speedY = 0;                                              	// character moving speed
	private Animation characterRun;                                        	// inject character running animation
	private Rectangle rect;													// rectangle to define object appearane
	private boolean isAlive = true;											// character alive status
	
	public MainCharacter() {												// constructor
		characterRun = new Animation(200);									// instansiate new characterRun object and pass in 200ms as deltatime for leg moving effect
		characterRun.addFrame(Resource.getResourceImage("data/main-character1.png"));
		characterRun.addFrame(Resource.getResourceImage("data/main-character2.png"));
		rect = new Rectangle();												// assign the character a rectangle
	}
	
	public void draw (Graphics g) {											// draw the character
		g.setColor(Color.black);
		// draw the rectangle with coordination x, y, width, height
//		g.drawRect((int) x, (int) y, characterRun.getFrame().getWidth(), characterRun.getFrame().getHeight());
		g.drawImage(characterRun.getFrame(), (int) x, (int) y, null);		// draw the main character
	}
	
	public Rectangle getBound() {											// get current character rectangle position
		return rect;
	}
	
	public void jump() {													// character jumpping logic
		speedY = -4;														// jump height
		y += speedY;														// distance y from origin will decrease, representing the jump
	}
	
	public void update() {													// update character position
		characterRun.update();												// update leg moving animation
		if (y >= GROUNDY - characterRun.getFrame().getHeight()) {			// if distance (y) between origin point >= ground level - my rectangle height.
			speedY = 0;														// drop the speedY to 0
			y = GROUNDY - characterRun.getFrame().getHeight();				// land the character on the ground
		} else {
			speedY+=GRAVITY;												// if not, keep dropping
			y+=speedY;
		}
		rect.x = (int) x;													// defining the character rectangle position
		rect.y = (int) y;
		rect.width = characterRun.getFrame().getWidth();
		rect.height = characterRun.getFrame().getHeight();
		
	}
	
	// getters and setters
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getSpeedY() {
		return speedY;
	}
	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}	
	public boolean getIsAlive() {
		return isAlive;
	}
	public void setIsAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
}