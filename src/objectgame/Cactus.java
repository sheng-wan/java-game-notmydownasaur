package objectgame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import util.Resource;

public class Cactus extends Enemy {
	
	private BufferedImage cactusImage;
	private int posX, posY; 
	private Rectangle rect;
	private MainCharacter mainCharacter;
	private boolean isScored = false;
	
	public Cactus(MainCharacter mainCharacter) {						// constructor, inject mainCharacter
		this.mainCharacter = mainCharacter;
		cactusImage = Resource.getResourceImage("data/cactus1.png");	// get cactus image, set position and rectangle
		posX = 200;
		posY = 70;
		rect = new Rectangle();
	}
	
	public void update() {												// make cactus moving
		posX -= 2;
		rect.x = posX;
		rect.y = posY;
		rect.width = cactusImage.getWidth();
		rect.height = cactusImage.getHeight();
	}
	
	@Override
	public void draw(Graphics g) {										// override abstract method, draw the cactus
		g.drawImage(cactusImage, posX, posY, null);
	}
	
	@Override
	public Rectangle getBound() {										// override abstract method, get the cactus rectangle coordinates
		return rect;
	}
	
	@Override
	public boolean isOutOfScreen() {									// override abstract method,check if the cactus is out of screen
		return (posX + cactusImage.getWidth() < 0);
	}
	
	@Override
	public boolean isOver() {											// override abstract method, check if character jumpped over cactus
		return (mainCharacter.getX() > posX);
	}
	
	@Override
	public boolean isScored() {											// override abstract method, check if the character earned score
		return isScored;
	}
	
	@Override
	public void setIsScored(boolean isScored) {							// override abstract method, set the score for the character
		this.isScored = isScored ;
	}
	
	// setters
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public void setCactusImage(BufferedImage cactusImage) {
		this.cactusImage = cactusImage;
	}
	
}