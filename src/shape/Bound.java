package shape;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import generic.Pair;

public final class Bound implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1599968670744773680L;
	private Pair topLeftCorner;
	private Pair size;
	private List<Pair> corners;
	
	public Bound(Pair topLeftCorner, Pair size) {
		this.topLeftCorner = topLeftCorner;
		this.size = size;
		this.corners = computeCorners();
	}
	
	private List<Pair> computeCorners() {
		List<Pair> corners = new LinkedList<>();
		corners.add(topLeftCorner);
		corners.add(new Pair(topLeftCorner.getX() + size.getX(), topLeftCorner.getY()));
		corners.add(new Pair(topLeftCorner.getX() + size.getX(), topLeftCorner.getY() + size.getY()));
		corners.add(new Pair(topLeftCorner.getX(), topLeftCorner.getY() + size.getY()));
		return corners;
	}
	
	public List<Pair> getCorners() {
		return corners;
	}
	
	public void setTopLeftCorner(Pair topLeftCorner) {
		this.topLeftCorner = topLeftCorner;
	}
	
	public void setSize(Pair size) {
		this.size = size;
	}
	
	public Pair getTopLeftCorner() {
		return topLeftCorner;
	}
	
	public Pair getSize() {
		return size;
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof Bound) {
			Bound bound = (Bound) object;
			return size.equals(bound.getSize())
				&& topLeftCorner.equals(bound.getTopLeftCorner());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return size.hashCode() * 31 +
			   topLeftCorner.hashCode();
	}
	
	public Bound getCopy() {
		return new Bound(topLeftCorner.getCopy(),size.getCopy());
	}
}
