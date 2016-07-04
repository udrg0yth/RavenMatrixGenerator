package rule;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import field.Transformation;

public class RuleGenerator extends HiddenMarkovModel<Transformation>{

	private Map<Integer, List<Transformation>> transformationsMap;
	
	public RuleGenerator(int numberOfStates, int numberOfObjects) {
		super(numberOfStates, numberOfObjects);
		transformationsMap = 
				new HashMap<>();
	}
	
	public RuleGenerator addTransformationAt(int[] indexArr, Transformation transformation) {
		for(int index: indexArr) {
			addTransformationAt(index, transformation);
		}
		return this;
	}
	
	public RuleGenerator addTransformationAt(int index, Transformation transformation) {
		List<Transformation> transformations =
				transformationsMap.get(index);
		if(transformations != null) {
			transformations.add(transformation);
		} else {
			transformations = new LinkedList<>();
			transformations.add(transformation);
			transformationsMap.put(index, transformations);
		}
		return this;
	}
	
	public Rule generate(int depth, int[] shapes) {
		Random random =
				new Random();
		
		Rule rule =
				new Rule();
		for(int shape: shapes){
			int currentDepth = 0;
			while(currentDepth < depth) {
				double pickingProbability = random.nextDouble();
				double totalProb = 0;
				for(int i=0;i<getNumberOfObjects();i++) {
					if(pickingProbability < totalProb + getPickingProbability(0, i)) {
						rule.addTransformationAt(shape, transformationsMap
								.get(shape)
								.get(i));
						++currentDepth;
						break;
					} 
					totalProb += getPickingProbability(0, i);
				}
				if(currentDepth == depth) {
					break;
				}
			}
		}
		return rule;
	}

}
