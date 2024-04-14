package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Coffe extends SuperObject{
	
	GamePanel gp;

	public OBJ_Coffe(GamePanel gp) {
		this.gp = gp;
		
		name = "Coffe";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/coffe.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
