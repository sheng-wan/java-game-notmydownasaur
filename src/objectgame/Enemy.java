package objectgame;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Enemy {								// abstract class
	
	public abstract Rectangle getBound (); 
	
	public abstract void draw (Graphics g);
	
	public abstract void update ();
	
	public abstract boolean isOutOfScreen ();
	
	public abstract boolean isOver();
	
	public abstract boolean isScored();
	
	public abstract void setIsScored(boolean isScored);

}
