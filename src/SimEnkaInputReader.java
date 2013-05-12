import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class SimEnkaInputReader {

	private List<InputSequence> inputSequences;

	private String startingState;
	private List<String> states;
	private List<String> acceptableStates;
	
	private List<String> alphabet;
	private List<Transition> transitions;
	
	public void readInput(InputStream is) throws SimEnkaInputReaderException {
		List<String> inputLines = readInputLines(is);
		if(inputLines.size() < 5) {
			throw new SimEnkaInputReaderException();
		}
		
		inputSequences = extractInputSequences(inputLines.get(0));
		
		states = splitToStates(inputLines.get(1));
		acceptableStates = splitToStates(inputLines.get(3));
		
		alphabet = splitToList(inputLines.get(2));
		startingState = inputLines.get(4);
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
				transitions.add(new Transition(startState, symbol, endState));
			}
		}
		
		return transitions;
	}
	
	private List<String> splitToStates(String line) {
		List<String> states = new ArrayList<String>();
		String[] lineArray = line.split(",");
		
		for(String stateValue : lineArray) {
			states.add(stateValue);
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
		simulatorDefinitions.setAcceptableStates(acceptableStates);
		
		simulatorDefinitions.setTransitions(transitions);
		
		return simulatorDefinitions;
	}
	
}
