package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80B;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 30);
		arial_80B = new Font("Arial", Font.BOLD, 50);
	}
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		// TITLE STATE
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		// PLAY STATE
		if(gp.gameState == gp.playState) {
			// Do playstate stuff later
		}
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		// DIALOGUE
		if(gp.gameState == gp.dialougeState) {
			drawDialogueScreen();
		}
	}
	
	public void drawTitleScreen() {
		
		
		g2.setColor(new Color(0,0,0));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeigth);
		// TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,88F));
		String text = "Slice of work life";
		int x = getXforCenteredText(text);
		int y = gp.tileSize*3;
		
		// SHADOW
		g2.setColor(Color.gray);
		g2.drawString(text, x+5, y+5);
		// MAIN COLOR
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		// IMAGE of my dude
		
		x = gp.screenWidth/2 - (gp.tileSize*2)/2;
		y += gp.tileSize*2;
		g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
		
		
		// MENU
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,48F));
		
		text = "New Game";
		x = getXforCenteredText(text);
		y += gp.tileSize*3.5;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-gp.tileSize, y);
		}

		text = "Load Game";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "Quit";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			g2.drawString(">", x-gp.tileSize, y);
		}

	}
	public void drawPauseScreen() {
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.tileSize/2;
		g2.drawString(text, x, y);
	}
	
	public void drawDialogueScreen() {
		
		// WINDOW
		int x = gp.tileSize*2;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*4);
		int heigth = gp.tileSize*4;
		drawSubWindow(x, y, width, heigth);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,22F));
		x += gp.tileSize;
		y += gp.tileSize;
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);			
			y+= 40;
		}
	}
	
	public void drawSubWindow(int  x, int y, int width, int height) {
		Color c = new Color(0,0,0,200);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		
		return x;
	}
			
}
