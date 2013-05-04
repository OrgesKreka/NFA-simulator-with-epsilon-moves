import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Simulator {

	private static final String INPUT_EPSILON_TRANSITION_SYMBOL = "$";
	
	private static final String OUTPUT_SYMBOL_FOR_EMPTY_STATE_LIST = "#";
	private static final String OUTPUT_STATE_LISTS_SEPARATOR = "|";
	private static final String OUTPUT_ALPHABET_SYMBOLS_SEPARATOR = ",";
	
	private SimulatorDefinitions simulatorDefinitions;
	
	public Simulator(SimulatorDefinitions simulatorDefinitions) {
		this.simulatorDefinitions = simulatorDefinitions;
	}

	public void run() {
		List<InputSequence> inputSequences = simulatorDefinitions.getInputSequences();
		for(InputSequence inputSequence : inputSequences) {
			Set<State> currentStates = new HashSet<State>();
			currentStates.add(simulatorDefinitions.getStartingState());
			
			currentStates = makeEpsilonTransitions(currentStates);
			System.out.print(statesTextFormatting(currentStates, true));
			
			List<String> alphabet = inputSequence.getAlphabet();
			for(String symbol : alphabet) {
				Set<State> nextStates = new HashSet<State>();
				for(State currentState : currentStates) {
					nextStates.addAll(getNextStates(currentState, symbol));
				}
				
				// Add all other states available through epsilon transitions
				nextStates = makeEpsilonTransitions(nextStates);
				System.out.print(OUTPUT_STATE_LISTS_SEPARATOR + statesTextFormatting(nextStates, true));
				
				currentStates = nextStates;
			}
			
			System.out.println();
		}
	}

	private Set<State> makeEpsilonTransitions(Set<State> states) {
		List<State> checkedStates = new ArrayList<State>();
		Set<State> finalStates = new HashSet<State>();
		
		while(true) {
			// There could be multiple epsilon transitions so be sure
			// to loop again and again as long as it needs
			boolean hasUncheckedState = false;
			Set<State> tmpCurrentStates = new HashSet<State>();
			for(State state : states) {
				if(!checkedStates.contains(state)) {
					checkedStates.add(state);
					tmpCurrentStates.addAll(getNextStates(state, INPUT_EPSILON_TRANSITION_SYMBOL));
					tmpCurrentStates.add(state);
					hasUncheckedState = true;
				}
			}
			states = tmpCurrentStates;
			finalStates.addAll(states);
			
			if(hasUncheckedState == false) {
				break;
			}
		}
		
		return finalStates;
	}
	
	private String statesTextFormatting(Set<State> statesSet, boolean orderBeforePrint) {
		if(statesSet.size() == 0) {
			statesSet.add(new State(OUTPUT_SYMBOL_FOR_EMPTY_STATE_LIST));
		}
		
		List<State> states = new ArrayList<State>();
		states.addAll(statesSet);
		
		if(orderBeforePrint) {
			Collections.sort(states, new Comparator<State>() {
				@Override
				public int compare(State o1, State o2) {
					return o1.getValue().compareTo(o2.getValue());
				}
			});
		}
		
		String line = "";
		line += states.get(0);
		for(int i = 1; i < states.size(); i++) {
			line += OUTPUT_ALPHABET_SYMBOLS_SEPARATOR + states.get(i);
		}
		
		return line;
	}

	private Set<State> getNextStates(State state, String symbol) {
		Set<State> expandedStates = new HashSet<State>();
		
		List<Transition> transitions = simulatorDefinitions.getTransitions();
		for(Transition transition : transitions) {
			if(transition.getStartState().equals(state) && transition.getSymbol().equals(symbol)) {
				expandedStates.add(transition.getEndState());
			}
		}
		
		return expandedStates;
	}
	
}
