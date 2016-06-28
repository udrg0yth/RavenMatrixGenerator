package rule;

public abstract class HiddenMarkovModel <T> {
	private int numberOfStates;
	private int numberOfObjects;
	private double [][] transitionProbabilities;
	private double [][] pickingProbabilities;
	
	protected HiddenMarkovModel(int numberOfStates, int numberOfObjects) {
		this.numberOfStates = numberOfStates;
		this.numberOfObjects = numberOfObjects;
		transitionProbabilities = new double[numberOfStates][numberOfStates];
		pickingProbabilities = new double[numberOfStates][numberOfObjects];
	}
	
	public void setStateProbability(int firstState, int secondState, double probability) {
		transitionProbabilities[firstState][secondState] = probability;
	}
	
	public void setPickingProbability(int state, int objectIndex, double probability) {
		pickingProbabilities[state][objectIndex] = probability;
	}
	
	public double getStateProbability(int firstState, int secondState) {
		return transitionProbabilities[firstState][secondState];
	}
	
	public double getPickingProbability(int firstState, int objectIndex) {
		return pickingProbabilities[firstState][objectIndex];
	}

	public int getNumberOfStates() {
		return numberOfStates;
	}

	public int getNumberOfObjects() {
		return numberOfObjects;
	}
}
