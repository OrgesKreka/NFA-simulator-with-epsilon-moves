import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class SimEnkaInputReader {

	private List<InputSequence> inputSequences;

	private State startingState;
	private List<State> states;
	
	private List<String> alphabet;
	private List<Transition> transitions;
	
	public void readInput(InputStream is) throws SimEnkaInputReaderException {
		List<String> inputLines = readInputLines(is);
		if(inputLines.size() < 5) {
			throw new SimEnkaInputReaderException();
		}
		
		inputSequences = extractInputSequences(inputLines.get(0));
		
		states = splitToStates(inputLines.get(1));
		List<State> acceptableStates = splitToStates(inputLines.get(3));
		applyAcceptableStates(states, acceptableStates);
		
		alphabet = splitToList(inputLines.get(2));
		startingState = new State(inputLines.get(4));
		transitions = extractTransitions(inputLines);
	}

	private List<Transition> extractTransitions(List<String> inputLines) {
		List<Transition> transitions = new ArrayList<Transition>();
		
		for(int i = 5; i < inputLines.size(); i++) {
			String line = inputLines.get(i);
			String[] splitByArrow = line.split("->");
			
			String startState = splitByArrow[0].split(",")[0];
			String symbol = splitByArrow[0].split(",")[1];
			List<String> endStates = Arrays.asList(splitByArrow[1].split(","));
			
			for(String endState : endStates) {
				transitions.add(new Transition(new State(startState), symbol, new State(endState)));
			}
		}
		
		return transitions;
	}
	
	private void applyAcceptableStates(List<State> allStates, List<State> acceptableStates) {
		for(State state : allStates) {
			if(acceptableStates != null) {
				for(State acceptableState : acceptableStates) {
					if(acceptableState.equals(state)) {
						state.setAcceptable(true);
					}
				}
			}
		}
	}
	
	private List<State> splitToStates(String line) {
		List<State> states = new ArrayList<State>();
		String[] lineArray = line.split(",");
		
		for(String stateValue : lineArray) {
			State state = new State(stateValue);
			states.add(state);
		}
		
		return states;
	}
	
	private List<String> splitToList(String line) {
		String[] lineArray = line.split(",");
		return Arrays.asList(lineArray);
	}
	
	private List<InputSequence> extractInputSequences(String line) {
		List<InputSequence> list = new ArrayList<InputSequence>();
		
		String[] lineArray = line.split("\\|");
		for(String inputSequeneLine : lineArray) {
			InputSequence inputSequene = new InputSequence(inputSequeneLine);
			list.add(inputSequene);
		}
		
		return list;
	}
	
	private List<String> readInputLines(InputStream is) {
		Scanner s = new Scanner(is);
		List<String> list = new ArrayList<String>();
		while (s.hasNext()){
		    list.add(s.next());
		}
		s.close();
		
		return list;
	}

	public SimulatorDefinitions generateSimulatorDefinitions() {
		SimulatorDefinitions simulatorDefinitions = new SimulatorDefinitions();
		
		simulatorDefinitions.setAlphabet(alphabet);
		simulatorDefinitions.setInputSequences(inputSequences);
		simulatorDefinitions.setStartingState(startingState);
		simulatorDefinitions.setStates(states);
		simulatorDefinitions.setTransitions(transitions);
		
		return simulatorDefinitions;
	}
	
}
