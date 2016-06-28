package generic;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import field.FieldOperations;
import shape.PolygonShape;
import shape.Shape;
import shape.SimpleShape;
import template.Template;

public class PaintTool {
	private Graphics2D graphics;
	
	public void draw(Shape shape, boolean fill) {
		if(graphics != null) {
			if(shape instanceof PolygonShape) {
				List<Pair> points =
						((PolygonShape) shape).getPoints();
				int x[] = new int[points.size()];
				int y[] = new int[points.size()];
				for(int i=0;i<points.size();i++) {
					x[i] = (int)points.get(i).getX();
					y[i] = (int)points.get(i).getY();
				}
				if(fill) {
					graphics.fillPolygon(x, y, x.length);
				} else {
					graphics.drawPolygon(x, y, x.length);
				}
			} else {
				SimpleShape simpleShape = (SimpleShape) shape;
				switch(shape.getShapeType()) {
					case LINE:
						Pair topLine = simpleShape.getTopLeftCorner(),
							  endLine = simpleShape.getEndCorner();
						
						graphics.drawLine((int)topLine.getX(), (int) topLine.getY(), 
								(int) endLine.getX(), (int) endLine.getY());
					break;
					case OVAL:
						Pair topOval = simpleShape.getTopLeftCorner(),
							 sizeOval = simpleShape.getSize();
						if(fill) {
							graphics.fillOval((int)topOval.getX(), (int)topOval.getY(), 
									(int)sizeOval.getX(),(int)sizeOval.getY());
							
						} else {
							graphics.drawOval((int)topOval.getX(), (int)topOval.getY(), 
									(int)sizeOval.getX(),(int)sizeOval.getY());
						}
					break;
					case RECTANGLE:
						Pair topRectangle = simpleShape.getTopLeftCorner(),
					 		sizeRectangle = simpleShape.getSize();
						if(fill) {
							graphics.fillOval((int)topRectangle.getX(), (int)topRectangle.getY(), 
									(int)sizeRectangle.getX(),(int)sizeRectangle.getY());
						} else {
							graphics.drawOval((int)topRectangle.getX(), (int)topRectangle.getY(), 
									(int)sizeRectangle.getX(),(int)sizeRectangle.getY());
						}
					break;
					default:
				}
			}
		}
	}
	
	public void paintPuzzle(Map<Integer, List<Shape>> resultedPuzzle, Template template) {
		graphics = (Graphics2D) template.getImage().getGraphics();
		resultedPuzzle
		.forEach((key,value) -> {
			BufferedImage cell =
					template.prepareCell();
			for(Shape shape: value) {
				cell = FieldOperations.superpose(cell, shape.getImage());
			}
			template.superposeAt(key/template.getLines(), key%template.getCols(), cell);
		});
	}
	
	public void setBrushSize(float size) {
		if(graphics != null) {
			graphics.setStroke(new BasicStroke(size));
		}
	}
	
	public void setColor(Color color) {
		if(graphics != null) {
			graphics.setColor(color);
		}
	}
	
	public void setGraphics(Graphics2D graphics) {
		this.graphics = graphics;
	}
}
