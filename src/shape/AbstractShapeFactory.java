package shape;

import generic.Pair;

public interface AbstractShapeFactory {
	Shape getShape(ShapeType type, 
			Pair topLeftCorner,
			double scaleFactor);
}
