package rule;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import field.Transformation;

public class Rule {
	Map<Integer, List<Transformation>> transformationsMap;
	
	Rule() {
		transformationsMap = new HashMap<>();
	}
	
	public Rule addTransformationAt(int index, Transformation transformation) {
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
	
	public List<Transformation> getTransformationsAt(int index) {
		return transformationsMap.get(index);
	}
	
}
