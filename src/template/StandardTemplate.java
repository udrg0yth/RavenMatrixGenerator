package template;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class StandardTemplate implements Template{
	private final int size;
	private final int ecart;
	private final int cellSize;
	private final BufferedImage template;
	
	public StandardTemplate(int size) {
		this.size = size;
		ecart = (int) (0.15*size/4);
		cellSize = (int)(0.85*size/3);
		template =
				  new BufferedImage(size, size,
				                    BufferedImage.TYPE_INT_RGB );
		
		Graphics2D g2 = template.createGraphics();
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, 600, 600);
		g2.setColor(Color.BLACK);
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
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
				template.setRGB(x+jC, y+iC, image.getRGB(iC, jC));
			}
		}
	}
	
	@Override
	public BufferedImage prepareCell() {
		return new BufferedImage(cellSize, cellSize,
                BufferedImage.TYPE_INT_RGB );
	}
	
	@Override
	public BufferedImage getImage() {
		return template;
	}
	
	@Override
	public int getCellSize() {
		return cellSize;
	}

}
