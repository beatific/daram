package org.beatific.daram.context.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.beatific.daram.context.Context;
import org.beatific.daram.pattern.REMatchers;
import org.beatific.daram.result.DesignResult;
import org.beatific.daram.result.GraphResult;
import org.beatific.daram.result.Result;

public class DaramContext implements Context {

	private final Map<String, Object> attributes;
	private final Map<String, String> config;
	private MBeanServerConnection connection;
	private ObjectName mbeanName;
	private Result result = new Result();
	private DesignResult currentDesign;
	
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
		} catch (Exception e) {}
	}

	public Result getResult() {
		return result;
	}

	public void setCaption(String caption) {
		currentDesign.setCaption(caption);
	}
	
	public void setName(String name) {
		
		if(result.getDesigns() == null) {
			result.setDesigns(new ArrayList());
		}
		currentDesign = new DesignResult();
		result.getDesigns().add(currentDesign);
		
		currentDesign.setName(name);
	}

	@Override
	public void addGraph(String x, String y, String name) {
		
		Double doubleX, doubleY;
		if(currentDesign.getGraphs() == null) {
			currentDesign.setGraphs(new ArrayList<GraphResult>());
		}
		
		if(REMatchers.numericMatcher(x).find() || REMatchers.decimalMatcher(x).find()) doubleX = Double.parseDouble(x);
		else throw new RuntimeException("x isn't set decimal value[" + x + "]");
		
		if(REMatchers.numericMatcher(y).find() || REMatchers.decimalMatcher(y).find()) doubleY = Double.parseDouble(y);
		else throw new RuntimeException("y isn't set decimal value[" + y + "]");
		
		currentDesign.getGraphs().add(new GraphResult().setX(doubleX).setY(doubleY).setName(name));
	}
	
	
}
