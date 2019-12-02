package model;

public class Sphere {

	private double radium;
	private double posX;
	private double posY;
	private int wait;
	private char directions;
	private boolean click;

	public Sphere(double radium, double posX, double posY, int wait, boolean click,char directions) {
		this.radium = radium;
		this.posX = posX;
		this.posY = posY;
		this.wait = wait;
		this.click = click;
		this.directions = directions;
	}
	
	public boolean getClick() {
		return click;
	}
	
	public void setClick(boolean newClick) {
		click = newClick;
	}

	public double getRadium() {
		return radium;
	}

	public void setRadium(double radium) {
		this.radium = radium;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public int getWait() {
		return wait;
	}

	public void setWait(int wait) {
		this.wait = wait;
	}

	public char getDirections() {
		return directions;
	}

	public void setDirections(char directions) {
		this.directions = directions;
	}
	
	public String text() {
		String text = radium+","+posX+","+posY+","+wait+","+click+","+directions;
		
		return text;
	}

}
