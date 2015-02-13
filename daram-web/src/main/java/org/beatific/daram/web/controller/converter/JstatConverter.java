package org.beatific.daram.web.controller.converter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.sql.TIMESTAMP;

public class JstatConverter {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static List convertGraphByValueName(Map dataMap, String valueName) {
		
		List graph = new ArrayList();
		
		Long time = null;
		if(dataMap.get("jstatTime") instanceof TIMESTAMP)
			try {
				time = ((TIMESTAMP)dataMap.get("jstatTime")).toDate();
			} catch (SQLException e) {}
		else throw new RuntimeException("Jstat Time convert Exception Type[" + dataMap.get("jstatTime").getClass().getName() + "]");
		
		graph.add(time);
		graph.add(dataMap.get(valueName));
		return graph;
	}
	
	private static List<List<?>> convertGraphByValueName(List<Map> dataMaps, String valueName) {
		
		List<List<?>> graphs = new ArrayList<List<?>>();
		
		for(Map dataMap : dataMaps) {
			graphs.add(convertGraphByValueName(dataMap, valueName));
		}
		return graphs;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, ?> convertGoogleLineCharts(List<Map> dataMaps) {
		System.out.println(dataMaps);
		Map map = new HashMap();
		
		Map eden = new HashMap();
		eden.put("graphs", convertGraphByValueName(dataMaps, "e"));
		eden.put("xTag", "Time");
		eden.put("yTag", "Eden");
		eden.put("denomination", "Byte");
		eden.put("name", "Eden");
		map.put("e", eden);
		
		Map s0 = new HashMap();
		s0.put("graphs", convertGraphByValueName(dataMaps, "s0"));
		s0.put("xTag", "Time");
		s0.put("yTag", "s0");
		s0.put("denomination", "Byte");
		s0.put("name", "Surviver0");
		map.put("s0", s0);
		
		Map s1 = new HashMap();
		s1.put("graphs", convertGraphByValueName(dataMaps, "s1"));
		s1.put("xTag", "Time");
		s1.put("yTag", "s1");
		s1.put("denomination", "Byte");
		s1.put("name", "Surviver1");
		map.put("s1", s1);
		
		Map old = new HashMap();
		old.put("graphs", convertGraphByValueName(dataMaps, "o"));
		old.put("xTag", "Time");
		old.put("yTag", "old");
		old.put("denomination", "Byte");
		old.put("name", "Old");
		map.put("o", old);
		
		Map perm = new HashMap();
		perm.put("graphs", convertGraphByValueName(dataMaps, "p"));
		perm.put("xTag", "Time");
		perm.put("yTag", "perm");
		perm.put("denomination", "Byte");
		perm.put("name", "Permenet");
		map.put("p", perm);
		
		return map;
	}
}
