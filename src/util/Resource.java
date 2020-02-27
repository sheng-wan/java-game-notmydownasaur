package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resource {
	
	public static BufferedImage getResourceImage(String path) {  // set up the image loading method
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));                  // it will return an image
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
}
