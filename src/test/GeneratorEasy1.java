package test;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import generic.PaintTool;
import generic.Pair;
import generic.Utils;
import rule.Puzzle;
import shape.AbstractShapeFactory;
import shape.Shape;
import shape.ShapeFactory;
import shape.ShapeType;
import template.StandardTemplate;
import template.Template;

public class GeneratorEasy1 {
	public static final void main(String[] args) {
		final Template template =
				new StandardTemplate(200,30,3,3);
		final PaintTool paintTool =
				new PaintTool();
		
		List<ShapeType> shapeTypes = new ArrayList<>();
		shapeTypes.add(ShapeType.PENTAGRAM);
		shapeTypes.add(ShapeType.OVAL);
		shapeTypes.add(ShapeType.DIAMOND);
		shapeTypes.add(ShapeType.BOW);
		shapeTypes.add(ShapeType.TRIANGLE);
		shapeTypes.add(ShapeType.RECTANGLE);
		shapeTypes.add(ShapeType.PENTAGON);
		shapeTypes.add(ShapeType.HEXAGON);
		shapeTypes.add(ShapeType.HEPTAGON);
		Collections.shuffle(shapeTypes, new Random(System.nanoTime()));
		
		int random = (int)(Math.random() * 9);
		
		paintTool.setColor(Color.RED);
		Puzzle puzzle = 
				new Puzzle(3, 3);
		AbstractShapeFactory shapeFactory =
				new ShapeFactory(new Pair(template.getCellSize(), template.getCellSize()));
		for(int i=0;i<8;i++) {
			puzzle.addInitialShapes(i, Collections.singletonList(shapeFactory.getShape(shapeTypes.get(random), 
					new Pair(20,20), 0.8D)));
		}
		
		Map<Integer, List<Shape>> resultedPuzzle =
				puzzle.runPuzzle(paintTool, template);
		for(int key=0;key<resultedPuzzle.size();key++) {
			int i = key/template.getCols(),
				j = key%template.getCols();
			template.superposeAt(i, j, resultedPuzzle.get(0).get(0).getImage());
		}
		
		BufferedImage questionMark = Utils.read("question_mark.jpg");
		template.superposeAt(2, 2, questionMark);
		
		Utils.save(template.getImage(), "puzzle");
		
/*
		int r = (int)(Math.random() * 9);
		
		BufferedImage image = template.prepareCell();
		paintTool.setGraphics(image.createGraphics());
		paintTool.setColor(Color.BLACK);
		paintTool.draw(shapeFactory.getShape(shapeTypes.get(random), 
					new Pair(20,20), 0.8D), template.getCellSize(), true);
		Utils.save(image, "answer" + (random+1));
		
		for(int i=0;i<6;i++) {
			random = (int)(Math.random() * 9);
			BufferedImage image = template.prepareCell();
			paintTool.setGraphics(image.createGraphics());
			paintTool.setColor(Color.BLACK);
			paintTool.draw(shapeFactory.getShape(shapeTypes.get(i), 
						new Pair(20,20), 0.8D), template.getCellSize(), true);
			Utils.save(image, "answer" + (i+1));
		}*/
	}
}
