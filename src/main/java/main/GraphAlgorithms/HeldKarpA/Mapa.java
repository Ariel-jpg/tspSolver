package main.GraphAlgorithms.HeldKarpA;

import java.util.Random;

public class Mapa {
	
	private boolean sameDistance;
	private int cities;
	private int maxDistance;
	
	
	public Mapa(int cities, int maxDistance, boolean sameDistance) {
		setMaxDistance(maxDistance);
		setSameDistance(sameDistance);
	}
	
	public Mapa(int cities, int maxDistance) {
		this(cities, maxDistance, true);
	}
	
	public int[][] setMatrix() {
		int[][] matrix = new int[cities][cities];
		Random generator = new Random();
		for(int i = 0; i < cities; i++)  {
			for(int j = 0; j < cities; j++)  {
				if(i > j && sameDistance) {
					matrix[i][j] = matrix[j][i];
				} else {
					matrix[i][j] = generator.nextInt(maxDistance + 1);
				}
			}
		}
		return matrix;
	}
	
	public void setMaxDistance(int maxDistance) {
		this.maxDistance = maxDistance;
	}
	
	public void setSameDistance(boolean sameDistance) {
		this.sameDistance = sameDistance;
	}
	
	//getters
	public int getCities() {
		return this.cities;
	}
	
	public int getMaxDistance() {
		return this.maxDistance;
	}
	
	public boolean getSameDistance() {
		return this.sameDistance;
	}
	
}
