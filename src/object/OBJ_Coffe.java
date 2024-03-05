package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Coffe extends SuperObject{

	public OBJ_Coffe() {
		
		name = "Coffe";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/coffe.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
