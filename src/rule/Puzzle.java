package rule;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import field.Transformation;
import generic.PaintTool;
import shape.Shape;
import template.Template;

public class Puzzle {
	private int lines;
	private int cols;
	private Map<Integer,List<Shape>> initialShapes;
	private Rule[][] rules;
	
	public Puzzle(int lines, int cols) {
		this.lines = lines;
		this.cols = cols;
		initialShapes = new HashMap<>();
		rules = new Rule[lines*cols][lines*cols];
	}
	
	public void addInitialShape(int cellIndex, Shape shape) {
		if(cellIndex < 0
		|| cellIndex < lines * cols) {
			throw new IndexOutOfBoundsException("Size is " + lines + "x" + cols 
					+ " but index was " + cellIndex);
		}
		List<Shape> shapes = 
				initialShapes.get(cellIndex);
		if(shapes != null) {
			shapes.add(shape);
		} else {
			shapes = new LinkedList<>();
			shapes.add(shape);
			initialShapes.put(cellIndex, shapes);
		}
	}
	
	public void addInitialShapes(int cellIndex, List<Shape> shapes) {
		initialShapes.put(cellIndex, shapes);
	}
	
	public void addRule(int from, int to, Rule rule) {
		rules[from][to] = rule;
	}
	
	public Map<Integer, List<Shape>> generatePossibleSolutions(List<Shape> initial, 
			List<Shape> solution, 
			int depth,
			RuleGenerator generator){
		Random random = new Random();
		Map<Integer, List<Shape>> possibleSolutions =
				new HashMap<>();
		for(int i=0;i<5;i++) {
			boolean found = false;
			while(!found) {
				Rule rule = generator.generate(random.nextInt(depth), new int[] {9,10});
				List<Shape> transformedShapes = new LinkedList<>();
				for(int j=0;j<initial.size();j++) {
					List<Transformation> transformations = rule.getTransformationsAt(j+1);
					if(transformations == null) {
						transformedShapes.add(initial.get(j));
					} else {
						Shape initialShape = initial.get(j);
						Shape cloneShape = initialShape.getCopy();
						for(int transIndex = 0;transIndex<transformations.size();transIndex++) {
							cloneShape = transformations.get(transIndex).apply(cloneShape);
						}
						transformedShapes.add(cloneShape);
					}
				}
				
				found = !checkEquality(solution, transformedShapes) &&
						!checkEquality(possibleSolutions, transformedShapes);
				if(found) {
					possibleSolutions.put(i, transformedShapes);
				}
			}
		}
		return possibleSolutions;
	}
	
	private boolean checkEquality(Map<Integer, List<Shape>> possible, List<Shape> transformed) {
		for(Map.Entry<Integer, List<Shape>> entry: possible.entrySet()) {
			if(checkEquality(entry.getValue(), transformed)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkEquality(List<Shape> first, List<Shape> second) {
		if(first.size() == second.size()) {
			for(int i=0;i<first.size();i++) {
				if(!first.get(i).equals(second.get(i))){
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public Map<Integer, List<Shape>> runPuzzle(PaintTool paintTool, Template template){
		Map<Integer, List<Shape>> resultTransformations =
				new HashMap<>();
		initialShapes
		.forEach((key, value) -> {
			for(Shape shape: value) {
				if(shape.getImage() == null) {
					BufferedImage image = 
							template.prepareCell();
					shape.setImage(image);
					paintTool.setGraphics((Graphics2D)image.getGraphics());
					paintTool.setColor(Color.BLACK);
					paintTool.setBrushSize(0.1f);
					paintTool.draw(shape, template.getCellSize(), true);
				}
			}
			resultTransformations.put(key, value);
		});
		initialShapes
		.forEach((key, value) -> {
			int currentkey = key;
			List<Shape> currentShapes = value;
			while(true) {
				int nextKey = -1;
				for(int colIndex=0;colIndex<cols*lines;colIndex++)  {
					if(rules[currentkey][colIndex] != null) {
						nextKey = colIndex;
						List<Shape> transformedShapes =
								new LinkedList<>();
						for(int shapeIndex = 0;shapeIndex<currentShapes.size();shapeIndex++) {
							Shape shape = currentShapes.get(shapeIndex);
							List<Transformation> transformations =
									rules[currentkey][colIndex].getTransformationsAt(shapeIndex+1);
							
							Shape cloneShape = shape.getCopy();
							
							if(transformations == null) {
								transformedShapes.add(cloneShape);
								continue;
							}
							for(int transIndex = 0;transIndex<transformations.size();transIndex++) {
								cloneShape = transformations.get(transIndex).apply(cloneShape);
							}
							transformedShapes.add(cloneShape);
						}
						resultTransformations.put(colIndex, transformedShapes);
						currentShapes = transformedShapes;
					}
				}
				if(nextKey == -1) {
					break;
				} else {
					currentkey = nextKey;
				}
			}
		});
		return resultTransformations;
	}

	public int getLines() {
		return lines;
	}
	
	public int getCols() {
		return cols;
	}
}
