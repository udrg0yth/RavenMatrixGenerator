package template;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

public class StandardTemplate implements Template{
	private final int width;
	private final int height;
	private final int ecart;
	private final int cellSize;
	private final int lines;
	private final int cols;
	private final BufferedImage template;
	private BufferedImage correctSolution;
	private List<BufferedImage> possibleSolutions;
	
	public StandardTemplate(int cellSize, int ecart, int lines, int cols) {
		this.cellSize = cellSize;
		this.ecart = ecart;
		this.lines = lines;
		this.cols = cols;
		width = cols * cellSize + (cols+1)*ecart;
		height = lines * cellSize + (lines+1)*ecart;
		template =
				  new BufferedImage(width, height,
				                    BufferedImage.TYPE_INT_RGB );
		
		Graphics2D g2 = template.createGraphics();
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, width, height);
		g2.setColor(Color.BLACK);
		
		for(int i=0;i<lines;i++) {
			for(int j=0;j<cols;j++) {
				int x = ecart * (j+1) + j*cellSize,
					y = ecart * (i+1) + i*cellSize;
				g2.drawRect(x, y, cellSize, cellSize);
			}
		}
		
	}
	
	@Override
	public void superposeAt(int i, int j, BufferedImage image) {
		int x = ecart * (j+1) + j*cellSize,
			y = ecart * (i+1) + i*cellSize;
		
		for(int iC=1;iC<cellSize-1;iC++) {
			for(int jC=1;jC<cellSize-1;jC++) {
				template.setRGB(x+jC, y+iC, image.getRGB(jC, iC));
			}
		}
	}
	
	@Override
	public BufferedImage prepareCell() {
		BufferedImage image = new BufferedImage(cellSize, cellSize,
                BufferedImage.TYPE_INT_RGB );
		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, cellSize, cellSize);
		graphics.setColor(Color.BLACK);
		return image;
	}
	
	@Override
	public BufferedImage getImage() {
		return template;
	}
	
	@Override
	public int getCellSize() {
		return cellSize;
	}

	@Override
	public int getEcart() {
		return ecart;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getLines() {
		return lines;
	}

	@Override
	public int getCols() {
		return cols;
	}

	@Override
	public BufferedImage getCorrectSolution() {
		return correctSolution;
	}

	@Override
	public void setCorrectSolution(BufferedImage correctSolution) {
		this.correctSolution = correctSolution;
	}

	@Override
	public List<BufferedImage> getPossibleSolutions() {
		return possibleSolutions;
	}

	@Override
	public void setPossbileSolutions(List<BufferedImage> possibleSolutions) {
		this.possibleSolutions = possibleSolutions;
	}

}
