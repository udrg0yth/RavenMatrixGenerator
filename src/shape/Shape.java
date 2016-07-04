package shape;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.List;

import generic.Pair;

public interface Shape extends Serializable{
	List<Pair> getPoints();
	ShapeType getShapeType();
	Bound getBound();
	Shape getCopy();
	void translate(double dx, double dy);
	void setImage(BufferedImage image);
	BufferedImage getImage();
}
