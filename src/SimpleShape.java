import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class SimpleShape implements Shape{
	private Pair topLeftCorner;
	private Pair endCorner;
	private Pair size;
	private ShapeType shapeType;
	private BufferedImage image;
	
	public SimpleShape(Pair topLeftCorner,
			Pair size,
			ShapeType shapeType) {
		this.topLeftCorner = topLeftCorner;
		this.shapeType = shapeType;
	}
	
	public SimpleShape(Pair topLeftCorner,
			Pair endCorner,
			Pair size,
			ShapeType shapeType) {
		this(topLeftCorner, size, shapeType);
		this.size = size;
		this.endCorner = endCorner;
	}

	public List<Pair> getPoints() {
		List<Pair> list =
				new LinkedList<>();
		list.add(topLeftCorner);
		if(endCorner != null)
			list.add(endCorner);
		if(size != null)
			list.add(size);
		return list;
	}
	
	public ShapeType getShapeType() {
		return shapeType;
	}

	public Pair getTopLeftCorner() {
		return topLeftCorner;
	}

	public Pair getEndCorner() {
		return endCorner;
	}
	
	public Pair getSize() {
		return size;
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
		return "[" + shapeType.name() + ","
			+  topLeftCorner.toString() + ","
			+  endCorner!=null?endCorner.toString():"" + ","
			+
	}

}
