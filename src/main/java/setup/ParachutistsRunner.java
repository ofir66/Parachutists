package setup;

import java.awt.image.BufferedImage;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import globalConstants.SizeConsts;
import logic.ParachutistsController;


public class ParachutistsRunner {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		ParachutistsController gamePlay;
		ImageParser imgParser = new ImageParser();
		Map<Integer, BufferedImage> imagesMap = imgParser.createImagesMap();
		
		// run the game
		gamePlay = new ParachutistsController(imagesMap);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setBounds(SizeConsts.FRAME_X, SizeConsts.FRAME_Y, 
										SizeConsts.FRAME_WIDTH, SizeConsts.FRAME_HEIGHT);
		frame.setTitle("Parachutists");
		frame.setResizable(false);
		frame.add(gamePlay);
		frame.setVisible(true);
	}

}
