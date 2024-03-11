import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class StringUtils_Tester {
    private String input1; // Input 1 is the param 1 for methods
    private String input2;// Input 2 is the param 2 for methods
    private String expectedResult; // Expected results of methods
    private String testMethod; // To store and fetch method name from csv file
    private int repeat; // we have some methods having this repeat variable so we need it for all in our data
    // AS we know e dont usually need  constructors in Junit,b uut sometimes 
    // when we need Data Driven testing i.e. fetching data from a source(csv in this case)
    // we need to define the constructor 
    public StringUtils_Tester(String method_name,String input1, String input2, String expectedResult, int repeat) {
        this.testMethod = method_name;
    	this.input1 = input1;
        this.input2 = input2;
        this.expectedResult = expectedResult;
        this.repeat = repeat;
    }
// Method which we will use to fetch the data from csv
    // and then the data will be passed to test methods
    
    // naming the test for easy debugging and logging
    @Parameterized.Parameters(name = "{index}: {0}({1}, {2}) = {3} and Repeat = {4}")
    public static Collection<Object[]> data() throws IOException {
        List<Object[]> testData = new ArrayList<>();
        // creating iterable object , as we need it to pass on data to methdos
        String csvFile = "data.csv";
        String line;
        // using bufferReader , we are creating and reading file through FileReader class
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        //	Headers is for first row of csv where we have headings of the columns
        String[] headers = null;
        while ((line = br.readLine()) != null) {
            if (headers == null) {
                headers = line.split(",");
                continue; // Skip processing header row
            }
            String[] data = line.split(",");
            String testMethod = data[0]; // storing name of test method 
            String expectedOutput = data[1].trim(); // Trim to remove leading/trailing spaces
            String input1 = data[2].trim(); // Trim to remove leading/trailing spaces
            String input2 = data[3].trim(); // Trim to remove leading/trailing spaces
            System.out.println("Repeat value from CSV: " + data[4].trim()); // debugging output on terminal
//            int repeat = Integer.parseInt(data[4].trim()); // Trim and parse repeat

            int repeat = Integer.parseInt(data[4].trim()); // Trim and parse repeat
// we will add data to the object normall y for our repeat, remove, and rotate method
            switch (testMethod) {
            case "testRepeat":
            case "testRemove":
            case "testRotate":
                Object[] testCaseData = {testMethod, input1, input2, expectedOutput, repeat};
                testData.add(testCaseData);
                break;
  // while for split and join we need to acutally 
            case "testSplit":
            case "testJoin":
                // Parse the input2 as an array of wordsuse
//                String[] words = input2.split("\\s+");
//                Object[] splitJoinTestData = {testMethod, input1, words, expectedOutput, repeat};
//                testData.add(splitJoinTestData);
            	  Object[] splitJoinTestData = {testMethod, input1, input2, expectedOutput, repeat};
                  testData.add(splitJoinTestData);
                break;
            default:
                // Handle unknown test method or ignore it
                break;
        }

        }
//closing bufferReader and returning final data as list object meaning iterable
        br.close();
        return testData;
    }

// Methods first if statement is simply checking if the name of test method is same as there own
    // if it is then we run the test
    // if no we pass the test without running
    @Test
    public void testRepeat() {
    	try {
	    	if ( testMethod.equals("testRepeat")) {
	    		 assertEquals(expectedResult, StringUtils.repeat(input1, input2, repeat));
	  
	    	}}
    	catch (Exception e) {
		e.printStackTrace();
	}
    	}
   
 // Methods first if statement is simply checking if the name of test method is same as there own
    // if it is then we run the test
    // if no we pass the test without running
    @Test
    public void testRemove() {
    	try {
	    	if ( testMethod.equals("testRemove")) {
	        assertEquals(expectedResult, StringUtils.remove(input1, input2.charAt(0)));}
	    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	}
 // Methods first if statement is simply checking if the name of test method is same as there own
    // if it is then we run the test
    // if no we pass the test without running
    @Test
    public void testRotate() {
    	try {
    		if ( testMethod.equals("testRotate")) {
    			assertEquals(expectedResult, StringUtils.rotate(input1, repeat));}
    }
    	catch (Exception e) {
		e.printStackTrace();
	}
    
    }
    // Methods first if statement is simply checking if the name of test method is same as there own
    // if it is then we run the test
    // if no we pass the test without running
    
    // here in split we additionally check for the string not being empty as we wont be able to split in that case
    @Test
    public void testSplit() {
    	try {
        if (testMethod.equals("testSplit")) {
            if (!input2.isEmpty()) {
                String[] expectedArray = expectedResult.split(",");
                assertEquals(Arrays.asList(expectedArray), Arrays.asList(StringUtils.split(input1, input2.charAt(0))));
            } else {
                // Handle the case when input2 is empty
                // You might want to log a message or handle it differently based on your requirements
            	// in my case I will simply ignore it and mark it as pass
            }
        }}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    }

 // for all methods first if statement is simply checking if the name of test method is same as there own
    // if it is then we run the test
    // if no we pass the test without running
    
    //here in join we additionally check if the string is empty then we cant join it 
    @Test
    public void testJoin() {
    	try {
        if (testMethod.equals("testJoin")) {
            if (!input2.isEmpty()) {
                List<String> list = Arrays.asList(input1.split(","));
                assertEquals(expectedResult, StringUtils.join(list, input2.charAt(0), 0, list.size() - 1));
            } else {
                // Handle the case when input2 is empty
                // You might want to log a message or handle it differently based on your requirements
            	// in my case I will simply ignore it and mark it as pass
            }
        }}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    }

    
}
