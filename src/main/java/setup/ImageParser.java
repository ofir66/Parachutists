package setup;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import globalConstants.IdConsts;
import view.ParachutistsDisplay;

public class ImageParser {
	
	/**
	 * Inserts an image resource into a given images map.
	 * @param imagesMap - the images map.
	 * @param imageName - the name of the image resource.
	 * @param imageId - the id of the image resource.
	 * @return true if insertion succeed and false otherwise.
	 */
	private boolean insertImage(Map<Integer, BufferedImage> imagesMap, String imageName, int imageId) {
		boolean wasInserted = false;
		
		try {
			imagesMap.put(imageId, ImageIO.read(new File(ResourcesConsts.PATH + imageName)));
			wasInserted = true;
		}
		catch (IOException e) {
			ParachutistsDisplay.printToStderr("Image " + imageName + " wasn't found.\n"
					+ "Verify current working directory and "
					+ "the path leading for 'resources' folder.\n"
					+ "Current working directory: " + System.getProperty("user.dir"));
			e.printStackTrace();
		}
		
		return wasInserted;
	}
	
	/** Creates a map of image identifications mapped to their BufferedImage representation
	 * @return Map of image identifications mapped to their BufferedImage representation.
	 * The value null will be returned if one image or more couldn't not be inserted into a new created map.
	 */
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
