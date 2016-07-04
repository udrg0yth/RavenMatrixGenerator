package generic;

import java.io.Serializable;

public class Pair implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2002999150740304110L;
	public static final double EPSILON = Math.pow(10, -6);
	private double x,y;
	
	public Pair(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	public Pair getCopy() {
		return new Pair(x,y);
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof Pair) {
			Pair pair = (Pair) object;
			return Math.abs(x-pair.getX()) < EPSILON
				&& Math.abs(y-pair.getY()) < EPSILON;
		} 
		return false;
	}
	
	@Override
	public int hashCode() {
		return Double.hashCode(x) * 31
			+ Double.hashCode(y);
	}
	
	@Override
	public String toString() {
		return "{" + x + "," + y + "}";
	}
}
