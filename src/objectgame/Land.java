package objectgame;

import static userinterface.GameScreen.GROUNDY;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import userinterface.GameScreen;
import util.Resource;

public class Land {

	private List<ImageLand> listImage;
	private BufferedImage imageLand1, imageLand2, imageLand3;
	private Random random;
	
	public Land(GameScreen game) {											// constructor
		random = new Random();
		imageLand1 = Resource.getResourceImage("data/land1.png");			// render land image
		imageLand2 = Resource.getResourceImage("data/land2.png");
		imageLand3 = Resource.getResourceImage("data/land3.png");
		listImage = new ArrayList<ImageLand>();
		int numberOfLandTitle = 600 / imageLand1.getWidth() + 2;			// calculate how many land images needed to cover the screen
		for (int i = 0; i < numberOfLandTitle; i++) {						// use for loop to calculate each land image's starting posX 
			ImageLand imageLand = new ImageLand();
			imageLand.posX = (int) (i * imageLand1.getWidth());
			imageLand.image = getImageLand();
			listImage.add(imageLand);
		}
	}
	
	private BufferedImage getImageLand() {									// generate random landscape
		int i = random.nextInt(9);
		switch(i) {															// switch method - similar to redux
		case 0: return imageLand1;											// bumppier land
		case 1: return imageLand3;											// bumpy land
		default: return imageLand2;											// smooth land
		}
	}
	
	public void draw(Graphics g) {											// draw the land
		for (ImageLand imageLand : listImage) {	
			g.drawImage(imageLand.image, imageLand.posX, (int) GROUNDY - 20, null);
		}
	}
	
	public void update() {													// moving the land image, faster than cloud( cloud is --)
		for (ImageLand imageLand : listImage) {
			imageLand.posX -= 2;
		}
		ImageLand firstElement = listImage.get(0);							// when the first land image moved out of gamescreen, add it to the back of the land image list
		if(firstElement.posX + imageLand1.getWidth() < 0) {
			firstElement.posX = listImage.get(listImage.size() - 1).posX + imageLand1.getWidth();
			listImage.add(firstElement);
			listImage.remove(0);
		}
	}
	
	private class ImageLand {												// child class
		int posX;
		BufferedImage image;
	}	

}
