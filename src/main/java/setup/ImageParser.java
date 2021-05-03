package setup;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import globalConstants.IdConsts;

public class ImageParser {
	
	public boolean insertImage(Map<Integer, BufferedImage> imagesMap, String imageName, int imageId) {
		boolean wasInserted = false;
		
		try {
			imagesMap.put(imageId, ImageIO.read(new File(ResourcesConsts.PATH + imageName)));
			wasInserted = true;
		}
		catch (IOException e) {
			System.err.println("Image " + imageName + " wasn't found.\n"
					+ "Verify current working directory and "
					+ "the path leading for 'resources' folder.\n"
					+ "Current working directory: " + System.getProperty("user.dir"));
			e.printStackTrace();
		}
		
		return wasInserted;
	}
	
	Map<Integer, BufferedImage> createImagesMap() {
		Map<Integer, BufferedImage> imagesMap = new HashMap<Integer, BufferedImage>();
		boolean wasBackgroundInserted = this.insertImage(imagesMap, "background.png", IdConsts.BACKGROUND);
		boolean wasBoatInserted = this.insertImage(imagesMap, "boat.png", IdConsts.BOAT);
		boolean wasParachutistInserted = this.insertImage(imagesMap, "parachutist.png", IdConsts.PARACHUTIST);
		boolean wasPlaneInserted = this.insertImage(imagesMap, "plane.png", IdConsts.PLANE);
		boolean wasSeaInserted = this.insertImage(imagesMap, "sea.png", IdConsts.SEA);
		
		if (wasBackgroundInserted && wasBoatInserted && wasParachutistInserted && wasPlaneInserted && wasSeaInserted) {
			return imagesMap;
		}
		
		return null;
		
	}
}
