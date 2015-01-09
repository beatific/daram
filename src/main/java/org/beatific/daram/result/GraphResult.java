package org.beatific.daram.result;

public class GraphResult {

	private Double x;
	private Double y;
	private String name;
	public Double getX() {
		return x;
	}
	public GraphResult setX(Double x) {
		this.x = x;
		return this;
	}
	public Double getY() {
		return y;
	}
	public GraphResult setY(Double y) {
		this.y = y;
		return this;
	}
	public String getName() {
		return name;
	}
	public GraphResult setName(String name) {
		this.name = name;
		return this;
	}
	@Override
	public String toString() {
		return "GraphResult [x=" + x + ", y=" + y + ", name=" + name + "]";
	}
	 
	
}
