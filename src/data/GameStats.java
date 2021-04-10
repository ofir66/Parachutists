package data;

public class GameStats {

	private int score;
	private int lives;
	
	public GameStats(int score, int lives) {
		this.score = score;
		this.lives = lives;
	}

	public int getScore() {
		return score;
	}

	public int getLives() {
		return lives;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}
}
