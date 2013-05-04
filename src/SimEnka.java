import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class SimEnka {

	private final static boolean DEBUG = false;
	
	public static void main(String[] args) {
		simEnka(System.in);
	}

	public static void simEnka(String fileName) throws FileNotFoundException {
		simEnka(new FileInputStream(new File(fileName)));
	}
	
	public static void simEnka(InputStream is) {
		SimEnkaInputReader inputReader = new SimEnkaInputReader();
		try {
			inputReader.readInput(is);
		} catch (SimEnkaInputReaderException e) {
			return;
		}
		
		SimulatorDefinitions simulatorDefinitions = inputReader.generateSimulatorDefinitions();

		long start = System.currentTimeMillis();
		
		Simulator simulator = new Simulator(simulatorDefinitions);
		simulator.run();

		if(DEBUG) {
			simulatorDefinitions.print();
			System.out.println((System.currentTimeMillis()-start)/1000.0);
		}
	}
	
}