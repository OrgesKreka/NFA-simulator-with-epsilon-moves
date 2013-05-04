import java.util.List;


public class SimulatorDefinitions {

	private List<InputSequence> inputSequences;
	
	private State startingState;
	private List<State> states;
	
	private List<String> alphabet;
	private List<Transition> transitions;
	
	public List<InputSequence> getInputSequences() {
		return inputSequences;
	}
	public void setInputSequences(List<InputSequence> inputSequences) {
		this.inputSequences = inputSequences;
	}
	public State getStartingState() {
		return startingState;
	}
	public void setStartingState(State startingState) {
		this.startingState = startingState;
	}
	public List<State> getStates() {
		return states;
	}
	public void setStates(List<State> states) {
		this.states = states;
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
		for(State state : states) {
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
