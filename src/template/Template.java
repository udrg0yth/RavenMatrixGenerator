package template;
import java.awt.image.BufferedImage;

public interface Template {
	BufferedImage getImage();
	BufferedImage prepareCell();
	void superposeAt(int i, int j, BufferedImage image);
	int getCellSize();
	int getEcart();
	int getWidth();
	int getHeight();
	int getLines();
	int getCols();
}
