package logic;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Map;

import javax.swing.Timer;

import constants.FontConsts;
import constants.IdConsts;
import constants.SizeConsts;
import constants.SpeedConsts;
import constants.StatsConsts;
import data.Background;
import data.Boat;
import data.GameStats;
import data.Parachutist;
import data.ParachutistsItem;
import data.Plane;
import data.Sea;
import gameInterface.GameController;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ParachutistsController extends JPanel implements GameController {
	
	private boolean isPlaying = false; 
	private GameStats gameStats;
	private Timer timer;
	private Background background;
	private Boat boat;
	private Parachutist parachutist;
	private Plane plane;
	private Sea sea;
	
	
	public ParachutistsController(Map<Integer, BufferedImage> imagesMap) {
		setFocusable(true);
		
		gameStats = new GameStats(StatsConsts.INITIAL_SCORE, StatsConsts.INITIAL_LIVES);
		background = new Background(imagesMap.get(IdConsts.BACKGROUND));
		boat = new Boat(SizeConsts.BOAT_X, SizeConsts.BOAT_Y, SizeConsts.BOAT_WIDTH, SizeConsts.BOAT_HEIGHT,
										imagesMap.get(IdConsts.BOAT));
		parachutist = new Parachutist(SizeConsts.PARACHUTE_X, SizeConsts.PARACHUTE_Y, 
				SizeConsts.PARACHUTE_WIDTH, SizeConsts.PARACHUTE_HEIGHT, imagesMap.get(IdConsts.PARACHUTIST));
		plane = new Plane(SizeConsts.PLANE_X, SizeConsts.PLANE_Y, 
				SizeConsts.PLANE_WIDTH, SizeConsts.PLANE_HEIGHT, imagesMap.get(IdConsts.PLANE));
		sea = new Sea(SizeConsts.SEA_X, SizeConsts.SEA_Y, SizeConsts.SEA_WIDTH, 
				SizeConsts.SEA_HEIGHT, imagesMap.get(IdConsts.SEA));
		isPlaying = true;
		timer = new Timer(SpeedConsts.TIMER_DELAY, null);
		startGame();
	}

	public void paint(Graphics g) {
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
	
	private void moveBoatRight() {
		if (boat.getPosX() < SizeConsts.BOAT_RIGHT_LIMIT) {
			boat.setPosX(boat.getPosX() + SizeConsts.BOAT_STEP_SIZE);
		}
	}
	
	private void moveBoatLeft() {
		if (boat.getPosX() > SizeConsts.BOAT_LEFT_LIMIT) {
			boat.setPosX(boat.getPosX()- SizeConsts.BOAT_STEP_SIZE);
		}
	}
	
	private void movePlane() {
		plane.setPosX(plane.getPosX() - SpeedConsts.PLANE_SPEED);
		if(plane.getPosX() <= 0) {
			plane.setPosX(SizeConsts.PLANE_RIGHT_LIMIT);
		}
	}
	
	private void moveParachute() {
		parachutist.setPosY(parachutist.getPosY() + SpeedConsts.PARACHUTE_SPEED);
	}
	
	private void enableKeyListener() {
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						if (boat.getIsVisible()) {
							moveBoatLeft();
						}
						break;
					case KeyEvent.VK_RIGHT:
						if (boat.getIsVisible()) {
							moveBoatRight();
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

			@Override
			public void keyReleased(KeyEvent e) {}
		});
	}
	
	private boolean areIntersecting(ParachutistsItem a, ParachutistsItem b) {
		Rectangle aRect = new Rectangle(a.getPosX(), a.getPosY(), a.getWidth(), a.getHeight());
		Rectangle bRect = new Rectangle(b.getPosX(), b.getPosY(), b.getWidth(), b.getHeight());
		
		return aRect.intersects(bRect);
	}
	
	private void enableActionListener() {
		timer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isPlaying){
					boolean isParachutistVisible = parachutist.getIsVisible();
					
					if(isParachutistVisible) {
						moveParachute();
					}
					
					if (plane.getIsVisible()) {
						movePlane();
					}
					
					// drop parachutist if plane reached his random position
					if (!isParachutistVisible && areIntersecting(plane, parachutist)) {
						parachutist.setIsVisible(true);
					}
					else if (areIntersecting(boat, parachutist)) {	
						gameStats.setScore(gameStats.getScore() + 10);
						parachutist.setIsVisible(false);
						// new random position for next drop from the plane
						parachutist.setPosX(SizeConsts.RANDOM.nextInt(SizeConsts.PARACHUTE_RANDOM_BASE));
						parachutist.setPosY(SizeConsts.PARACHUTE_Y);
					}
					else if (areIntersecting(sea, parachutist)){
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

	@Override
	public void startGame() {
		this.enableKeyListener();
		this.enableActionListener();
	}

	@Override
	public void restartGame() {
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

	@Override
	public void endGame() {
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
		frame.dispose();
	}
}
