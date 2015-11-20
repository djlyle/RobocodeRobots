package sjl;
import robocode.*;
/**
 * MyClass - a class by (your name here)
 */
public class EnemyBot
{
	private String name;
	private double bearing;
	private double distance;
	private double energy;
	private double heading;
	private double velocity;
	
	public EnemyBot()
	{
		reset();
	}
	
	public void reset(){
		name = "";
		bearing = 0;
		distance = 0;
		energy = 0;
		heading = 0;
		velocity = 0;
	}
	
	public boolean none(){
		return name.equals("");
	}
	
	public double getBearing(){
		return bearing;
	}
	
	public double getDistance(){
		return distance;
	}
	
	public double getEnergy(){
		return energy;
	}
	
	public double getHeading(){
		return heading;
	}
	
	public String getName(){
		return name;
	}
	
	public double getVelocity(){
		return velocity;
	}
	
	public void update(ScannedRobotEvent e){
		name = e.getName();
		bearing = e.getBearing();
		heading = e.getHeading();
		distance = e.getDistance();
		energy = e.getEnergy();
		velocity = e.getVelocity();
	}
	
}
