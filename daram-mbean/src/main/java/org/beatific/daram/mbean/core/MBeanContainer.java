package org.beatific.daram.mbean.core;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.modelmbean.ModelMBean;

import org.beatific.ddirori.utils.AnnotationUtils;

public class MBeanContainer {

	private Map<String, Object> container = new HashMap<String, Object>();
	private static MBeanContainer mbeanContainer;
	
	public static MBeanContainer getMBeanContainer() {
		
		synchronized (MBeanContainer.class) {
			if(mbeanContainer == null) mbeanContainer = new MBeanContainer();
		}
		return mbeanContainer;
	}
	
	private MBeanContainer() {
		init();
	}
	
	private List<Object> getMBeans() {
		
		List<Object> mbeans = new ArrayList<Object>();
		for(Class<?> clazz : AnnotationUtils.findClassByAnnotation("org.beatific.daram", MBean.class)) {
			try {
				mbeans.add(clazz.newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return mbeans;
		
	}
	
	private String getObjectName(Object mbean) {
		Class<?> clazz = mbean.getClass();
		MBean annotation = clazz.getAnnotation(MBean.class);
		return annotation.objectName();
	}
	
	private void init() {
		for(Object mbean : getMBeans()) {
			register(mbean);
		}
	}
	
	public Object getMBean(String objectName) {
		return container.get(objectName);
	}
	
	private void register(Object object) {
		
		String objectName = getObjectName(object);
		registerMBeanServer(objectName, object);
		container.put(objectName, object);
		
	}
	
	private void registerMBeanServer(String objectName, Object object) {
		
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		
		try {
			ModelMBean mbean = MBeanSupport.makeModelMBean(object);
			ObjectName name = new ObjectName(objectName);
			mbs.registerMBean(mbean, name);
			
		} catch (Exception e) {}
	}
	
}
