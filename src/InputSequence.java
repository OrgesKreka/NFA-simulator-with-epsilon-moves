import java.util.Arrays;
import java.util.List;


public class InputSequence {

	private List<String> alphabet;
	
	public InputSequence(String line) {
		String[] lineArray = line.split(",");
		alphabet = Arrays.asList(lineArray);
	}
	
	public List<String> getAlphabet() {
		return alphabet;
	}

	@Override
	public String toString() {
		return alphabet.toString();
	}
	
}
