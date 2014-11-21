package acano;

public class Problems {
	
	public static void main(String[] args) {
		int [] increasingSubsequence = {};
		printArray(increasingSubsequence);
		int [] ans = reverseIncreasingSubsequence(increasingSubsequence);
		printArray(ans);
		int[] sortedArray1 = {};
		int[] sortedArray2 = {};
		int[] indices = pairOfIndices(sortedArray1,sortedArray2, 2);
		printArray(indices);
		if(indices.length == 2){
			int[] numbers = {sortedArray1[indices[0]], sortedArray2[indices[1]]};
			printArray(numbers);
		}else{
			System.err.println("No indices found");
		}		
	}
	
	public static int[] pairOfIndices(int[] array1, int[] array2,int num){
		int i = 0;
		int j = 0;
		int sum;
		while(i < array1.length){
			j = 0;
			sum = 0;
			while(j < array2.length && sum < num){
				sum = array1[i]+ array2[j];
				if(sum == num){
					//return the two indices
					int[] indices = {i,j};
					return indices;
				}
				j++;
			}
			i++;
		}
		//return a -1 to signify not found
		int[] notFound = {-1};
		return notFound;
	}
	
	
	public static int[] reverseIncreasingSubsequence(int[] array){
		int maxLen = 0;
		int maxStart = 0;
		int maxEnd = 0;
		int start = 0;
		int end = 0;
		int len = 0;
		int i = 1;
		while(i < array.length){
			if(array[i] > array[i-1]){
				end=i;
			}else{
				len = end - start+1;
				if(len > maxLen){
					maxStart = start;
					maxEnd = end;
					maxLen = len;	
				}
				start = i;
				end = i;
			}
			i++;
		}
		len = end - start+1;
		if(len > maxLen){
			maxStart = start;
			maxEnd = end;
		}
		//reverse the longest subsequence
		return reverseSubSequence(array,maxStart,maxEnd);
	}
	
	private static int[] reverseSubSequence(int[] array, int start, int end){
		int i = start;
		int j = end;
		while(i < j){
			int temp = array[j];
			array[j] = array[i];
			array[i] = temp;
			i++;
			j--;
		}
		//return reversed array
		return array;
	}
	
	private static void printArray(int[] array){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i: array){
			sb.append(i);
			sb.append(",");
		}
		if(sb.length() > 1)
			sb.setCharAt(sb.length()-1, ']');
		else
			sb.append("]");
		System.out.println(sb.toString());
	}
	
	
	
	
}
