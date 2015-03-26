package org.beatific.daram.core.spring.repository;

import org.beatific.daram.core.spring.dao.DesignDao;
import org.beatific.daram.core.spring.dao.vo.DesignVo;
import org.beatific.daram.core.spring.dao.vo.MonitorDesignVo;
import org.beatific.daram.core.spring.dao.vo.MonitorGraphVo;
import org.beatific.daram.core.spring.dao.vo.MonitorVo;
import org.beatific.daram.design.Design;
import org.beatific.daram.design.Graph;
import org.beatific.ddirori.context.annotation.DDirori;
import org.beatific.ddirori.repository.OneStateRepository;
import org.beatific.ddirori.repository.Store;
import org.springframework.util.Assert;

@Store
public class DesignRepository extends OneStateRepository<Design> {

	@DDirori(name="designDao")
	private DesignDao dao;
	
	private Design getDesign(Object object) {
		
        Design design = null;
		
		Assert.notNull(object);
		if(object instanceof Design) design = (Design)object;
		else throw new RuntimeException("Type Cast Exception : source[" + object.getClass().getName() + "], destination[" + Design.class.getName() + "]");
		
		return design;
	}
	
	@Override
	public void save(Object object) {
		
		if(dao == null)return;
		
		Design design = getDesign(object);
		
		DesignVo designVo = new DesignVo();
		designVo.setDesignName(design.getName());
		designVo.setxTag(design.getxTag());
		designVo.setyTag(design.getyTag());
		if(dao.selectDesign(designVo) != null) return;
		dao.insertDesign(designVo);
	}

	@Override
	public Design load(Object object) {
		return null;
	}

	@Override
	public void change(Object object) {
		
		Design design = getDesign(object);
		
		Long monitorId = design.getMonitorId();
		if(monitorId == null) {
			monitorId = dao.selectMonitorPk();
			MonitorVo monitorVo = new MonitorVo();
			monitorVo.setMonitorId(monitorId);
			dao.insertMonitor(monitorVo);
		}
		
		design.setMonitorId(monitorId);
		
		MonitorDesignVo monitorDesignVo = new MonitorDesignVo();
		monitorDesignVo.setMonitorId(monitorId);
		monitorDesignVo.setDesignName(design.getName());
		monitorDesignVo.setCaption(design.getCaption());
		dao.insertMonitorDesign(monitorDesignVo);
		
		MonitorGraphVo monitorGraphVo = new MonitorGraphVo();
		monitorGraphVo.setMonitorId(monitorId);
		monitorGraphVo.setDesignName(design.getName());
		for(Graph graph : design.getGraphs()) {
			monitorGraphVo.setGraphName(graph.getName());
			monitorGraphVo.setyValue(graph.getY());
			dao.insertMonitorGraph(monitorGraphVo);
		}
		
	}

	@Override
	public void remove(Object object) {
	}

}
