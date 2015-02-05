package org.beatific.daram.web.controller.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beatific.daram.web.vo.dao.DesignVo;
import org.beatific.daram.web.vo.dao.MonitorGraphVo;

public class DesignConverter {

//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public static List<Map> convertNvdLineCharts(List<MonitorGraphVo> monitorGraphVos) {
//		List<Map> nvdLineCharts = new ArrayList<Map>();
//		Map nvdLineChart = new HashMap();
//		nvdLineChart.put("key", monitorGraphVos.get(0).getGraphName());
//		nvdLineChart.put("values", convertGraph(monitorGraphVos));
//		nvdLineCharts.add(nvdLineChart);
//		return nvdLineCharts;
//	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static List<List<?>> convertGraph(List<MonitorGraphVo> monitorGraphVos) {
		
		List<List<?>> graphs = new ArrayList<List<?>>();
		for(MonitorGraphVo monitorGraphVo : monitorGraphVos) {
			List graph = new ArrayList();
			graph.add(monitorGraphVo.getxValue());
			graph.add(monitorGraphVo.getyValue());
			graphs.add(graph);
		}
		return graphs;
	}
	
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
