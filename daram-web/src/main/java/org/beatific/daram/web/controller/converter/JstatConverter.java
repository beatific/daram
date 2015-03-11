package org.beatific.daram.web.controller.converter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.sql.TIMESTAMP;

public class JstatConverter {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static List convertGraphByValueName(Map dataMap, String... valueNames) {

		if(dataMap == null) return null;
		
		List graph = new ArrayList();
		Long time = null;
		
		if(dataMap.get("jstatTime") instanceof TIMESTAMP)
			try {
				time = ((TIMESTAMP)dataMap.get("jstatTime")).dateValue().getTime();
			} catch (SQLException e) {}
		else throw new RuntimeException("Jstat Time convert Exception Type[" + dataMap.get("jstatTime").getClass().getName() + "]");
		
		graph.add(time);
		for(String valueName : valueNames)graph.add(dataMap.get(valueName));
		return graph;
	}
	
	private static List<List<?>> convertGraphByValueName(List<Map> dataMaps, String... valueNames) {
		
		List<List<?>> graphs = new ArrayList<List<?>>();
		
		for(Map dataMap : dataMaps) {
			graphs.add(convertGraphByValueName(dataMap, valueNames));
		}
		return graphs;
	}
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Map> convertGoogleLineCharts(List<Map> dataMaps) {
		List<Map> list = new ArrayList<Map>();
		
		Map eden = new HashMap();
		eden.put("graphs", convertGraphByValueName(dataMaps, "e"));
		eden.put("xTag", "Time");
		eden.put("yTag", "eden");
		eden.put("denomination", "%");
		eden.put("name", "Eden");
		list.add(eden);
		
		Map s0 = new HashMap();
		s0.put("graphs", convertGraphByValueName(dataMaps, "s0"));
		s0.put("xTag", "Time");
		s0.put("yTag", "s0");
		s0.put("denomination", "%");
		s0.put("name", "Survivor0");
		list.add(s0);
		
		Map s1 = new HashMap();
		s1.put("graphs", convertGraphByValueName(dataMaps, "s1"));
		s1.put("xTag", "Time");
		s1.put("yTag", "s1");
		s1.put("denomination", "%");
		s1.put("name", "Survivor1");
		list.add(s1);
		
		Map old = new HashMap();
		old.put("graphs", convertGraphByValueName(dataMaps, "o"));
		old.put("xTag", "Time");
		old.put("yTag", "old");
		old.put("denomination", "%");
		old.put("name", "Old");
		list.add(old);
		
//		Map perm = new HashMap();
//		perm.put("graphs", convertGraphByValueName(dataMaps, "p"));
//		perm.put("xTag", "Time");
//		perm.put("yTag", "perm");
//		perm.put("denomination", "Byte");
//		perm.put("name", "Permanent");
//		list.add(perm);
		
		Map gcc = new HashMap();
		gcc.put("graphs", convertGraphByValueName(dataMaps, "ygc", "fgc"));
		gcc.put("xTag", "Time");
		
		List<String> ygcTags = new ArrayList<String>();
		ygcTags.add("ygc");
		ygcTags.add("fgc");
		
		gcc.put("yTag", ygcTags);
		gcc.put("denomination", "");
		gcc.put("name", "GCC");
		list.add(gcc);
		
		Map gct = new HashMap();
		gct.put("graphs", convertGraphByValueName(dataMaps, "ygct", "fgct"));
		gct.put("xTag", "Time");
		
		List<String> ygctTags = new ArrayList<String>();
		ygctTags.add("ygct");
		ygctTags.add("fgct");
		
		gct.put("yTag", ygctTags);
		gct.put("denomination", "sec");
		gct.put("name", "GCT");
		list.add(gct);
		
		return list;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Map> convertGoogleLineCharts(Map dataMap) {
		List<Map> list = new ArrayList<Map>();
		
		Map eden = new HashMap();
		eden.put("graph", convertGraphByValueName(dataMap, "e"));
		eden.put("xTag", "Time");
		eden.put("yTag", "eden");
		eden.put("denomination", "%");
		eden.put("name", "Eden");
		list.add(eden);
		
		Map s0 = new HashMap();
		s0.put("graph", convertGraphByValueName(dataMap, "s0"));
		s0.put("xTag", "Time");
		s0.put("yTag", "s0");
		s0.put("denomination", "%");
		s0.put("name", "Survivor0");
		list.add(s0);
		
		Map s1 = new HashMap();
		s1.put("graph", convertGraphByValueName(dataMap, "s1"));
		s1.put("xTag", "Time");
		s1.put("yTag", "s1");
		s1.put("denomination", "%");
		s1.put("name", "Survivor1");
		list.add(s1);
		
		Map old = new HashMap();
		old.put("graph", convertGraphByValueName(dataMap, "o"));
		old.put("xTag", "Time");
		old.put("yTag", "old");
		old.put("denomination", "%");
		old.put("name", "Old");
		list.add(old);
		
//		Map perm = new HashMap();
//		perm.put("graph", convertGraphByValueName(dataMap, "p"));
//		perm.put("xTag", "Time");
//		perm.put("yTag", "perm");
//		perm.put("denomination", "Byte");
//		perm.put("name", "Permanent");
//		list.add(perm);
		
		Map gcc = new HashMap();
		gcc.put("graph", convertGraphByValueName(dataMap, "ygc", "fgc"));
		gcc.put("xTag", "Time");
		
		List<String> ygcTags = new ArrayList<String>();
		ygcTags.add("ygc");
		ygcTags.add("fgc");
		
		gcc.put("yTag", ygcTags);
		gcc.put("denomination", "");
		gcc.put("name", "GCC");
		list.add(gcc);
		
		Map gct = new HashMap();
		gct.put("graph", convertGraphByValueName(dataMap, "ygct", "fgct"));
		gct.put("xTag", "Time");
		
		List<String> ygctTags = new ArrayList<String>();
		ygctTags.add("ygct");
		ygctTags.add("fgct");
		
		gct.put("yTag", ygctTags);
		gct.put("denomination", "sec");
		gct.put("name", "GCT");
		list.add(gct);
		
		return list;
	}
}
