package data;

import java.awt.image.BufferedImage;


public class Plane extends ParachutistsItem {
	
	private boolean isVisible;
	
	public Plane (int posX, int posY, int width, int height, BufferedImage img) {
		super(posX, posY, width, height, img);
		
		isVisible = true;
	}
	
	public boolean getIsVisible() {
		return isVisible;
	}


	public void setIsVisible(boolean canMove) {
		this.isVisible = canMove;
	}
}
