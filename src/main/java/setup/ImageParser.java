package setup;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import globalConstants.IdConsts;

public class ImageParser {
	
	public ImageParser() {
		
	}
	
	Map<Integer, BufferedImage> createImagesMap() {
		Map<Integer, BufferedImage> imagesMap = new HashMap<Integer, BufferedImage>();
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		try {
			imagesMap.put(IdConsts.BACKGROUND, ImageIO.read(new File(ResourcesConsts.PATH + "background.png")));
			imagesMap.put(IdConsts.BOAT, ImageIO.read(new File(ResourcesConsts.PATH + "boat.png")));
			imagesMap.put(IdConsts.PARACHUTIST, ImageIO.read(new File(ResourcesConsts.PATH + "parachutist.png")));
			imagesMap.put(IdConsts.PLANE, ImageIO.read(new File(ResourcesConsts.PATH + "plane.png")));
			imagesMap.put(IdConsts.SEA, ImageIO.read(new File(ResourcesConsts.PATH + "sea.png")));
		} catch (IOException e) {
			System.out.println("Wrong path was given for the image.\n"
												+ "Verify current working directory and "
												+ "edit the path to lead for 'resources' folder.\n"
												+ "Current working directory: " + System.getProperty("user.dir"));
			e.printStackTrace();
		}
		
		return imagesMap;
	}
}
