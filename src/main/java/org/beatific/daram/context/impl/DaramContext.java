package org.beatific.daram.context.impl;

import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.beatific.daram.context.Context;

public class DaramContext implements Context {

	private final Map<String, Object> attributes;
	private final Map<String, String> config;
	private MBeanServerConnection connection;
	private ObjectName mbeanName;
	
	public DaramContext(Map<String, String> config) {
		this.attributes = new HashMap<String, Object>();
		this.config = config;
	}
	
	public Object getAttribute(String var) {
		return attributes.get(var);
	}
	
	public String getConfigValue(String configKey) {
		return config.get(configKey);
	}
	
	public void setConnection(MBeanServerConnection connection) {
		this.connection = connection;
	}
	public void setObjectName(String objectName) {
		try {
			this.mbeanName = new ObjectName(objectName);
		} catch (MalformedObjectNameException e) {}
	}
	
	public void setAttribute(String var, String attribute) {
		if(attributes.containsKey(var))return;
		try {
		    Object value = connection.getAttribute(mbeanName, attribute);
		    attributes.put(var, value);
		    System.out.println(attributes);
		} catch (Exception e) {}
	}
}
