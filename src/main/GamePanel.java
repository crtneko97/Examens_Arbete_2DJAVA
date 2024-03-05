package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import org.w3c.dom.ls.LSOutput;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

// This is basically a JPanel class with a little more functions and more room to work
public class GamePanel extends JPanel implements Runnable{
	
	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tiles
	final int scale = 3; // Den här gör så att vi scalear tilesen i.om de kommer bli för litet annars. så 16 x 3(scale) = 48
	
	public final int tileSize = originalTileSize * scale; // 48x48 tile tileSize = actuall tilesize that will be displayed on the screen
	// maxScreenCol + row blir alltså <- 16 -> x 12 ration blir då också 4 by 3 kan skrivar 4:3 
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 48*16 = 768 pixlar
	public final int screenHeigth = tileSize * maxScreenRow; // 48*12 = 576 pixels
	
	// WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	
	// FPS
	int FPS = 60;
	
	
	// Thread is something u can start and stop
	// When you want repeat some process again and again such as drawing 60 times a second thread is very useful
	// To run a thread we need to implement runnable
	
	// SYSTEM
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Sound sound = new Sound();
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	
	
	// ENTITY AND OBJECT
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[10];
	
	// Set player's default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeigth)); // setPreferredSize(new Dimension(screenWidth, screenHeigth)) = Set the size of this class (JPanel) 
		this.setBackground(Color.black); //Background color Black for now
		this.setDoubleBuffered(true); // If set to true, all the drawing from this component will be done in an offscreen painting buffer. In short, enabling this can improve game's rendering performance.
		this.addKeyListener(keyH); // adding the keyhandler to this gamePanel, now the GamePanel can recognize key input and also we add one more line
		this.setFocusable(true); // with this, this GamePanel can be " Focused" to receive key input.
	}

	public void setupGame() {
		
		aSetter.setObject();
		
		playMusic(0);
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this); // this == GamePanel so we are passing this GamePanel class to this GameThread Constructor so this is how u instance a thread
		gameThread.start();
	}

	// So basically when we start tgus ganeThread it will automatically call this run method
	@Override
//	public void run() {
//		// in this run method, we will create a game loop which will be the core of our game
//		
//		// Sleeper method
//		double drawInterval = 1000000000/FPS;
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		
//		
//		// gameThread != null means, as long as this gameThread exists, it repeats the process that is written inside of the these brackets
//		while(gameThread != null) {
//			//Need to limit this loop for how fast it should update things
//			// check the cutrrent time, we type like this
////			long currentTime= System.nanoTime(); //Returns the current value of the running Java Virtual Machine's high -resolution time source, in nanoseconds
////			Long currenTime2 = System.currentTimeMillis(); // this returns the current time in milliseconds this is okay too, but nano is more precise
////			System.out.println("Current Time: "+currentTime);
//			
//			//Two things that we're gonna do in this loop is
//			// 1 UPDATE: update information such as character position
//			// this means that we want to update the player information based on where the character is positioned
//			update();
//			// 2 DRAW: draw the screen with the updated information
//			// based on this updated information, we redaw the screen
//			
//			repaint(); // This is how we call the paintComponent
//			
//			// figure out how much time until the next draw time
//			
//			// let the thread sleep for the remaining time for the next draw time
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime(); // subtract the current time from nextDrawTime so this returns how much time remaining until the nextDrawTime
//				// convert nanoseconds to milliseconds
//				remainingTime = remainingTime/100000; // so we can accept this as milliseconds
//				
//				// so if this update and repaint took more than this drawInterval then no time is left this thread doesn't need to sleep since we already used the allocated time so we put 0 to the remainingTime i doubt this will happen in my game but just in case
//				if(remainingTime < 0) {
//					remainingTime = 0;
//				}
//
//				Thread.sleep((long) remainingTime); // this sleep basically pause the game loop so it won't do anything until this sleep time is over
//				
//				nextDrawTime += drawInterval; //which means 0.01666 seconds later will be the nextDrawTime
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	public void run() {
		double drawIntterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		//display FPS
		long timer = 0;
		int drawCount = 0;
		
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime(); // 30:30 ryisnow
			
			delta += (currentTime - lastTime) / drawIntterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			
			if(timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
			
			
		}
	}
	
	
	public void update() {
		//Change the player position
		
		// x row y coloumn so whenever player hit these keys the key input is catched by KeyHandler and this update method updates the player coordinates then after this update, it's gonna call this paintComponent that draws a rectangle with updated player X and Y positions
		player.update();
	}
	
	//this paintComponent is actually a built-in method in Java so the name isnt original name it's a stand method name to draw on jpanel
	// Graphics A class that has many functions to draw objects on the screen.
	public void paintComponent(Graphics g) {
		
		// whenever you use this paintComponent method on JPanel, you need to type this this "super" means the parent class of this class
		// and in this case, the parent class means JPanel because this GamePanel is a subclass of JPanel, it's a practice that is set by Java to make this work
		
		super.paintComponent(g);
		
		//so imagine this Graphics is you pencil or paintbrush and before using this Graphics we're gonna convert this Graphics to Graphics2D class
		// Graphics2D class extends the Graphics class to provide more sophisticated control over geometry, coordinate transformations, color management, and text layout.
		//so this means we change this Graphics g to this Graphics2D class this Graphics and Graphics2D are kind of similar but this Graphics2D has a bit more functions
		Graphics2D g2 = (Graphics2D)g;
		
		// TILE
		tileM.draw(g2);
		
		// OBJECT
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		// PLAYER
		player.draw(g2);
		
		//dispose of this graphics context and release any system resources that it is using. the program still works without this line but this is a good practice to save some memories
		g2.dispose();
	}
	public void playMusic(int i) {
		
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	
	public void stopMusic() {
		
		sound.stop();
	}
	public void playSE(int i) {
		
		sound.setFile(i);
		sound.play();
	}
	
}
