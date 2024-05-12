package main;

import entity.NPC_BearMinimum;
import object.OBJ_Coffe;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Phone;
import object.OBJ_Portfolio;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	
	public void setObject() {
		
		
		
	}
	
	public void setNpc() {
		
		gp.npc[0] = new NPC_BearMinimum(gp);
		gp.npc[0].worldX = gp.tileSize*21;
		gp.npc[0].worldY = gp.tileSize*21;
	}
}
