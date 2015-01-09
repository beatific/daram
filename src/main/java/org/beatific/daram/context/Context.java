package org.beatific.daram.context;

import javax.management.MBeanServerConnection;


public interface Context {

	public void setAttribute(String var, String attribute);
	public Object getAttribute(String var);
	public String getConfigValue(String configKey);
	public void setConnection(MBeanServerConnection connection);
	public void setObjectName(String objectName);
}
