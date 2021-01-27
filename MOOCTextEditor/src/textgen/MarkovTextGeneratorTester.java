package textgen;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;

/**
 * JUnit tests for MarkovTextGenerator methods
 * @author bAe
 *
 */

public class MarkovTextGeneratorTester {
	
	private String emptyString, shortString, longString;
	
	private MarkovTextGeneratorLoL mTGenerator;
	//gen.train(textString);
	
	/**
	 * Setup called for each test
	 * @throws Exception
	 */
	@Before
	public void setup() throws Exception {
		// text generator with a 42-seed degree of randomness
		mTGenerator = new MarkovTextGeneratorLoL(new Random(42));
		// test strings
		emptyString = "";
		shortString = "hi there hi leo";
		longString = "Hello, hello, hello.  "
				+ "Hello there. Hello, hello...  This is a test.  "
				+ "Hello there.  Hello Bob.  Test again. "
				+ "Hello hello hello.";
		
	}
	
	/**
	 * Test train method
	 */
	@Test
	public void testTrain() {
		// test null exception
		// test empty string, word-list should be empty
		mTGenerator.train(emptyString);
		//assertEquals()
		// test shortString
		// test longerString
		// ensure last word as a node label is unique in node-list
	}

}
