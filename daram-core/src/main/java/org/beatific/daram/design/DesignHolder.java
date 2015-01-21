package org.beatific.daram.design;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.beatific.daram.mbean.MBeanManager;

public class DesignHolder {

	private static Map<String, Design> holder = new LinkedHashMap<String, Design>();
	
	public static void hold(Design design) {
		holder.put(design.getName(), design);
	}
	
	public static void reload(MBeanManager manager) {
		for(Entry<String, Design> entry : holder.entrySet()) {
			entry.getValue().loadDesign(manager);
		}
	}
	
	public static Map<String, Design> getDesigns() {
		return holder;
	}
	
	public static Design getDesign(String name) {
		return holder.get(name);
	}
}
