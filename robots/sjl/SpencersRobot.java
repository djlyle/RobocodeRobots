package sjl;
import robocode.*;
import java.awt.Point;
import java.lang.Math;
//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

enum RobotState
{
   HUNT,
   PATROL,
   HIDE
}
/**
 * SpencersRobot - a robot by (your name here)
 */
public class SpencersRobot extends Robot
{
	private RobotState myState;
	private AdvancedEnemyBot myEnemyBot = new AdvancedEnemyBot();
	
	/**
	 * run: SpencersRobot's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar
		double enemyHeading;
		myState = RobotState.PATROL;
		
		// Robot main loop
		while(true) {
		    turnRadarRight(360);
			switch(myState)
			{
				case PATROL:
					patrol();
				break;
				case HIDE:
					hide();
				break;
				case HUNT:
				    hunt();
				break;
			} 
			
		}
	}
	
	private void patrol(){
		System.out.println("In patrol function");
		// Replace the next 4 lines with any behavior you would like
		ahead(25);
		turnRight(10);
	}
	
	private void hide(){
		System.out.println("In hide function");
		double absBearingDeg = getAbsoluteBearing(getHeading(),myEnemyBot.getBearing());
		turnToHeading((absBearingDeg+180)%360);
		ahead(25);
	}
	
	//Calculate absolute bearing to something (e.g. an enemy robot)
	//based on its relative bearing
	private double getAbsoluteBearing(double absoluteHeading, double relativeBearing)
	{
		double absBearingDeg = absoluteHeading + relativeBearing;
		if(absBearingDeg < 0)
		{
			absBearingDeg += 360;
		}
		return absBearingDeg;
	}
	
	private void hunt(){
		System.out.println("In hunt function");
		double absBearingDeg = getAbsoluteBearing(getHeading(),myEnemyBot.getBearing());
		
		turnToHeading(absBearingDeg);
		ahead(25);
		fire(3);
	}
	
	//Turn robot until facing absolute heading 
	//where absolute heading is between 0 and 360 degrees
	private void turnToHeading(double absoluteHeading)
	{
		double currentHeading = getHeading();
		double delta;
		if(currentHeading > absoluteHeading)
		{
		  delta = currentHeading - absoluteHeading;
		}
		else
		{
		  delta = absoluteHeading - currentHeading;
		}
		turnRight(delta);	
	}
	
	public void onRobotDeath(RobotDeathEvent e)
	{
		switch(myState)
		{
			case PATROL:
			break;
			case HUNT:
				if(e.getName().equals(myEnemyBot.getName()))
				{
					myEnemyBot.reset();	
					myState = RobotState.PATROL;
				}	
			break;	
			case HIDE:
			break;
		}	
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		switch(myState)
		{
				case PATROL:
					// Replace the next line with any behavior you would like
					myEnemyBot.update(e,this);	
					myState = RobotState.HUNT;	
				break;
				case HUNT:
					if(e.getName().equals(myEnemyBot.getName()))
					{
						myEnemyBot.update(e,this);
					}
					if(getEnergy() < 25)
					{
						myState = RobotState.HIDE;
					}
				break;
				case HIDE:
					if(e.getDistance() < myEnemyBot.getDistance())
					{
						myEnemyBot.update(e,this);
					}
				break;
		}
		System.out.println("myEnemyBot name: "+myEnemyBot.getName());
		System.out.println("myEnemyBot position: "+myEnemyBot.getX()+","+myEnemyBot.getY());
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(20);
	}	
}