package test;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
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
import shape.Bound;
import shape.Shape;
import shape.ShapeFactory;
import shape.ShapeType;
import shape.SimpleShape;
import template.StandardTemplate;
import template.Template;

public class Generator {
	private static final Pair randomPair(int cellSize) {
		Random random = new Random();
		int x=random.nextInt(5)*(cellSize/5),
			y=random.nextInt(5)*(cellSize/5);
		return new Pair(x,y);
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		for(int folderIndex = 0;folderIndex<50;folderIndex++) {
		final Template template =
				new StandardTemplate(200,30,3,3);
		final PaintTool paintTool =
				new PaintTool();
		final RuleGenerator ruleGenerator = 
				new RuleGenerator(1,2);
		ruleGenerator.addTransformationAt(new int[]{9, 10, 11}, (shape) -> {
			if(shape == null) {
				throw new NullPointerException("Shape is null!");
			}
			BufferedImage transformed = null;
			if(shape.getBound().getTopLeftCorner().getX() + 0.2D*template.getCellSize()>=template.getCellSize()) {
				System.out.println("outsideX " + shape.getBound().getTopLeftCorner().getX() + " " + shape.getBound().getTopLeftCorner().getY());
				transformed =
						FieldOperations.translate(shape, -0.8D, 0.0, template.getCellSize());
			} else {
				transformed = 
					FieldOperations.translate(shape, 0.2D, 0.0D, template.getCellSize());
			}
			Shape transformedShape = shape.getCopy();
			transformedShape.setImage(transformed);
			return transformedShape;
		});
		ruleGenerator.addTransformationAt(new int[]{9, 10, 11}, (shape) -> {
			if(shape == null) {
				throw new NullPointerException("Shape is null!");
			}
			BufferedImage transformed = null;
			if(shape.getBound().getTopLeftCorner().getY() + 0.2D*template.getCellSize()>=template.getCellSize()) {
				System.out.println("outsideY " + shape.getBound().getTopLeftCorner().getY() + 0.2D*template.getCellSize());
				transformed =
						FieldOperations.translate(shape, 0.0D, -0.8D, template.getCellSize());
			} else {
				transformed = 
						FieldOperations.translate(shape, 0.0D, 0.2D, template.getCellSize());
			}
			Shape transformedShape = shape.getCopy();
			transformedShape.setImage(transformed);
			return transformedShape;
		}); 
		ruleGenerator.setPickingProbability(0, 0, 0.5);
		ruleGenerator.setPickingProbability(0, 1, 0.5);
		AbstractShapeFactory shapeFactory =
				new ShapeFactory(new Pair(template.getCellSize(), template.getCellSize()));
		List<Shape> initialShapes =
				new LinkedList<>();
		for(int i=1;i<=4;i++) {
			Shape shape = new SimpleShape(new Bound(new Pair(i*(template.getCellSize()/5),0),
					  new Pair(0, template.getCellSize())),
					  ShapeType.LINE);
			initialShapes.add(shape);
		}
		for(int i=1;i<=4;i++) {
			Shape shape = new SimpleShape(new Bound(new Pair(0,i*(template.getCellSize()/5)),
					  new Pair(template.getCellSize(),0)),
					  ShapeType.LINE);
			initialShapes.add(shape);
		}
		
		Shape firstSquare = shapeFactory.getShape(ShapeType.RECTANGLE, randomPair(template.getCellSize()), 0.2d),
			  secondSquare = shapeFactory.getShape(ShapeType.RECTANGLE, randomPair(template.getCellSize()), 0.2d),
			  thirdSquare = shapeFactory.getShape(ShapeType.RECTANGLE, randomPair(template.getCellSize()), 0.2d);
		initialShapes.add(firstSquare);
		initialShapes.add(secondSquare);
		initialShapes.add(thirdSquare);
		
		List<Shape> initialShapes2 =
				new LinkedList<>();
		for(int i=1;i<=4;i++) {
			Shape shape = new SimpleShape(new Bound(new Pair(i*(template.getCellSize()/5),0),
					  new Pair(0, template.getCellSize())),
					  ShapeType.LINE);
			initialShapes2.add(shape);
		}
		for(int i=1;i<=4;i++) {
			Shape shape = new SimpleShape(new Bound(new Pair(0,i*(template.getCellSize()/5)),
					  new Pair(template.getCellSize(),0)),
					  ShapeType.LINE);
			initialShapes2.add(shape);
		}
		
		Shape firstSquare2 = shapeFactory.getShape(ShapeType.RECTANGLE, randomPair(template.getCellSize()), 0.2d),
			  secondSquare2 = shapeFactory.getShape(ShapeType.RECTANGLE, randomPair(template.getCellSize()), 0.2d),
			  thirdSquare2 = shapeFactory.getShape(ShapeType.RECTANGLE, randomPair(template.getCellSize()), 0.2d);
		initialShapes2.add(firstSquare2);
		initialShapes2.add(secondSquare2);
		initialShapes2.add(thirdSquare2);
		
		List<Shape> initialShapes3 =
				new LinkedList<>();
		for(int i=1;i<=4;i++) {
			Shape shape = new SimpleShape(new Bound(new Pair(i*(template.getCellSize()/5),0),
					  new Pair(0, template.getCellSize())),
					  ShapeType.LINE);
			initialShapes3.add(shape);
		}
		for(int i=1;i<=4;i++) {
			Shape shape = new SimpleShape(new Bound(new Pair(0,i*(template.getCellSize()/5)),
					  new Pair(template.getCellSize(),0)),
					  ShapeType.LINE);
			initialShapes3.add(shape);
		}
		
		Shape firstSquare3 = shapeFactory.getShape(ShapeType.RECTANGLE, randomPair(template.getCellSize()), 0.2d),
			  secondSquare3 = shapeFactory.getShape(ShapeType.RECTANGLE, randomPair(template.getCellSize()), 0.2d),
			  thirdSquare3 = shapeFactory.getShape(ShapeType.RECTANGLE, randomPair(template.getCellSize()), 0.2d);
		initialShapes3.add(firstSquare3);
		initialShapes3.add(secondSquare3);
		initialShapes3.add(thirdSquare3);
		
		Rule firstRule = 
				ruleGenerator.generate(2, new int[]{9,10,11});/*
		Rule secondRule =
				ruleGenerator.generate(1, new int[]{9,10});*/
		Puzzle puzzle = 
				new Puzzle(3, 3);
		puzzle.addInitialShapes(0, initialShapes);
		puzzle.addInitialShapes(3, initialShapes2);
		puzzle.addInitialShapes(6, initialShapes3);
		puzzle.addRule(0, 1, firstRule);
		puzzle.addRule(1, 2, firstRule);
		puzzle.addRule(3, 4, firstRule);
		puzzle.addRule(4, 5, firstRule);
		puzzle.addRule(6, 7, firstRule);
		puzzle.addRule(7, 8, firstRule);
		Map<Integer, List<Shape>> resultedPuzzle =
				puzzle.runPuzzle(paintTool, template);
		
		Map<Integer, List<Shape>> possibleSolutions = 
				puzzle.generatePossibleSolutions(resultedPuzzle.get(7), 
						resultedPuzzle.get(8), 3, ruleGenerator);
		
		paintTool.paintPuzzle(resultedPuzzle, possibleSolutions, template);
		
		Utils.createDirectory("puzzle" + folderIndex);
		Utils.save(template.getImage(), "puzzle" + folderIndex + "/puzzle");
		Utils.save(template.getCorrectSolution(), "puzzle" + folderIndex + "/0");
		for(int i = 0; i<template.getPossibleSolutions().size();i++) {
			Utils.save(template.getPossibleSolutions().get(i), "puzzle" + folderIndex + "/" + (i+1));
		}
		}
	}
}
