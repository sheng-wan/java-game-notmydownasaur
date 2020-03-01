package objectgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import util.Resource;

public class Clouds {
	
	private BufferedImage cloudImage;
	private List<Cloud> clouds;
	
	public Clouds() {																// constructor
		cloudImage = Resource.getResourceImage("data/cloud.PNG");					// case sensitive
		clouds = new ArrayList<Cloud>();
		
		Cloud cloud1 = new Cloud(); 												// create different cloud positions
		cloud1.posX = 100;
		cloud1.posY = 50;
		clouds.add(cloud1);
		
		cloud1 = new Cloud(); 
		cloud1.posX = 200;
		cloud1.posY = 100;
		clouds.add(cloud1);
		
		cloud1 = new Cloud(); 
		cloud1.posX = 300;
		cloud1.posY = 80;
		clouds.add(cloud1); 
		
		cloud1 = new Cloud(); 
		cloud1.posX = 450;
		cloud1.posY = 70;
		clouds.add(cloud1);
		
		cloud1 = new Cloud(); 
		cloud1.posX = 600;
		cloud1.posY = 150;
		clouds.add(cloud1);
	}
	
	public void draw(Graphics g) {													// draw the clouds on screen
		for (Cloud cloud : clouds) {
			g.drawImage(cloudImage, (int) cloud.posX, (int) cloud.posY, null);			
		}
	}
	
	public void update() {															// make cloud moving
		for(Cloud cloud : clouds) {
			cloud.posX --;
		}
		
		Cloud firstCloud = clouds.get(0);											// when a cloud is completely moved out of the window, add it back to the clouds list
		if (firstCloud.posX + cloudImage.getWidth() < 0) {
			firstCloud.posX = 600;
			clouds.remove(firstCloud);
			clouds.add(firstCloud);
		}
	}
	
	private class Cloud{															// cloud coordination
		float posX;
		float posY;
	}
	
}
