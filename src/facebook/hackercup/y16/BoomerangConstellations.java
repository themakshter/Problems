package facebook.hackercup.y16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class BoomerangConstellations {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(reader.readLine());
		for (int i = 0; i < cases; i++) {
			int nights = Integer.parseInt(reader.readLine());
			Point[] points = new Point[nights];
			for (int j = 0; j < nights; j++) {
				points[j] = new Point(reader.readLine());
			}
			int constellations = findBoomerangConstellations(points);
			System.out.println("Case #"+(i+1)+": " + constellations);
		}
	}

	public static int findBoomerangConstellations(Point[] points) {
		HashMap<Double,Integer> map;
		int constellations = 0;
		for(Point p1 : points){
			map = new HashMap<Double,Integer>();
			for(Point p2: points){
				if(p1.equals(p2))
					continue;
				double distance = distanceBetweenPoints(p1, p2);
				if(!map.containsKey(distance)){
					map.put(distance, 1);
				}else{
					int newValue = map.get(distance)+1;
					map.put(distance, newValue);
				}
			}
			for(double key: map.keySet()){
				if(map.get(key) < 2)
					continue;
				else if(map.get(key) == 2)
					constellations +=1;
				else
					constellations += map.get(key);
					
			}
		}
		return constellations;	
	}

	public static double distanceBetweenPoints(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p2.y - p1.y, 2) + Math.pow(p2.x - p1.x, 2));
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(String points) {
		String[] pointsArray = points.split(" ");
		this.x = Integer.parseInt(pointsArray[0]);
		this.y = Integer.parseInt(pointsArray[1]);
	}
	
	public boolean equals(Point p){
		return this.x == p.x && this.y == p.y;
	}

}
