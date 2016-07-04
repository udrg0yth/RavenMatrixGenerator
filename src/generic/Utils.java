package generic;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

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
	
	public static final BufferedImage read(String path) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(path));
		} catch (IOException e) {
		}
		return img;
	}
	
	public static final void createDirectory(String dirName) {
		File file = new File(dirName);
		file.mkdir();
	}
	
	public static final void save(BufferedImage image, String name) {
		try {
		    File outputfile = new File(name + ".jpg");
		    ImageIO.write(image, "jpg", outputfile);
		} catch (IOException e) {
		}
	}
}
