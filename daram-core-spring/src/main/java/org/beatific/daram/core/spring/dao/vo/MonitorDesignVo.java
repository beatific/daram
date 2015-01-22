package org.beatific.daram.core.spring.dao.vo;

public class MonitorDesignVo {

	private Long monitorId;
	private String designName;
	private String caption;
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
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	@Override
	public String toString() {
		return "MonitorDesignVo [monitorId=" + monitorId + ", designName="
				+ designName + ", caption=" + caption + "]";
	}
	
}
