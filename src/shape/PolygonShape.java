package shape;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import generic.Pair;

public class PolygonShape implements Shape{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8627655686288274119L;
	private Bound bound;
	private List<Pair> vertices;
	private ShapeType shapeType;
	private BufferedImage image;
	
	public PolygonShape(
			Bound bound,
			List<Pair> vertices,
			ShapeType shapeType) {
		this.bound = bound;
		this.vertices = vertices;
		this.shapeType = shapeType;
	}

	@Override
	public List<Pair> getPoints() {
		return vertices;
	}
	
	@Override
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
		bound.setTopLeftCorner(new Pair(bound.getTopLeftCorner().getX()+dx,bound.getTopLeftCorner().getY()+dy));
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof PolygonShape) {
			PolygonShape polygonShape = (PolygonShape) object;
			return this.shapeType == polygonShape.shapeType &&
				this.bound.equals(polygonShape.bound);
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
		List<Pair> verticesCopy = new LinkedList<>();
		for(Pair pair: vertices) {
			verticesCopy.add(pair.getCopy());
		}
		Shape polygonShape = new PolygonShape(bound.getCopy(), verticesCopy, shapeType);
		BufferedImage copy = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
	    Graphics2D g2D =(Graphics2D) copy.getGraphics();
	    g2D.drawImage(image, 0, 0, null);
	    g2D.dispose();
	    polygonShape.setImage(copy);
		return polygonShape;
	}
}
