package org.beatific.daram.web.service;

import java.util.List;

import org.beatific.daram.web.vo.dao.DashBoardVo;
import org.beatific.daram.web.vo.dao.DesignPerDashBoardVo;
import org.beatific.daram.web.vo.dao.DesignVo;
import org.beatific.daram.web.vo.dao.MonitorGraphVo;

public interface DesignService {

	public MonitorGraphVo selectMonitorGraphByDesign(MonitorGraphVo vo);
	
	public List<MonitorGraphVo> listMonitorGraphByDesign(MonitorGraphVo vo);
	
	public List<DesignVo> listDesign(DashBoardVo vo);
	
	public void updateDesignPerDashBoard(DesignPerDashBoardVo vo);
	
	public List<DesignVo> listDesignByDashBoard(DashBoardVo vo);
}
