package template;
import java.awt.image.BufferedImage;
import java.util.List;

public interface Template {
	BufferedImage getImage();
	BufferedImage getCorrectSolution();
	void setCorrectSolution(BufferedImage correctSolution);
	List<BufferedImage> getPossibleSolutions();
	void setPossbileSolutions(List<BufferedImage> possibleSolutions);
	BufferedImage prepareCell();
	void superposeAt(int i, int j, BufferedImage image);
	int getCellSize();
	int getEcart();
	int getWidth();
	int getHeight();
	int getLines();
	int getCols();
}
