package org.beatific.daram.result;

import java.util.List;

public class DesignResult {

	private String name;
	private List<GraphResult> graphs;
	private String caption;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<GraphResult> getGraphs() {
		return graphs;
	}
	public void setGraphs(List<GraphResult> graphs) {
		this.graphs = graphs;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	@Override
	public String toString() {
		return "DesignResult [name=" + name + ", graphs=" + graphs
				+ ", caption=" + caption + "]";
	}
}
