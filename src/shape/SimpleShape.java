package shape;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import generic.Pair;

public class SimpleShape implements Shape{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3342844952929384744L;
	private Bound bound;
	private ShapeType shapeType;
	private BufferedImage image;
	
	public SimpleShape(Bound bound,
			ShapeType shapeType) {
		this.bound = bound;
		this.shapeType = shapeType;
	}

	public List<Pair> getPoints() {
		List<Pair> list =
				new LinkedList<>();
		list.add(bound.getTopLeftCorner());
		list.add(new Pair(bound.getTopLeftCorner().getX() +
				bound.getSize().getX(), bound.getTopLeftCorner().getY() + 
				bound.getSize().getY()));
		return list;
	}
	
	public ShapeType getShapeType() {
		return shapeType;
	}

	@Override
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

	@Override
	public Bound getBound() {
		return bound;
	}

	@Override
	public void translate(double dx, double dy) {
		bound.setTopLeftCorner(new Pair(bound.getTopLeftCorner().getX() + dx, bound.getTopLeftCorner().getY() + dy));
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof SimpleShape) {
			SimpleShape simpleShape = (SimpleShape) object;
			return this.shapeType == simpleShape.shapeType &&
				this.bound.equals(simpleShape.bound);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return shapeType.ordinal() * 31
			+ bound.hashCode();
	}

	@Override
	public Shape getCopy() {
		Shape simpleShape = new SimpleShape(bound.getCopy(), shapeType);
		BufferedImage copy = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
	    Graphics2D g2D =(Graphics2D) copy.getGraphics();
	    g2D.drawImage(image, 0, 0, null);
	    g2D.dispose();
		simpleShape.setImage(copy);
		return simpleShape;
	}

}
