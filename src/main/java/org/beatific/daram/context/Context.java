package org.beatific.daram.context;

import javax.management.MBeanServerConnection;

import org.beatific.daram.result.Result;


public interface Context {

	public void setAttribute(String var, String attribute);
	public Object getAttribute(String var);
	public String getConfigValue(String configKey);
	public void setConnection(MBeanServerConnection connection);
	public void setObjectName(String objectName);
	public Result getResult();
	public void setCaption(String caption);
	public void addGraph(String x, String y, String name);
	public void setName(String name);
}
