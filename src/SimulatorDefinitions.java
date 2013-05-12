import java.util.List;


public class SimulatorDefinitions {

	private List<InputSequence> inputSequences;
	
	private String startingState;
	private List<String> states;
	private List<String> acceptableStates;
	
	private List<String> alphabet;
	private List<Transition> transitions;
	
	public List<InputSequence> getInputSequences() {
		return inputSequences;
	}

	public void setInputSequences(List<InputSequence> inputSequences) {
		this.inputSequences = inputSequences;
	}

	public String getStartingState() {
		return startingState;
	}

	public void setStartingState(String startingState) {
		this.startingState = startingState;
	}

	public List<String> getStates() {
		return states;
	}

	public void setStates(List<String> states) {
		this.states = states;
	}

	public void setAcceptableStates(List<String> acceptableStates) {
		this.acceptableStates = acceptableStates;
	}

	public List<String> getAcceptableStates() {
		return acceptableStates;
	}

	public List<String> getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(List<String> alphabet) {
		this.alphabet = alphabet;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}
	
	public void print() {
		System.out.println("========================");
		System.out.println("Input sequences");
		for(InputSequence inputSequence : inputSequences) {
			System.out.println(inputSequence);
		}
		
		System.out.println("========================");
		System.out.println("Starting state");
		System.out.println(startingState);
		
		System.out.println("========================");
		System.out.println("States");
		for(String state : states) {
			System.out.println(state);
		}
		
		System.out.println("========================");
		System.out.println("Acceptable states");
		for(String state : acceptableStates) {
			System.out.println(state);
		}
		
		System.out.println("========================");
		System.out.println("Alphabet symbols");
		for(String symbol : alphabet) {
			System.out.println(symbol);
		}
		
		System.out.println("========================");
		System.out.println("Transitions");
		for(Transition transition : transitions) {
			System.out.println(transition);
		}
	}
	
	
}
