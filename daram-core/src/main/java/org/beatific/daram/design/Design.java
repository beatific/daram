package org.beatific.daram.design;

import java.util.HashMap;
import java.util.Map;

import org.beatific.daram.mbean.MBeanManager;
import org.beatific.ddirori.context.ApplicationContextUtils;
import org.beatific.ddirori.repository.RepositoryStore;

public class Design {

	private String name;
	private String caption;
	private String captionExpression;
	private final Map<String, Graph> graphs = new HashMap<String, Graph>();
	private String xTag;
	private String yTag;
	private final RepositoryStore store = ApplicationContextUtils.getApplicationContext().getStore();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCaption() {
		return caption;
	}
	
	public void setCaptionExpression(String captionExpression) {
		this.captionExpression = captionExpression;
	}
	
	public void loadDesign(MBeanManager manager) {
		graphs.clear();
		caption = null;
		
		loadCaption(manager);
		loadGraphs(manager);
		
		store.save(this);
	}
	
	private void loadCaption(MBeanManager manager) {
		this.caption = (String)manager.extract(this.captionExpression);
	}
	
	private void loadGraphs(MBeanManager manager) {
		for(Graph graph : this.graphs.values()) {
			graph.loadGraph(manager);
		}
	}
	
	public Map<String, Graph> getGraphs() {
		return graphs;
	}
	
	public void addGraph(String name, Graph graph) {
		this.graphs.put(name, graph);
	}
	
	public String getxTag() {
		return xTag;
	}
	
	public void setxTag(String xTag) {
		this.xTag = xTag;
	}
	
	public String getyTag() {
		return yTag;
	}
	
	public void setyTag(String yTag) {
		this.yTag = yTag;
	}
	
	
	@Override
	public String toString() {
		return "Design [name=" + name + ", caption=" + caption
				+ ", captionExpression=" + captionExpression + ", graphs="
				+ graphs + ", xTag=" + xTag + ", yTag=" + yTag + "]";
	}
	
	
	
}
