package parachutistsConnector;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import control.ParachutistsController;
import data.Background;
import data.Boat;
import data.GameStats;
import data.Parachutist;
import data.ParachutistsModel;
import data.Plane;
import data.Sea;
import globalConstants.SizeConsts;

public class Parachutists {

	private final ParachutistsModel model;
	private final ParachutistsController controller;
	
	public Parachutists(Background background, Boat boat, GameStats gameStats, Parachutist parachutist,
			Plane plane, Sea sea) {
		this.model = new ParachutistsModel(background, boat, gameStats, parachutist, plane, sea);
		this.controller = new ParachutistsController(model);
	}
	
	public void start() {
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setBounds(SizeConsts.FRAME_X, SizeConsts.FRAME_Y, 
										SizeConsts.FRAME_WIDTH, SizeConsts.FRAME_HEIGHT);
		frame.setTitle("Parachutists");
		frame.setResizable(false);
		frame.add(this.controller);
		frame.setVisible(true);
	}
	
}
