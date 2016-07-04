package shape;
import java.util.LinkedList;
import java.util.List;

import generic.Pair;

public class ShapeFactory implements AbstractShapeFactory{
	private Pair size;
	
	public ShapeFactory(Pair size) {
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
				int resize = (int)(size.getX()*scaleFactor);
				Bound bound = new Bound(topLeftCorner, new Pair(resize, resize));
				points.add(new Pair(topLeftCorner.getX()+resize/2, topLeftCorner.getY()));
				points.add(new Pair(topLeftCorner.getX(), topLeftCorner.getY()+resize*2/3));
				points.add(new Pair(topLeftCorner.getX()+resize, topLeftCorner.getY()+resize*2/3));
				return new PolygonShape(bound, points, type);
			default:
				resize = (int)(size.getX()*scaleFactor);
				bound = new Bound(topLeftCorner, new Pair(resize, resize));
				return new SimpleShape(bound, type);
				
		}
	}

}
