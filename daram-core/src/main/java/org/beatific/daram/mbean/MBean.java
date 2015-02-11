package org.beatific.daram.mbean;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

public class MBean {

	private ObjectName objectName;
	private Map<String, String> attributes = new HashMap<String, String>();
	
	public void setObjectName(String objectName) {
		try {
			this.objectName = new ObjectName(objectName);
		} catch (MalformedObjectNameException e) {
			throw new ObjectNameCreateException(e);
		}
	}
	
	public Map<String, String> getAttributes() {
		return attributes;
	}
	
	public void setAttribute(String name, String attribute) {
		this.attributes.put(name, attribute);
	}
	
	public Map<String, Object> loadMBean(MBeanConnection connection) {
		Map<String, Object> result = new HashMap<String, Object>();
		for(Entry<String, String> entry : attributes.entrySet())
		try {
		    Object value = connection.getAttribute(objectName, entry.getValue());
		    result.put(entry.getKey(), value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String toString() {
		return "MBean [objectName=" + objectName + ", attributes=" + attributes
				+ "]";
	}
	
}
