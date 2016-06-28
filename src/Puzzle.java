import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
	
	public Map<Integer, List<Shape>> runPuzzle(){
		Map<Integer, List<Shape>> resultTransformations =
				new HashMap<>();
		initialShapes
		.forEach((key, value) -> {
			resultTransformations.put(key, value);
		});
		initialShapes
		.forEach((key, value) -> {
			int currentkey = key;
			while(true) {
				int nextKey = -1;
				for(int colIndex=0;colIndex<cols;colIndex++)  {
					if(rules[currentkey][colIndex] != null) {
						nextKey = colIndex;
						List<Shape> transformedShapes =
								new LinkedList<>();
						for(int shapeIndex = 0;shapeIndex<value.size();shapeIndex++) {
							Shape shape = value.get(shapeIndex);
							List<Transformation> transformations =
									rules[currentkey][colIndex].getTransformationsAt(shapeIndex);
							
							try {
								Shape cloneShape = (Shape) shape.clone();
								if(transformations == null) {
									transformedShapes.add(cloneShape);
									continue;
								}
								for(int transIndex = 0;transIndex<transformations.size();transIndex++) {
									cloneShape = transformations.get(transIndex).apply(cloneShape);
								}
								transformedShapes.add(cloneShape);
							} catch (CloneNotSupportedException e) {
							}
						}
						resultTransformations.put(colIndex, transformedShapes);
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
