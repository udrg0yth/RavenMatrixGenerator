package field;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import shape.Shape;

public class FieldOperations {
	private FieldOperations(){}
	public static final BufferedImage superpose(BufferedImage imageUnder, BufferedImage imageAbove) {
		int resultMatrix[][] = new int[imageUnder.getWidth()][imageUnder.getWidth()];

		for(int i = 0; i < imageUnder.getWidth(); i++)
		    for(int j = 0; j < imageUnder.getHeight(); j++) {
		        if(imageAbove.getRGB(i, j) == -1) {
		        	resultMatrix[i][j] = imageUnder.getRGB(i, j);
		        } else {
		        	resultMatrix[i][j] = imageAbove.getRGB(i, j);
		        }
		    }
		BufferedImage image = new BufferedImage(imageUnder.getWidth(), imageUnder.getWidth(),
                BufferedImage.TYPE_INT_RGB);
		
		for (int i = 0; i < imageUnder.getWidth(); i++) {
	        for (int j = 0; j < imageUnder.getWidth(); j++) {
	        	image.setRGB(i, j, resultMatrix[i][j]);
	        }
	    }
		
		return image;
	}
	
	public static final BufferedImage rotate(BufferedImage image, double degrees) {
		BufferedImage rot = new BufferedImage(image.getHeight(), image.getWidth(),
				BufferedImage.TYPE_INT_RGB );

		AffineTransform xform = new AffineTransform();
		xform.rotate(Math.toRadians(degrees), image.getHeight()/2, image.getWidth()/2);
	    Graphics2D g = (Graphics2D) rot.createGraphics();
	    g.setColor(Color.WHITE);
		g.fillRect(0, 0, rot.getWidth(), rot.getHeight());
	    g.drawImage(image, xform, null);
	    g.dispose();
	    return rot;
	}
	
	public static final BufferedImage translate(Shape shape, double dx, double dy, int cellSize) {
		BufferedImage image = shape.getImage();
		BufferedImage trans = new BufferedImage(image.getHeight(), image.getWidth(),
				BufferedImage.TYPE_INT_RGB );
		
		AffineTransform xform = new AffineTransform();
		xform.setToIdentity();
		System.out.println(dx+ " " + dy + " " + dx*cellSize + " " +dy*cellSize);
		xform.translate(dx*cellSize, dy*cellSize);
		shape.translate(dx*cellSize, dy*cellSize);
	    Graphics2D g = (Graphics2D) trans.createGraphics();
	    g.setColor(Color.WHITE);
		g.fillRect(0, 0, trans.getWidth(), trans.getHeight());
	    g.drawImage(image, xform, null);
	    g.dispose();
	    return trans;
	}
}
 