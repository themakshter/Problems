package google;

/**
 * Algorithm to find all possible combinations of the given strings
 * 
 * @author Mohammad Ali
 * 
 */
public class Combinations {

	/**
	 * Trying test case
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String[][] words = { 	{ "quick", "lazy" },
								{ "brown", "black", "grey"},
								{ "fox", "dog" } 
							};
		combinations(words);

	}

	/**
	 * Main method to produce combinations
	 * 
	 * @param words
	 *            input array to make combinations of
	 */
	public static void combinations(String[][] words) {
		// Start our helper method
		String[] combinations = combineAt(words, 0);
		for (String combination : combinations) {
			System.out.println(combination);
		}
		System.out.println(combinations.length);
	}

	/**
	 * Method to start combining the array from the given index
	 * 
	 * @param words
	 *            input file
	 * @param index
	 *            index from where to start combination
	 * @return list of combined words
	 */
	public static String[] combineAt(String[][] words, int index) {
		// Base case where we return the array we have
		if (index == words.length - 1) {
			return words[words.length - 1];
		}
		// call a combine method for this case and previous one
		return combine(words[index], combineAt(words, index + 1));
	}

	/**
	 * Returns array of combined words between the two arrays
	 * 
	 * @param words1
	 *            first array to be combined
	 * @param words2
	 *            second array to be combined
	 * @return combined array of the two
	 */
	public static String[] combine(String[] words1, String[] words2) {
		// Total combinations will be amount of words in array1 multiplied by
		// amount of words in array2
		String[] combined = new String[words1.length * words2.length];
		// keep separate counter for combined array
		int k = 0;
		for (int i = 0; i < words1.length; i++) {
			for (int j = 0; j < words2.length; j++) {
				combined[k] = words1[i] + " " + words2[j];
				k++;
			}
		}
		return combined;
	}
}
