package org.beatific.daram.design;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DesignHolder {

	private static Map<String, Design> holder = new LinkedHashMap<String, Design>();
	
	public static void hold(Design design) {
		if(holder.containsKey(design.getName()))return;
		holder.put(design.getName(), design);
	}
	
	public static void reload() {
		Long monitorId = null;
		for(Entry<String, Design> entry : holder.entrySet()) {
			
			Design design = entry.getValue();
			if(!design.isSave())design.save();
			
			monitorId = design.loadDesign(monitorId);
		}
	}
	
	public static Map<String, Design> getDesigns() {
		return holder;
	}
	
	public static Design getDesign(String name) {
		return holder.get(name);
	}
}
