package org.beatific.daram.web.vo.dao;

import java.util.Date;
import java.util.List;

public class MonitorGraphVo {

	private Long monitorId;
	private String designName;
	private String graphName;
	private Date monitorTime;
	private Long xValue;
	private Double yValue;
	private String fromTime; // YYYYMMDDHH24MISS
	private String toTime;  // YYYYMMDDHH24MISS
	
	private List<DesignVo> designs;
	
	public List<DesignVo> getDesigns() {
		return designs;
	}
	public void setDesigns(List<DesignVo> designs) {
		this.designs = designs;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	public Date getMonitorTime() {
		return monitorTime;
	}
	public void setMonitorTime(Date monitorTime) {
		this.xValue = monitorTime.getTime();
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
//		this.xValue = Long.parseLong(format.format(monitorTime));
		this.monitorTime = monitorTime;
	}
	public Long getxValue() {
		return xValue;
	}
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
				+ designName + ", graphName=" + graphName + ", monitorTime="
				+ monitorTime + ", xValue=" + xValue + ", yValue=" + yValue
				+ "]";
	}
	
}
