package test;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import field.FieldOperations;
import generic.PaintTool;
import generic.Pair;
import generic.Utils;
import rule.Puzzle;
import rule.Rule;
import rule.RuleGenerator;
import shape.AbstractShapeFactory;
import shape.Shape;
import shape.ShapeFactory;
import shape.ShapeType;
import template.StandardTemplate;
import template.Template;

public class GeneratorEasy2 {
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
		
		final RuleGenerator ruleGenerator = 
				new RuleGenerator(1,1);
		ruleGenerator.addTransformationAt(new int[]{1}, (shape) -> {
			if(shape == null) {
				throw new NullPointerException("Shape is null!");
			}
			BufferedImage transformed = null;
			transformed = FieldOperations.rotate(shape.getImage(), 45);
			System.out.println(transformed);
			Shape transformedShape = shape.getCopy();
			transformedShape.setImage(transformed);
			return transformedShape;
		});
		
		ruleGenerator.setPickingProbability(0, 0, 1);
		AbstractShapeFactory shapeFactory =
				new ShapeFactory(new Pair(template.getCellSize(), template.getCellSize()));
		Rule firstRule = 
				ruleGenerator.generate(1, new int[]{1});
		Random random = new Random();
		Puzzle puzzle = 
				new Puzzle(3, 3);
		puzzle.addInitialShapes(0,  Collections.singletonList(shapeFactory.getShape(shapeTypes.get(random.nextInt(6)), 
				new Pair(40,40), 0.6D)));
		puzzle.addInitialShapes(3,  Collections.singletonList(shapeFactory.getShape(shapeTypes.get(random.nextInt(6)), 
				new Pair(40,40), 0.6D)));
		puzzle.addInitialShapes(6,  Collections.singletonList(shapeFactory.getShape(shapeTypes.get(random.nextInt(6)), 
				new Pair(40,40), 0.6D)));
		puzzle.addRule(0, 1, firstRule);
		puzzle.addRule(1, 2, firstRule);
		puzzle.addRule(3, 4, firstRule);
		puzzle.addRule(4, 5, firstRule);
		puzzle.addRule(6, 7, firstRule);
		puzzle.addRule(7, 8, firstRule);
		
		Map<Integer, List<Shape>> resultedPuzzle =
				puzzle.runPuzzle(paintTool, template);
		
		for(int key=0;key<resultedPuzzle.size();key++) {
			int i = key/template.getCols(),
				j = key%template.getCols();
			template.superposeAt(i, j, resultedPuzzle.get(key).get(0).getImage());
		}
		
		BufferedImage questionMark = Utils.read("question_mark.jpg");
		template.superposeAt(2, 2, questionMark);
		
		Utils.save(template.getImage(), "puzzle");
	}
}
