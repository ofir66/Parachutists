package setup;

import java.awt.image.BufferedImage;
import java.util.Map;
import data.Background;
import data.Boat;
import data.GameStats;
import data.Parachutist;
import data.Plane;
import data.Sea;
import globalConstants.IdConsts;
import globalConstants.SizeConsts;
import globalConstants.StatsConsts;
import parachutistsConnector.Parachutists;


public class ParachutistsRunner {

	public static void main(String[] args) {
		ImageParser imgParser = new ImageParser();
		Map<Integer, BufferedImage> imagesMap = imgParser.createImagesMap();
		
		if (imagesMap == null) {
			return;
		}
		
		// run the game
		Background background = new Background(imagesMap.get(IdConsts.BACKGROUND));
		Boat boat = new Boat(SizeConsts.BOAT_X, SizeConsts.BOAT_Y, SizeConsts.BOAT_WIDTH, SizeConsts.BOAT_HEIGHT,
										imagesMap.get(IdConsts.BOAT));
		GameStats gameStats = new GameStats(StatsConsts.INITIAL_SCORE, StatsConsts.INITIAL_LIVES);
		Parachutist parachutist = new Parachutist(SizeConsts.PARACHUTE_X, SizeConsts.PARACHUTE_Y, 
				SizeConsts.PARACHUTE_WIDTH, SizeConsts.PARACHUTE_HEIGHT, imagesMap.get(IdConsts.PARACHUTIST));
		Plane plane = new Plane(SizeConsts.PLANE_X, SizeConsts.PLANE_Y, 
				SizeConsts.PLANE_WIDTH, SizeConsts.PLANE_HEIGHT, imagesMap.get(IdConsts.PLANE));
		Sea sea = new Sea(SizeConsts.SEA_X, SizeConsts.SEA_Y, SizeConsts.SEA_WIDTH, 
				SizeConsts.SEA_HEIGHT, imagesMap.get(IdConsts.SEA));
		
		Parachutists parachutists = new Parachutists(background, boat, gameStats, parachutist, plane, sea);
		
		parachutists.start();
	}

}
