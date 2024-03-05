package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	
	/**
	 *  
	 * Entity class
	 * This stores variables that will be used
	 * in player, monster and NPC classes.
	 * 
	 * and this will be the parent class for the Player class and any other character classes
	 * **/ 
	
	public int worldX, worldY;
	public int speed;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea; // setting up the collision for the player
	public int solidAreaDefaultX, solidAreaDefaulY;
	public boolean collisionisOn = false;

}
