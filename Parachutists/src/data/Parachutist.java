package data;

import java.awt.image.BufferedImage;


public class Parachutist extends ParachutistsItem {
	
	private boolean isVisible;

	
	public Parachutist (int posX, int posY, int width, int height, BufferedImage img) {
		super(posX, posY, width, height, img);
		
		isVisible = false;
	}
	

	public boolean getIsVisible() {
		return isVisible;
	}


	public void setIsVisible(boolean canMove) {
		this.isVisible = canMove;
	}
}

