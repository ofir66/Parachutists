package setup;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import constants.IdConsts;

public class ImageParser {
	
	public ImageParser() {
		
	}
	
	Map<Integer, BufferedImage> createImagesMap() {
		Map<Integer, BufferedImage> imagesMap = new HashMap<>();
		
		try {
			imagesMap.put(IdConsts.BACKGROUND, ImageIO.read(new File("resources/background.png")));
			imagesMap.put(IdConsts.BOAT, ImageIO.read(new File("resources/boat.png")));
			imagesMap.put(IdConsts.PARACHUTIST, ImageIO.read(new File("resources/parachutist.png")));
			imagesMap.put(IdConsts.PLANE, ImageIO.read(new File("resources/plane.png")));
			imagesMap.put(IdConsts.SEA, ImageIO.read(new File("resources/sea.png")));
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
