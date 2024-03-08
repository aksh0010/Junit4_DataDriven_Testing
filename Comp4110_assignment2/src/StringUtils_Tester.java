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
    private String input1;
    private String input2;
    private String expectedResult;
    private String testMethod;
    private int repeat;

    public StringUtils_Tester(String method_name,String input1, String input2, String expectedResult, int repeat) {
        this.testMethod = method_name;
    	this.input1 = input1;
        this.input2 = input2;
        this.expectedResult = expectedResult;
        this.repeat = repeat;
    }

    @Parameterized.Parameters(name = "{index}: {0}({1}, {2}) = {3} and Repeat = {4}")
    public static Collection<Object[]> data() throws IOException {
        List<Object[]> testData = new ArrayList<>();
        String csvFile = "data.csv";
        String line;
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        String[] headers = null;
        while ((line = br.readLine()) != null) {
            if (headers == null) {
                headers = line.split(",");
                continue; // Skip processing header row
            }
            String[] data = line.split(",");
            String testMethod = data[0];
            String expectedOutput = data[1].trim(); // Trim to remove leading/trailing spaces
            String input1 = data[2].trim(); // Trim to remove leading/trailing spaces
            String input2 = data[3].trim(); // Trim to remove leading/trailing spaces
            System.out.println("Repeat value from CSV: " + data[4].trim());
//            int repeat = Integer.parseInt(data[4].trim()); // Trim and parse repeat

            int repeat = Integer.parseInt(data[4].trim()); // Trim and parse repeat

            switch (testMethod) {
            case "testRepeat":
            case "testRemove":
            case "testRotate":
                Object[] testCaseData = {testMethod, input1, input2, expectedOutput, repeat};
                testData.add(testCaseData);
                break;
            case "testSplit":
            case "testJoin":
                // Parse the input2 as an array of words
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

        br.close();
        return testData;
    }


    @Test
    public void testRepeat() {
    	if ( testMethod.equals("testRepeat")) {
    		 assertEquals(expectedResult, StringUtils.repeat(input1, input2, repeat));
  
    	}
    	}
   

    @Test
    public void testRemove() {
    	if ( testMethod.equals("testRemove")) {
        assertEquals(expectedResult, StringUtils.remove(input1, input2.charAt(0)));}
    }

    @Test
    public void testRotate() {
    	if ( testMethod.equals("testRotate")) {
        assertEquals(expectedResult, StringUtils.rotate(input1, repeat));}
    }

    @Test
    public void testSplit() {
        if (testMethod.equals("testSplit")) {
            if (!input2.isEmpty()) {
                String[] expectedArray = expectedResult.split(",");
                assertEquals(Arrays.asList(expectedArray), Arrays.asList(StringUtils.split(input1, input2.charAt(0))));
            } else {
                // Handle the case when input2 is empty
                // You might want to log a message or handle it differently based on your requirements
            }
        }
    }


    @Test
    public void testJoin() {
        if (testMethod.equals("testJoin")) {
            if (!input2.isEmpty()) {
                List<String> list = Arrays.asList(input1.split(","));
                assertEquals(expectedResult, StringUtils.join(list, input2.charAt(0), 0, list.size() - 1));
            } else {
                // Handle the case when input2 is empty
                // You might want to log a message or handle it differently based on your requirements
            }
        }
    }

    
}
