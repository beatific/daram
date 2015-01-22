package org.beatific.daram.design;

import org.beatific.daram.mbean.MBeanManager;

public class Graph {

	private String name;
	private double y;
	private String yExpression;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setyExpression(String yExpression) {
		this.yExpression = yExpression;
	}
	public void loadGraph() {
		this.y = Double.parseDouble((String)MBeanManager.extract(yExpression));
	}
	public double getY() {
		return y;
	}
	@Override
	public String toString() {
		return "Graph [y=" + y + ", yExpression=" + yExpression + "]";
	}
	
}
