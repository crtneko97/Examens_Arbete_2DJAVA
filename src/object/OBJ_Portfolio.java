package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Portfolio extends SuperObject{
	
	GamePanel gp;

	 public OBJ_Portfolio(GamePanel gp) {
		 this.gp = gp;

		name = "Portfolio";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/portfolj.png"));
			uTool.scaledImage(image, gp.tileSize, gp.tileSize);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
