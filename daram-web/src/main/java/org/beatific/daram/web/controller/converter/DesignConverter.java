package org.beatific.daram.web.controller.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beatific.daram.web.vo.dao.DesignVo;
import org.beatific.daram.web.vo.dao.MonitorGraphVo;

public class DesignConverter {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static List<?> convertGraph(MonitorGraphVo monitorGraphVo) {
		List graph = new ArrayList();
		graph.add(monitorGraphVo.getxValue());
		graph.add(monitorGraphVo.getyValue());
		return graph;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map convertGoogleLineChart(MonitorGraphVo monitorGraphVo) {
		Map map = new HashMap();
		map.put("graph", convertGraph(monitorGraphVo));
		map.put("name", monitorGraphVo.getDesignName());
		return map;
		
	}
	
	private static List<List<?>> convertGraph(List<MonitorGraphVo> monitorGraphVos) {
		
		List<List<?>> graphs = new ArrayList<List<?>>();
		for(MonitorGraphVo monitorGraphVo : monitorGraphVos) {
			graphs.add(convertGraph(monitorGraphVo));
		}
		return graphs;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map convertGoogleLineCharts(DesignVo design, List<MonitorGraphVo> monitorGraphVos) {
		Map map = new HashMap();
		map.put("graphs", convertGraph(monitorGraphVos));
		map.put("xTag", design.getxTag());
		map.put("yTag", design.getyTag());
		map.put("denomination", design.getDenomination());
		map.put("name", design.getDesignName());
		return map;
	}
	
}
