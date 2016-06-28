package field;

import shape.Shape;

@FunctionalInterface
public interface Transformation {
	Shape apply(Shape shape);
}
