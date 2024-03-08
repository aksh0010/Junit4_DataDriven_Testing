import java.util.List;

public class StringUtils {
	
	//	Repeat a String repeat times to form a new String, with a String separator injected each time
	 public static String repeat(String str, String separator, int repeat) {
	        if (str == null || separator == null || repeat <= 0) {
	            throw new IllegalArgumentException("Invalid input");
	        }
	        StringBuilder result = new StringBuilder();
	        for (int i = 0; i < repeat; i++) {
	            result.append(str);
	            if (i < repeat - 1) {
	                result.append(separator);
	            }
	        }
	        return result.toString();
	    }
	 
	// Removes all occurrences of a character from within the source string
	 public static String remove(String str, char remove) {
	        if (str == null) {
	            throw new IllegalArgumentException("Invalid input");
	        }
	        return str.replace(Character.toString(remove), "");
	    }
	 //Rotate (circular shift) a String of shift characters
	 public static String rotate(String str, int shift) {
		    if (str == null || str.isEmpty()) {
		        throw new IllegalArgumentException("Invalid input: Input string cannot be null or empty");
		    }
		    int len = str.length();
		    if (len == 1 || shift == 0) {
		        return str; // No rotation needed
		    }
		    shift = shift % len;
		    if (shift < 0) {
		        shift = len + shift;
		    }
		    return str.substring(len - shift) + str.substring(0, len - shift);
		}

	 //Splits the provided text into an array, separator specified
	 public static String[] split(String str, char separatorChar) {
	        if (str == null) {
	            throw new IllegalArgumentException("Invalid input");
	        }
	        return str.split(Character.toString(separatorChar));
	    } 
	 
	 // Joins the elements of the provided List into a single String containing the provided list of elements
	 public static String join(List<?> list, char separator, int startIndex, int endIndex) {
	        if (list == null || list.isEmpty() || startIndex < 0 || endIndex >= list.size() || startIndex > endIndex) {
	            throw new IllegalArgumentException("Invalid input");
	        }
	        StringBuilder result = new StringBuilder();
	        for (int i = startIndex; i <= endIndex; i++) {
	            result.append(list.get(i));
	            if (i < endIndex) {
	                result.append(separator);
	            }
	        }
	        return result.toString();
	    }
	    public static void main(String[] args) {
			System.out.println("Code Complied without errors for testing purposes");
		}
}
