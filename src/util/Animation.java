package util;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {
	
	private List<BufferedImage> frames;
	private int frameIndex = 0;
	private int deltaTime;												// time difference between previous frame was drawn and the current frame
	private long previousTime;
	
	public Animation(int deltaTime) {
		this.deltaTime = deltaTime;
		frames = new ArrayList<BufferedImage>();
	}
	
	public void update() {												// update the character frames
		if(System.currentTimeMillis() - previousTime > deltaTime) {		// if current time in milliseconds - previous frame drawn time > deltatime, increase index 
			frameIndex ++;
			if (frameIndex >= frames.size()) {							// if frame index is >= current frames' size, reset frame index
				frameIndex = 0;
			}
			previousTime = System.currentTimeMillis();					// reassign previous time
		}
	}
	
	public void addFrame(BufferedImage frame) {							// add frame to the frames variable
		frames.add(frame);
	}
	
	public BufferedImage getFrame() {									// frame getter
		if(frames.size() > 0) {
			return frames.get(frameIndex);
		}
		return null;
	}
}
