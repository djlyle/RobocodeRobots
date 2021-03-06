package sjl;

import robocode.*;

/**
 * AdvancedEnemyBot
 */
public class AdvancedEnemyBot extends EnemyBot
{
	private double x;
	private double y;
	
	public AdvancedEnemyBot(){
		reset();
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public void reset(){
		super.reset();
		x = 0;
		y = 0;	
	}
	
	
	public void update(ScannedRobotEvent e, Robot robot)
	{
		super.update(e);
		double absBearingDeg = (robot.getHeading() + e.getBearing());
		if(absBearingDeg < 0){
			absBearingDeg += 360;
		}
		
		//Note: Use the _sine_ to get the X value because 0 deg is North
		//i.e. the robocode coordinate axis are essentially flipped
		x = robot.getX() + Math.sin(Math.toRadians(absBearingDeg)) * e.getDistance();
		
		//Note: Use the _cosine_ to get the Y value because 0 deg is North
		//i.e. the robocode coordinate axis are essentially flipped
		y = robot.getY() + Math.cos(Math.toRadians(absBearingDeg)) * e.getDistance();
	}
	
	public double getFutureX(long when){
		return x + Math.sin(Math.toRadians(getHeading())) * getVelocity() * when;
	}
	
	public double getFutureY(long when){
		return y + Math.cos(Math.toRadians(getHeading())) * getVelocity() * when;
	}
}
