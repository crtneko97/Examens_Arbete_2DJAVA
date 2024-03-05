package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Portfolio extends SuperObject{

	 public OBJ_Portfolio() {
		
		name = "Portfolio";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/portfolj.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
