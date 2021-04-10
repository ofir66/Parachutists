package data;

import java.awt.image.BufferedImage;


public class Boat extends ParachutistsItem {

	private boolean isVisible;
	
	public Boat (int posX, int posY, int width, int height, BufferedImage img) {
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



