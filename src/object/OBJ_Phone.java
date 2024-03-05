package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Phone extends SuperObject{

	public OBJ_Phone() {
		
		name = "Phone";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/phone.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
