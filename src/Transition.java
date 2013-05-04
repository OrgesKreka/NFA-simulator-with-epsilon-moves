

public class Transition {

	private State startState;
	private String symbol;
	private State endState;
	
	public Transition(State startState, String symbol, State endState) {
		this.startState = startState;
		this.symbol = symbol;
		this.endState = endState;
	}

	public State getEndState() {
		return endState;
	}
	
	public State getStartState() {
		return startState;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	@Override
	public String toString() {
		return startState + "," + symbol + "->" + endState;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((endState == null) ? 0 : endState.hashCode());
		result = prime * result
				+ ((startState == null) ? 0 : startState.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transition other = (Transition) obj;
		if (endState == null) {
			if (other.endState != null)
				return false;
		} else if (!endState.equals(other.endState))
			return false;
		if (startState == null) {
			if (other.startState != null)
				return false;
		} else if (!startState.equals(other.startState))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}
	
}
