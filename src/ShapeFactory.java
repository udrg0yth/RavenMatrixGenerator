import java.util.LinkedList;
import java.util.List;

public class ShapeFactory implements AbstractShapeFactory{
	private int size;
	
	public ShapeFactory(int size) {
		this.size = size;
	}
	
	@Override
	public Shape getShape(ShapeType type, 
							 Pair topLeftCorner,
							 double scaleFactor) {
		List<Pair> points = 
				new LinkedList<>();
		switch(type) {
			case TRIANGLE:
				int resize = (int)(size*scaleFactor);
				points.add(new Pair(topLeftCorner.getX()+resize/2, topLeftCorner.getY()));
				points.add(new Pair(topLeftCorner.getX(), topLeftCorner.getY()+resize*2/3));
				points.add(new Pair(topLeftCorner.getX()+resize, topLeftCorner.getY()+resize*2/3));
				return new PolygonShape(points, type);
			default:
				return new SimpleShape(topLeftCorner, new Pair(size*scaleFactor,
						  size*scaleFactor), type);
				
		}
	}

}
