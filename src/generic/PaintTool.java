package generic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import field.FieldOperations;
import shape.PolygonShape;
import shape.Shape;
import shape.SimpleShape;
import template.Template;

public class PaintTool {
	private Graphics2D graphics;
	
	public void draw(Shape shape, int size, boolean fill) {
		Color color = graphics.getColor();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, size, size);
		graphics.setColor(color);
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
						Pair topLine = simpleShape.getPoints().get(0),
							  endLine = simpleShape.getPoints().get(1);
						
						graphics.drawLine((int)topLine.getX(), (int) topLine.getY(), 
								(int) endLine.getX(), (int) endLine.getY());
					break;
					case OVAL:
						Pair topOval = simpleShape.getBound().getTopLeftCorner(),
							 sizeOval = simpleShape.getBound().getSize();
						if(fill) {
							graphics.fillOval((int)topOval.getX(), (int)topOval.getY(), 
									(int)sizeOval.getX(),(int)sizeOval.getY());
							
						} else {
							graphics.drawOval((int)topOval.getX(), (int)topOval.getY(), 
									(int)sizeOval.getX(),(int)sizeOval.getY());
						}
					break;
					case RECTANGLE:
						Pair topRectangle = simpleShape.getBound().getTopLeftCorner(),
				 			sizeRectangle = simpleShape.getBound().getSize();
						if(fill) {
							graphics.fillRect((int)topRectangle.getX(), (int)topRectangle.getY(), 
									(int)sizeRectangle.getX(),(int)sizeRectangle.getY());
						} else {
							graphics.drawRect((int)topRectangle.getX(), (int)topRectangle.getY(), 
									(int)sizeRectangle.getX(),(int)sizeRectangle.getY());
						}
					break;
					default:
				}
			}
		}
	}
	
	public void paintPuzzle(Map<Integer, List<Shape>> resultedPuzzle, 
			Map<Integer, List<Shape>> possibleSolutions,
			Template template) {
		graphics = (Graphics2D) template.getImage().getGraphics();
		resultedPuzzle
		.forEach((key,value) -> {
			BufferedImage cell =
					template.prepareCell();
			for(Shape shape: value) {
				cell = FieldOperations.superpose(cell, shape.getImage());
			}
			int i = key/template.getCols(),
				j = key%template.getCols();
			if(i == 2 && j == 2) {
				template.setCorrectSolution(cell);
				BufferedImage questionMark = Utils.read("question_mark.jpg");
				template.superposeAt(i, j, questionMark);
			} else {
				template.superposeAt(i, j, cell);
			}
		});
		List<BufferedImage> possibleSolutionsImages =
				new LinkedList<>();
		possibleSolutions
		.forEach((key, value) -> {
			BufferedImage cell =
					template.prepareCell();
			for(Shape shape: value) {
				cell = FieldOperations.superpose(cell, shape.getImage());
			}
			possibleSolutionsImages.add(cell);
		});
		
		template.setPossbileSolutions(possibleSolutionsImages);
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
