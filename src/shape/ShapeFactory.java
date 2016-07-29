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
			case OCTAGON:
				int resize = (int)(size.getX()*scaleFactor);
				Bound bound = new Bound(topLeftCorner, new Pair(resize, resize));
				points.add(new Pair(topLeftCorner.getX()+resize/3, topLeftCorner.getY()));
				points.add(new Pair(topLeftCorner.getX()+(resize*2)/3, topLeftCorner.getY()));
				points.add(new Pair(topLeftCorner.getX()+resize, topLeftCorner.getY()+resize/3));
				points.add(new Pair(topLeftCorner.getX()+resize, topLeftCorner.getY()+(resize*2)/3));
				points.add(new Pair(topLeftCorner.getX()+(resize*2)/3, topLeftCorner.getY()+resize));
				points.add(new Pair(topLeftCorner.getX()+resize/3, topLeftCorner.getY()+resize));
				points.add(new Pair(topLeftCorner.getX(), topLeftCorner.getY()+(resize*2)/3));
				points.add(new Pair(topLeftCorner.getX(), topLeftCorner.getY()+resize/3));
				points.add(new Pair(topLeftCorner.getX()+resize/3, topLeftCorner.getY()));
				return new PolygonShape(bound, points, type);
			case HEPTAGON:
				resize = (int)(size.getX()*scaleFactor);
				bound = new Bound(topLeftCorner, new Pair(resize, resize));
				points.add(new Pair(topLeftCorner.getX()+resize/2, topLeftCorner.getY()));
				points.add(new Pair(topLeftCorner.getX()+(resize*0.88), topLeftCorner.getY()+(0.22*resize)));
				points.add(new Pair(topLeftCorner.getX()+resize, topLeftCorner.getY()+(0.60*resize)));
				points.add(new Pair(topLeftCorner.getX()+(resize*0.72), topLeftCorner.getY()+resize));
				points.add(new Pair(topLeftCorner.getX()+(resize*0.28), topLeftCorner.getY()+resize));
				points.add(new Pair(topLeftCorner.getX(), topLeftCorner.getY()+(0.60*resize)));
				points.add(new Pair(topLeftCorner.getX()+(resize*0.12), topLeftCorner.getY()+(0.22*resize)));
				points.add(new Pair(topLeftCorner.getX()+resize/2, topLeftCorner.getY()));
				return new PolygonShape(bound, points, type);
			case HEXAGON:
				resize = (int)(size.getX()*scaleFactor);
				bound = new Bound(topLeftCorner, new Pair(resize, resize));
				points.add(new Pair(topLeftCorner.getX()+resize/2, topLeftCorner.getY()));
				points.add(new Pair(topLeftCorner.getX()+resize, topLeftCorner.getY()+(0.22*resize)));
				points.add(new Pair(topLeftCorner.getX()+resize, topLeftCorner.getY()+(0.78*resize)));
				points.add(new Pair(topLeftCorner.getX()+resize/2, topLeftCorner.getY()+resize));
				points.add(new Pair(topLeftCorner.getX(), topLeftCorner.getY()+(0.78*resize)));
				points.add(new Pair(topLeftCorner.getX(), topLeftCorner.getY()+(0.22*resize)));
				points.add(new Pair(topLeftCorner.getX()+resize/2, topLeftCorner.getY()));
				return new PolygonShape(bound, points, type);
			case PENTAGON:
				resize = (int)(size.getX()*scaleFactor);
				bound = new Bound(topLeftCorner, new Pair(resize, resize));
				points.add(new Pair(topLeftCorner.getX()+resize/2, topLeftCorner.getY()));
				points.add(new Pair(topLeftCorner.getX()+resize, topLeftCorner.getY()+(0.34*resize)));
				points.add(new Pair(topLeftCorner.getX()+(resize*0.78), topLeftCorner.getY()+resize));
				points.add(new Pair(topLeftCorner.getX()+(resize*0.22), topLeftCorner.getY()+resize));
				points.add(new Pair(topLeftCorner.getX(), topLeftCorner.getY()+(0.34*resize)));
				points.add(new Pair(topLeftCorner.getX()+resize/2, topLeftCorner.getY()));
				return new PolygonShape(bound, points, type);
			case PENTAGRAM:
				resize = (int)(size.getX()*scaleFactor);
				bound = new Bound(topLeftCorner, new Pair(resize, resize));
				points.add(new Pair(topLeftCorner.getX()+resize/2, topLeftCorner.getY()));
				points.add(new Pair(topLeftCorner.getX()+(resize*3.2)/4, topLeftCorner.getY()+resize));
				points.add(new Pair(topLeftCorner.getX(), topLeftCorner.getY()+resize/2.5));
				points.add(new Pair(topLeftCorner.getX()+resize, topLeftCorner.getY()+resize/2.5));
				points.add(new Pair(topLeftCorner.getX()+(resize*0.9)/4, topLeftCorner.getY()+resize));
				points.add(new Pair(topLeftCorner.getX()+resize/2, topLeftCorner.getY()));
				return new PolygonShape(bound, points, type);
			case DIAMOND:
				resize = (int)(size.getX()*scaleFactor);
				bound = new Bound(topLeftCorner, new Pair(resize, resize));
				points.add(new Pair(topLeftCorner.getX()+resize/2, topLeftCorner.getY()));
				points.add(new Pair(topLeftCorner.getX()+resize, topLeftCorner.getY()+resize/2));
				points.add(new Pair(topLeftCorner.getX()+resize/2, topLeftCorner.getY()+resize));
				points.add(new Pair(topLeftCorner.getX(), topLeftCorner.getY()+resize/2));
				return new PolygonShape(bound, points, type);
			case BOW:
				resize = (int)(size.getX()*scaleFactor);
				bound = new Bound(topLeftCorner, new Pair(resize, resize));
				points.add(new Pair(topLeftCorner.getX()+resize/2, topLeftCorner.getY()));
				points.add(new Pair(topLeftCorner.getX(), topLeftCorner.getY()+resize/2));
				points.add(new Pair(topLeftCorner.getX()+resize, topLeftCorner.getY()+resize/2));
				points.add(new Pair(topLeftCorner.getX()+resize/2, topLeftCorner.getY()+resize));
				return new PolygonShape(bound, points, type);
			case TRIANGLE:
				resize = (int)(size.getX()*scaleFactor);
				bound = new Bound(topLeftCorner, new Pair(resize, resize));
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
