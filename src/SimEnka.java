import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class SimEnka {

	private final static boolean DEBUG = false;
	
	public static void main(String[] args) {
		if(DEBUG) {
			simEnka("exampleInput.txt");
		} else {
			simEnka(System.in);
		}
	}

	public static void simEnka(String fileName) {
		try {
			simEnka(new FileInputStream(new File(fileName)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void simEnka(InputStream is) {
		SimEnkaInputReader inputReader = new SimEnkaInputReader();
		try {
			inputReader.readInput(is);
		} catch (SimEnkaInputReaderException e) {
			return;
		}
		
		SimulatorDefinitions simulatorDefinitions = inputReader.generateSimulatorDefinitions();

		if(DEBUG) {
			simulatorDefinitions.print();
			System.out.println();
		}
		
		long start = System.currentTimeMillis();
		
		Simulator simulator = new Simulator(simulatorDefinitions);
		simulator.run();

		if(DEBUG) {
			System.out.println();
			System.out.println((System.currentTimeMillis()-start)/1000.0);
		}
	}
	
}