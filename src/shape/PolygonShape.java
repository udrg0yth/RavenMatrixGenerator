package shape;
import java.awt.image.BufferedImage;
import java.util.List;

import generic.Pair;

public class PolygonShape implements Shape{
	private List<Pair> vertices;
	private ShapeType shapeType;
	private BufferedImage image;
	
	public PolygonShape(List<Pair> vertices,
			ShapeType shapeType) {
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
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
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
	public String toString() {
		return "[" + shapeType.name() + "," + vertices + "]";
	}
}
