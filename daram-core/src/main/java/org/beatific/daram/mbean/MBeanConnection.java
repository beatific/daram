package org.beatific.daram.mbean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.MBeanServerConnection;

public class MBeanConnection {
	private MBeanServerConnection connection;
	private List<MBean> mbeans;
	
	public void setConnection(MBeanServerConnection connection) {
		this.connection = connection;
	}

	public void setMbeans(List<MBean> mbeans) {
		this.mbeans = mbeans;
	}
	
	public Map<String, Object> reload() {
		
		Map<String, Object> beans = new HashMap<String, Object>();
		for(MBean mbean : mbeans)  {
			beans.putAll(mbean.loadMBean(this.connection));
		}
		return beans;
	}
}
