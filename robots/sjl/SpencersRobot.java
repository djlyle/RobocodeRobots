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
			switch(myState)
			{
				case PATROL:
					patrol();
				break;
				case HIDE:
					hide();
				break;
			} 
			
		}
	}
	
	private void patrol(){
		// Replace the next 4 lines with any behavior you would like
		ahead(100);
		turnGunRight(90);
		turnRight(90);
		ahead(100);
		turnGunRight(90);
		turnRight(90);
		ahead(100);
		turnRight(90);
		turnGunRight(90);
		ahead(100);
	}
	
	private void hide(){
		ahead(100);
		turnRight(45);
		ahead(100);
		turnRight(45);
		back(100);
		turnRight(45);
		back(100);
	}
	
	private void hunt(){
		
	
	}
	

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		myEnemyBot.update(e,this);	
		System.out.println("myEnemyBot position: "+myEnemyBot.getX()+","+myEnemyBot.getY());
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(20);
	}	
}