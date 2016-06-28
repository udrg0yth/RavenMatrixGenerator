import java.awt.Polygon;
import java.util.List;

public class Utils {
	private Utils(){};
	public static final Polygon getPolygon(List<Pair> pairs) {
		Polygon polygon = new Polygon();
		int []x = new int[pairs.size()];
		int []y = new int[pairs.size()];
		int i = 0;
		for(Pair pair: pairs) {
			x[i] = (int)pair.getX();
			y[i] = (int)pair.getY();
			++i;
		}
		polygon.xpoints = x;
		polygon.ypoints = y;
		polygon.npoints = pairs.size();
		return polygon;
	}
}
