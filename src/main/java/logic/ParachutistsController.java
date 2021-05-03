package logic;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import data.Background;
import data.Boat;
import data.GameStats;
import data.Parachutist;
import data.ParachutistsModel;
import data.Plane;
import data.Sea;
import gameInterface.GameController;
import globalConstants.FontConsts;
import globalConstants.SizeConsts;
import globalConstants.SpeedConsts;
import globalConstants.StatsConsts;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ParachutistsController extends JPanel implements GameController {
	
	private final ParachutistsModel model;
	private boolean isPlaying; 
	private Timer timer;
	private final ParachutistsAlgHandler parachutistsAlgHandler;
	
	
	public ParachutistsController(ParachutistsModel model) {
		setFocusable(true);
		
		this.model = model;
		isPlaying = true;
		timer = new Timer(SpeedConsts.TIMER_DELAY, null);
		parachutistsAlgHandler = new ParachutistsAlgHandler();
		startGame();
	}

	public void paint(Graphics g) {
		GameStats gameStats = this.model.getGameStats();
		Background background = this.model.getBackground();
		Sea sea = this.model.getSea();
		Boat boat = this.model.getBoat();
		Plane plane = this.model.getPlane();
		Parachutist parachutist = this.model.getParachutist();
		
		// draw backGround
		g.drawImage(background.getImg(), 0, 0, null);

		// draw stats
		g.setColor(Color.black);
		g.setFont(FontConsts.STATS_FONT);
		g.drawString("Score : "+ gameStats.getScore(), SizeConsts.SCORE_X, SizeConsts.SCORE_Y);
		g.drawString("Lives : "+ gameStats.getLives(), SizeConsts.LIVES_X, SizeConsts.LIVES_Y);
		
		// draw game items
		g.drawImage(sea.getImage(), sea.getPosX(), sea.getPosY(), 
								sea.getWidth(), sea.getHeight(), null);
		
		if (boat.getIsVisible()) {
			g.drawImage(boat.getImage(), boat.getPosX(), boat.getPosY(), 
					boat.getWidth(), boat.getHeight(), null);
		}
		
		if (plane.getIsVisible()) {
			g.drawImage(plane.getImage(), plane.getPosX(), plane.getPosY(), 
					plane.getWidth(), plane.getHeight(), null);
		}

		if(parachutist.getIsVisible()) {
			g.drawImage(parachutist.getImage(), parachutist.getPosX(), parachutist.getPosY(), 
									parachutist.getWidth(), parachutist.getHeight(), null);
		}
		
		// when game over
		if(gameStats.getLives() == 0 ) {
			g.setColor(Color.red);
			
			// enter "game over" string in the middle
			String gameOverTxt = "Game Over";
			g.setFont(FontConsts.GAME_OVER_FONT);
			Graphics2D g2d = (Graphics2D)g.create();
			FontMetrics fm = g2d.getFontMetrics();
			int gameOverX = (SizeConsts.FRAME_WIDTH - fm.stringWidth(gameOverTxt)) / 2;
			int gameOverY = ((SizeConsts.FRAME_HEIGHT - fm.getHeight()) / 2);
			g.drawString(gameOverTxt, gameOverX, gameOverY);
			
			// g.setFont(FontConsts.GAME_OVER_SCORE_FONT);
			// int gameOverScoreX = gameOverX + SizeConsts.GAME_OVER_SCORE_RELATIVE_X_DIS;
			int gameOverScoreY = gameOverY + SizeConsts.GAME_OVER_SCORE_RELATIVE_Y_DIS;
			g.drawString("Score: " +  gameStats.getScore(), gameOverX, gameOverScoreY);
			
			g.setFont(FontConsts.RESTART_FONT);
			int restartY = gameOverScoreY + SizeConsts.RESTART_RELATIVE_Y_DIS;
			g.drawString("Click Enter for restart" , 
					gameOverX, restartY);
		}

		g.dispose();
	}
	
	private void enableKeyListener() {
		this.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {}

			public void keyPressed(KeyEvent e) {
				Boat boat = model.getBoat();
				
				switch(e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						if (boat.getIsVisible()) {
							parachutistsAlgHandler.moveBoatLeft(boat);
						}
						break;
					case KeyEvent.VK_RIGHT:
						if (boat.getIsVisible()) {
							parachutistsAlgHandler.moveBoatRight(boat);
						}
						break;
					case KeyEvent.VK_ENTER:
						if(!isPlaying) {
							restartGame();
						}
						break;
					case KeyEvent.VK_ESCAPE:
						endGame();
						break;
				}
			}

			public void keyReleased(KeyEvent e) {}
		});
	}
	
	private void enableActionListener() {
		timer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Parachutist parachutist = model.getParachutist();
				Boat boat = model.getBoat();
				Plane plane = model.getPlane();
				GameStats gameStats = model.getGameStats();
				Sea sea = model.getSea();
				
				
				if (isPlaying){
					boolean isParachutistVisible = parachutist.getIsVisible();
					
					if(isParachutistVisible) {
						parachutistsAlgHandler.moveParachute(parachutist);
					}
					
					if (plane.getIsVisible()) {
						parachutistsAlgHandler.movePlane(plane);
					}
					
					// drop parachutist if plane reached his random position
					if (!isParachutistVisible && parachutistsAlgHandler.areIntersecting(plane, parachutist)) {
						parachutist.setIsVisible(true);
					}
					else if (parachutistsAlgHandler.areIntersecting(boat, parachutist)) {	
						gameStats.setScore(gameStats.getScore() + 10);
						parachutist.setIsVisible(false);
						// new random position for next drop from the plane
						parachutist.setPosX(SizeConsts.RANDOM.nextInt(SizeConsts.PARACHUTE_RANDOM_BASE));
						parachutist.setPosY(SizeConsts.PARACHUTE_Y);
					}
					else if (parachutistsAlgHandler.areIntersecting(sea, parachutist)){
						gameStats.setLives(gameStats.getLives() - 1);
						parachutist.setIsVisible(false);
						parachutist.setPosX(SizeConsts.RANDOM.nextInt(SizeConsts.PARACHUTE_RANDOM_BASE));
						parachutist.setPosY(SizeConsts.PARACHUTE_Y);
					}	
				}
				
				if(gameStats.getLives() == 0) {
					isPlaying = false;
					parachutist.setIsVisible(false);
					plane.setIsVisible(false);
					boat.setIsVisible(false);
				}
				
				repaint();
			}
		});
		timer.start();
	}

	public void startGame() {
		this.enableKeyListener();
		this.enableActionListener();
	}

	public void restartGame() {
		Boat boat = model.getBoat();
		Plane plane = model.getPlane();
		GameStats gameStats = model.getGameStats();
		
		gameStats.setScore(0);
		gameStats.setLives(StatsConsts.INITIAL_LIVES);
		boat.setPosX(SizeConsts.BOAT_X);
		boat.setPosY(SizeConsts.BOAT_Y);
		boat.setIsVisible(true);
		plane.setPosX(SizeConsts.PLANE_X);
		plane.setPosY(SizeConsts.PLANE_Y);
		plane.setIsVisible(true);
		isPlaying = true;
		repaint();
	}

	public void endGame() {
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
		frame.dispose();
		System.exit(0);
	}
}
