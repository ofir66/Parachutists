package data;

import java.awt.image.BufferedImage;


public class Background {
	
	private BufferedImage img;
	
	public Background (BufferedImage img) {
		this.img = img;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}
}
