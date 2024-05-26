package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_BearMinimum extends Entity{
	
	
	public NPC_BearMinimum(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {
		up1 = setup("/npc/npc1_up1");
		up2 = setup("/npc/npc1_up2");
		down1 = setup("/npc/npc1_down1");
		down2 = setup("/npc/npc1_down2");
		left1 = setup("/npc/npc1_left1");
		left2 = setup("/npc/npc1_left2");
		right1 = setup("/npc/npc1_right1");
		right2 = setup("/npc/npc1_right2");
	}
	public void setDialogue() {
		
		dialogues[0] = "Yo Yo! ^_~";
		dialogues[1] = "I'm Bear";
		dialogues[2] = "Almost done w school mate?\nShie time flies..";
		dialogues[3] = "Care for a break? Mr ***** is grumpy \nso try to avoid him :P";
	}
	public void setAction() {
		
		actionLockCounter ++;
		
		
		if(actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1; // pick a number from 1 to 100
			
			if(i <= 25) {
				direction = "up";
			}
			if(i > 25 && i <= 50) {
				direction = "down";
			}
			if(i > 50 && i <= 75) {
				direction = "left";
			}
			if(i > 75 && i <= 100) {
				direction = "right";
			}
			
			actionLockCounter = 0;
		}
	}
	public void speak() {
		
		
		super.speak();
	}

}


