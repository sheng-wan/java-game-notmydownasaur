package userinterface;

import javax.swing.JFrame;

public class GameWindow extends JFrame{                   // JFrame library is for window display
	
	private GameScreen gameScreen;                        // inject the GameScreen object
	
	public GameWindow() {                                 // constructor
		super("#Not My Downasour");                       // game window name
		setSize(600, 175);                                // set window size
		setLocation(800, 400);                            // window startup position
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // set close operation
		gameScreen = new GameScreen();
		add(gameScreen);                                  
		addKeyListener(gameScreen);                       // add key listener 
	}
	
	public void startGame() {                             // call the startGame function from GameScreen
		gameScreen.startGame();
	}
	
	public static void main(String args[]) {              // main method to run the app
		GameWindow gw =	new GameWindow();
		gw.setVisible(true);
		gw.startGame();
	}
	
}