package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Phone extends SuperObject{
	
	GamePanel gp;

	public OBJ_Phone(GamePanel gp) {
		this.gp = gp;

		name = "Phone";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/phone.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
