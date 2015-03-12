package org.beatific.daram.mbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.beatific.daram.design.DesignHolder;
import org.beatific.ddirori.attribute.AttributeExtractor;
import org.beatific.ddirori.bean.BeanContainer;

public class MBeanManager {

	private static MBeanContainer container = new MBeanContainer();
	private static AttributeExtractor extractor;
	private static List<MBeanConnection> connections = new ArrayList<MBeanConnection>();
	public static void setBasePacket(String basePackage) {
	
		String[] packages = basePackage==null ? new String[]{"org.beatific.daram"} : basePackage.split(",");
		
		extractor = new AttributeExtractor(packages, true) {

			@Override
			protected Object getObject(BeanContainer container,
					String objectName) {
				return container.getBean(objectName);
			}
		};
		
	}
	
	public static void reload() {
		for(MBeanConnection connection : connections) {
		
			Map<String, Object> objectMap = connection.reload();
			if(objectMap == null || objectMap.size() == 0) return;
			for(Entry<String, Object> entry : objectMap.entrySet())
				container.registerBean(entry.getKey(), entry.getValue());
		}
		DesignHolder.reload();
	}
	
	public static Object extract(String str) {
		return extractor.extract(container, str);
	}

	public static void addConnection(MBeanConnection connection) {
		connections.add(connection);
	}
	
	public static void extractJstat() {
		for(MBeanConnection connection : connections)
			connection.collectJstatInfo();
	}
	
	
}
