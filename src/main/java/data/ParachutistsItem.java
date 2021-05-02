package data;

import java.awt.image.BufferedImage;
import gameInterface.GameItem;

public abstract class ParachutistsItem implements GameItem<Integer> {
	
	private int posX;
	private int posY;
	private final int width;
	private final int height;
	private final BufferedImage img;
	
	public ParachutistsItem(int posX, int posY, int width, int height, BufferedImage img) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.img = img;
	}
	
	public BufferedImage getImage() {	
		return img; 
	}
	
	public Integer getPosX() {
		return posX;
	}
	
	public void setPosX(Integer posX) {	
		this.posX = posX;
	}

	public Integer getPosY() {	
		return posY;
	}
	
	public void setPosY(Integer posY) {	
		this.posY = posY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
