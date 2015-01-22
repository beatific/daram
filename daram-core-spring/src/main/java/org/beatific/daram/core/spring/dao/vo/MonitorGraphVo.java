package org.beatific.daram.core.spring.dao.vo;

public class MonitorGraphVo {

	private Long monitorId;
	private String designName;
	private String graphName;
	private Double yValue;
	public Long getMonitorId() {
		return monitorId;
	}
	public void setMonitorId(Long monitorId) {
		this.monitorId = monitorId;
	}
	public String getDesignName() {
		return designName;
	}
	public void setDesignName(String designName) {
		this.designName = designName;
	}
	public String getGraphName() {
		return graphName;
	}
	public void setGraphName(String graphName) {
		this.graphName = graphName;
	}
	public Double getyValue() {
		return yValue;
	}
	public void setyValue(Double yValue) {
		this.yValue = yValue;
	}
	@Override
	public String toString() {
		return "MonitorGraphVo [monitorId=" + monitorId + ", designName="
				+ designName + ", graphName=" + graphName + ", yValue="
				+ yValue + "]";
	}
	
}
