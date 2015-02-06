package org.beatific.daram.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.beatific.daram.web.dao.DesignDao;
import org.beatific.daram.web.service.DesignService;
import org.beatific.daram.web.vo.dao.DashBoardVo;
import org.beatific.daram.web.vo.dao.DesignPerDashBoardVo;
import org.beatific.daram.web.vo.dao.DesignVo;
import org.beatific.daram.web.vo.dao.MonitorGraphVo;
import org.springframework.stereotype.Service;

@Service(value="designService")
public class DesignServiceImpl implements DesignService {

	@Resource(name="designDao")
	private DesignDao dao;
	
	public List<MonitorGraphVo> listMonitorGraphByDesign(MonitorGraphVo vo) {
		
		return dao.listMonitorGraphByDesign(vo);
	}
	
    public MonitorGraphVo selectMonitorGraphByDesign(MonitorGraphVo vo) {
		
		return dao.selectMonitorGraphByDesign(vo);
	}
	
    public List<DesignVo> listDesign(DashBoardVo vo) {
		
		return dao.listDesign(vo);
	}
    
    public void updateDesignPerDashBoard(DesignPerDashBoardVo vo) {
    	
    	DesignPerDashBoardVo dashboardVo = new DesignPerDashBoardVo();
		dashboardVo.setDashBoardId(new Long(1));
		dao.deleteDesignPerDashBoard(dashboardVo);
		
		int i = 0;
		for(Map<String, String> design : vo.getDesigns()) {
			dashboardVo.setDesignName(design.get("designName"));
			dashboardVo.setSort(i++);
			dao.insertDesignPerDashBoard(dashboardVo);
		}
    }
    
    public List<DesignVo> listDesignByDashBoard(DashBoardVo vo) {
    	return dao.listDesignByDashBoard(vo);
    }
    
}
