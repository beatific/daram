package org.beatific.daram.design;

import java.util.ArrayList;
import java.util.List;

import org.beatific.daram.mbean.MBeanManager;
import org.beatific.ddirori.context.ApplicationContextUtils;
import org.beatific.ddirori.repository.RepositoryStore;

public class Design {

	private String name;
	private String caption;
	private String captionExpression;
	private final List<Graph> graphs = new ArrayList<Graph>();
	private String xTag;
	private String yTag;
	private final RepositoryStore store = ApplicationContextUtils.getApplicationContext().getStore();
	private Long monitorId;
	private boolean isSave = false;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCaption() {
		return caption;
	}
	
	public Long getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(Long monitorId) {
		this.monitorId = monitorId;
	}

	public void setCaptionExpression(String captionExpression) {
		this.captionExpression = captionExpression;
	}
	
	public void save() {
		isSave = true;
		store.save(this);
	}
	
	public boolean isSave() {
		return isSave;
	}
	
	public Long loadDesign(Long monitorId) {
		
		caption = null;
		
		this.monitorId = monitorId;
		
		loadCaption();
		loadGraphs();
		
		store.change(this);
		return this.monitorId;
	}
	
	private void loadCaption() {
		if(captionExpression == null) return;
		this.caption = (String)MBeanManager.extract(this.captionExpression);
	}
	
	private void loadGraphs() {
		for(Graph graph : this.graphs) {
			graph.loadGraph();
		}
	}
	
	public List<Graph> getGraphs() {
		return graphs;
	}
	
	public void addGraph(Graph graph) {
		this.graphs.add(graph);
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
