import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class Generator {
	private static final Pair randomPair() {
		int x = (int)Math.random()*5,
			y = (int)Math.random()*5;
		return new Pair(x,y);
	}
	public static void main(String[] args) {
		final Template template =
				new StandardTemplate(600);
		final PaintTool paintTool =
				new PaintTool();
		final RuleGenerator ruleGenerator = 
				new RuleGenerator(1,2);
		ruleGenerator.addTransformationAt(new int[]{9, 10}, (shape) -> {
			BufferedImage image = shape.getImage();
			if(image == null) {
				image =
						template.prepareCell();
				paintTool.setGraphics((Graphics2D)image.getGraphics());
				paintTool.draw(shape, true);
				shape.setImage(image);
			}
			FieldOperations.translate(image, 0.2D, 0.0D, template.getCellSize());
			return shape;
		});
		ruleGenerator.addTransformationAt(new int[]{9, 10}, (shape) -> {
			BufferedImage image = shape.getImage();
			if(image == null) {
				image =
						template.prepareCell();
				paintTool.setGraphics((Graphics2D)image.getGraphics());
				paintTool.draw(shape, true);
				shape.setImage(image);
			}
			FieldOperations.translate(image, 0.0D, 0.2D, template.getCellSize());
			return shape;
		}); 
		ruleGenerator.setPickingProbability(0, 0, 0.5);
		ruleGenerator.setPickingProbability(0, 1, 0.5);
		AbstractShapeFactory shapeFactory =
				new ShapeFactory(template.getCellSize());
		List<Shape> initialShapes =
				new LinkedList<>();
		for(int i=1;i<=4;i++) {
			Shape shape = new SimpleShape(new Pair(i*(template.getCellSize()/5),0),
					  new Pair(i*(template.getCellSize()/5), template.getCellSize()),
					  null,
					  ShapeType.LINE);
			initialShapes.add(shape);
		}
		for(int i=1;i<=4;i++) {
			Shape shape = new SimpleShape(new Pair(0,i*(template.getCellSize()/5)),
					  new Pair(template.getCellSize(),i*(template.getCellSize()/5)),
					  null,
					  ShapeType.LINE);
			initialShapes.add(shape);
		}
		
		Shape firstSquare = shapeFactory.getShape(ShapeType.RECTANGLE, randomPair(), 0.2d),
			  secondSquare = shapeFactory.getShape(ShapeType.RECTANGLE, randomPair(), 0.2d);
		
		Rule firstRule = 
				ruleGenerator.generate(2, new int[]{9,10});
		Rule secondRule =
				ruleGenerator.generate(2, new int[]{9,10});
		Puzzle puzzle = 
				new Puzzle(1, 3);
		puzzle.addInitialShapes(0, initialShapes);
		puzzle.addRule(0, 1, firstRule);
		puzzle.addRule(1, 2, secondRule);
		Map<Integer, List<Shape>> resultedPuzzle =
				puzzle.runPuzzle();
		/*Template template = new StandardTemplate(600);
		ShapeFactory factory = new ShapeFactory(template.getCellSize());
		FieldOperations fieldOperations = new FieldOperations();
		
		BufferedImage cell = template.prepareCell();
		
		Graphics2D g2 = cell.createGraphics();
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, cell.getWidth(), cell.getHeight());
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(10));
		g2.drawLine(50, 50, 100, 100);
		
		BufferedImage oval = template.prepareCell();
		g2 = oval.createGraphics();
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, oval.getWidth(), oval.getHeight());
		g2.setColor(Color.BLACK);
		List<Pair> pairs = factory.getVertices(ShapeType.CIRCLE, new Pair(0,0), 0.6);
		//g2.drawPolygon(Utils.getPolygon(pairs));
		g2.drawOval((int)pairs.get(0).getX(), (int)pairs.get(0).getY(), (int)pairs.get(1).getX(), (int)pairs.get(1).getY());
		
	    
	    BufferedImage rot = fieldOperations.rotate(cell, -45);
	    
	    BufferedImage oval2 = template.prepareCell();
		g2 = oval2.createGraphics();
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, oval2.getWidth(), oval2.getHeight());
		g2.setColor(Color.BLACK);
		g2.drawOval(120, 15, 30, 30);
		
	    BufferedImage rot2 = fieldOperations.rotate(rot, -45);
	    
	    BufferedImage oval3 = template.prepareCell();
		g2 = oval3.createGraphics();
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, oval3.getWidth(), oval3.getHeight());
		g2.setColor(Color.BLACK);
		g2.drawOval(120, 120, 30, 30);
	    
	    template.superposeAt(0, 0, fieldOperations.superpose(cell, oval));
		template.superposeAt(0, 1, fieldOperations.superpose(rot, oval2));
		template.superposeAt(0, 2, fieldOperations.superpose(rot2, oval3));
		*/
		BufferedImage image = template.getImage();
		
		try {
		    File outputfile = new File("saved.jpg");
		    ImageIO.write(image, "jpg", outputfile);
		} catch (IOException e) {
		}
		
	}
}
