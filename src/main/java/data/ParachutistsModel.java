package data;

public class ParachutistsModel {
	
	private final Background background;
	private final Boat boat;
	private final GameStats gameStats;
	private final Parachutist parachutist;
	private final Plane plane;
	private final Sea sea;
	
	public ParachutistsModel(Background background, Boat boat, GameStats gameStats, Parachutist parachutist,
			Plane plane, Sea sea) {
		this.background = background;
		this.boat = boat;
		this.gameStats = gameStats;
		this.parachutist = parachutist;
		this.plane = plane;
		this.sea = sea;
	}

	public Background getBackground() {
		return background;
	}

	public Boat getBoat() {
		return boat;
	}

	public GameStats getGameStats() {
		return gameStats;
	}

	public Parachutist getParachutist() {
		return parachutist;
	}

	public Plane getPlane() {
		return plane;
	}

	public Sea getSea() {
		return sea;
	}
}
