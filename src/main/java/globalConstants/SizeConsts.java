package globalConstants;

import java.util.Random;

public class SizeConsts {
	
	public static final int FRAME_WIDTH = 800;
	public static final int FRAME_HEIGHT = 650;
	public static final int FRAME_X = 30;
	public static final int FRAME_Y = 0;
	
	public static final int PLANE_WIDTH = FRAME_WIDTH / 6;
	public static final int PLANE_HEIGHT = FRAME_HEIGHT / 5;
	public static final int PLANE_RIGHT_LIMIT = FRAME_WIDTH - PLANE_WIDTH;
	public static final int PLANE_X = PLANE_RIGHT_LIMIT;
	public static final int PLANE_Y = 10;
	
	public static final int SEA_WIDTH = FRAME_WIDTH;
	public static final int SEA_HEIGHT = FRAME_HEIGHT / 8;
	public static final int SEA_X = 0;
	public static final int SEA_Y = FRAME_HEIGHT - (FRAME_HEIGHT / 6);
	
	public static final int BOAT_WIDTH = FRAME_WIDTH / 7;
	public static final int BOAT_HEIGHT = FRAME_HEIGHT / 6;
	public static final int BOAT_X = (FRAME_WIDTH / 2) - (BOAT_WIDTH / 2);
	public static final int BOAT_Y = SEA_Y - BOAT_HEIGHT + 15;
	
	public static final int PARACHUTE_WIDTH = PLANE_WIDTH / 2;
	public static final int PARACHUTE_HEIGHT = PLANE_HEIGHT / 2;
	public static final int PARACHUTE_RANDOM_BASE = FRAME_WIDTH - PARACHUTE_WIDTH;
	public static final Random RANDOM = new Random();
	public static final int PARACHUTE_X = RANDOM.nextInt(PARACHUTE_RANDOM_BASE);
	public static final int PARACHUTE_Y = PLANE_Y + (PLANE_HEIGHT / 2);
	
	public static final int STATS_FONT_SIZE = 25;
	public static final int GAME_OVER_FONT_SIZE = 50;
	public static final int GAME_OVER_SCORE_FONT_SIZE = 24;
	public static final int RESTART_FONT_SIZE = 24;
	public static final int SCORE_X = 20;
	public static final int SCORE_Y = 45;
	public static final int LIVES_X = SCORE_X;
	public static final int LIVES_Y = SCORE_Y + 35;
	public static final int GAME_OVER_SCORE_RELATIVE_X_DIS = 51;
	public static final int GAME_OVER_SCORE_RELATIVE_Y_DIS = GAME_OVER_FONT_SIZE;
	public static final int RESTART_RELATIVE_X_DIS = 46;
	public static final int RESTART_RELATIVE_Y_DIS = 70;
	
	public static final int BOAT_RIGHT_LIMIT = FRAME_WIDTH - BOAT_WIDTH - 30;
	public static final int BOAT_LEFT_LIMIT = BOAT_WIDTH - 95;
	public static final int BOAT_STEP_SIZE = 30;
}
