package control;

import java.awt.Rectangle;

import data.Boat;
import data.Parachutist;
import data.ParachutistsItem;
import data.Plane;
import globalConstants.SizeConsts;
import globalConstants.SpeedConsts;

/**
 * A handler for all algorithm related issues:
 * Moving the boat, moving the plane and more.
 */
public class ParachutistsAlgHandler {
	
	public void moveBoatRight(Boat boat) {
		if (boat.getPosX() < SizeConsts.BOAT_RIGHT_LIMIT) {
			boat.setPosX(boat.getPosX() + SizeConsts.BOAT_STEP_SIZE);
		}
	}
	
	public void moveBoatLeft(Boat boat) {
		if (boat.getPosX() > SizeConsts.BOAT_LEFT_LIMIT) {
			boat.setPosX(boat.getPosX()- SizeConsts.BOAT_STEP_SIZE);
		}
	}
	
	public void movePlane(Plane plane) {
		plane.setPosX(plane.getPosX() - SpeedConsts.PLANE_SPEED);
		if(plane.getPosX() <= 0) {
			plane.setPosX(SizeConsts.PLANE_RIGHT_LIMIT);
		}
	}
	
	public void moveParachute(Parachutist parachutist) {
		parachutist.setPosY(parachutist.getPosY() + SpeedConsts.PARACHUTE_SPEED);
	}
	
	public boolean areIntersecting(ParachutistsItem a, ParachutistsItem b) {
		Rectangle aRect = new Rectangle(a.getPosX(), a.getPosY(), a.getWidth(), a.getHeight());
		Rectangle bRect = new Rectangle(b.getPosX(), b.getPosY(), b.getWidth(), b.getHeight());
		
		return aRect.intersects(bRect);
	}
}
