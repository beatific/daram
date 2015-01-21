package org.beatific.daram.mbean;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.management.MBeanServerConnection;

import org.beatific.daram.design.DesignHolder;
import org.beatific.ddirori.attribute.AttributeExtractor;
import org.beatific.ddirori.bean.BeanContainer;

public class MBeanManager {

	private MBeanServerConnection connection;
	private List<MBean> mbeans;
	private MBeanContainer container = new MBeanContainer();
	private final AttributeExtractor extractor;

	public MBeanManager(String basePackage) {
	
		String[] packages = basePackage==null ? new String[]{"org.beatific.daram"} : basePackage.split(",");
		
		this.extractor = new AttributeExtractor(packages) {

			@Override
			protected Object getObject(BeanContainer container,
					String objectName) {
				return container.getBean(objectName);
			}
			
		};
		
	}
	
	public void setConnection(MBeanServerConnection connection) {
		this.connection = connection;
	}

	public void setMbeans(List<MBean> mbeans) {
		this.mbeans = mbeans;
	}
	
	public void load() {
		for(MBean mbean : mbeans) {
			Map<String, Object> objectMap = mbean.loadMBean(this.connection);
			for(Entry<String, Object> entry : objectMap.entrySet()) 
				container.registerBean(entry.getKey(), entry.getValue());
		}
		
		DesignHolder.reload(this);
	}
	
	public Object extract(String str) {
		return extractor.extract(container, str);
	}

	@Override
	public String toString() {
		return "MBeanManager [connection=" + connection + ", mbeans=" + mbeans
				+ "]";
	}

}
