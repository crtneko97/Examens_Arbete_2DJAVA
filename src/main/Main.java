package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //When the window is being closed the app will stop running
		window.setResizable(false); //So that we cannot change the size on the window
		window.setTitle("Perfect Society"); // Sets the title for the project
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack(); //pack() causes this window to be sized to fit the preferred size and layouts of its subcomponents (=GamePanel).
		
		
		window.setLocationRelativeTo(null); // Not spesify the location of the window. = The window will be displayed at the center of the screen
		window.setVisible(true);
		
		gamePanel.setupGame();
		gamePanel.startGameThread();
		
	}

}
