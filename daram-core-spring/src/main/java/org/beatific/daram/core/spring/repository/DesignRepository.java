package org.beatific.daram.core.spring.repository;

import org.beatific.daram.core.spring.dao.DesignDao;
import org.beatific.daram.core.spring.dao.vo.DesignVo;
import org.beatific.daram.core.spring.dao.vo.MonitorDesignVo;
import org.beatific.daram.core.spring.dao.vo.MonitorGraphVo;
import org.beatific.daram.core.spring.dao.vo.MonitorVo;
import org.beatific.daram.design.Design;
import org.beatific.daram.design.Graph;
import org.beatific.ddirori.context.ApplicationContextUtils;
import org.beatific.ddirori.repository.OneStateRepository;
import org.beatific.ddirori.repository.Store;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Store
public class DesignRepository extends OneStateRepository<Design> {

	private DesignDao dao = null;
	
	private DesignDao getDao() {
		if(dao == null) {
			WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ApplicationContextUtils.getServletContext());
			dao = (DesignDao)wac.getBean("designDao");
		}
		return dao;
	}
	
	private Design getDesign(Object object) {
		
        Design design = null;
		
		Assert.notNull(object);
		if(object instanceof Design) design = (Design)object;
		else throw new RuntimeException("Type Cast Exception : source[" + object.getClass().getName() + "], destination[" + Design.class.getName());
		
		return design;
	}
	
	@Override
	public void save(Object object) {
		
		Design design = getDesign(object);
		DesignVo designVo = new DesignVo();
		designVo.setDesignName(design.getName());
		designVo.setxTag(design.getxTag());
		designVo.setyTag(design.getyTag());
		if(getDao().selectDesign(designVo) != null) return;
		getDao().insertDesign(designVo);
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
			monitorId = getDao().selectMonitorPk();
			MonitorVo monitorVo = new MonitorVo();
			monitorVo.setMonitorId(monitorId);
			getDao().insertMonitor(monitorVo);
		}
		
		design.setMonitorId(monitorId);
		
		MonitorDesignVo monitorDesignVo = new MonitorDesignVo();
		monitorDesignVo.setMonitorId(monitorId);
		monitorDesignVo.setDesignName(design.getName());
		monitorDesignVo.setCaption(design.getCaption());
		getDao().insertMonitorDesign(monitorDesignVo);
		
		MonitorGraphVo monitorGraphVo = new MonitorGraphVo();
		monitorGraphVo.setMonitorId(monitorId);
		monitorGraphVo.setDesignName(design.getName());
		for(Graph graph : design.getGraphs()) {
			monitorGraphVo.setGraphName(graph.getName());
			monitorGraphVo.setyValue(graph.getY());
			getDao().insertMonitorGraph(monitorGraphVo);
		}
		
	}

	@Override
	public void remove(Object object) {
	}

}