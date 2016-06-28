package shape;
import java.awt.image.BufferedImage;
import java.util.List;

import generic.Pair;

public interface Shape extends Cloneable{
	List<Pair> getPoints();
	ShapeType getShapeType();
	Object clone() throws CloneNotSupportedException;
	void setImage(BufferedImage image);
	BufferedImage getImage();
}
