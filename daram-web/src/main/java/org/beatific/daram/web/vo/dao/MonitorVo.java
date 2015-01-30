package org.beatific.daram.web.vo.dao;

import java.util.Date;

public class MonitorVo {

	private Long monitorId;
	private Date monitorTime;
	public Long getMonitorId() {
		return monitorId;
	}
	public void setMonitorId(Long monitorId) {
		this.monitorId = monitorId;
	}
	public Date getMonitorTime() {
		return monitorTime;
	}
	public void setMonitorTime(Date monitorTime) {
		this.monitorTime = monitorTime;
	}
	@Override
	public String toString() {
		return "MonitorVo [monitorId=" + monitorId + ", monitorTime="
				+ monitorTime + "]";
	}
	
}
