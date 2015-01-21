package org.beatific.daram.design;

import org.beatific.daram.mbean.MBeanManager;

public class Graph {

	private double x;
	private double y;
	private String xExpression;
	private String yExpression;
	
	public void setxExpression(String xExpression) {
		this.xExpression = xExpression;
	}
	public void setyExpression(String yExpression) {
		this.yExpression = yExpression;
	}
	public double getX() {
		return x;
	}
	public void loadGraph(MBeanManager manager) {
		this.x = Double.parseDouble((String)manager.extract(xExpression));
		this.y = Double.parseDouble((String)manager.extract(yExpression));
	}
	public double getY() {
		return y;
	}
	@Override
	public String toString() {
		return "Graph [x=" + x + ", y=" + y + ", xExpression=" + xExpression
				+ ", yExpression=" + yExpression + "]";
	}
}
