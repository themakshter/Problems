package bt;

public class Util {

	public static  String constructNormalisedName(String originalName){
		StringBuilder sb = new StringBuilder();
		String[] bits = originalName.toLowerCase().trim().split(" ");
		for(String bit : bits){
			sb.append(bit);
		}
		return sb.toString();
	}
}
