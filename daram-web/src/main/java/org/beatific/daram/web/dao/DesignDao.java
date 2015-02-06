package org.beatific.daram.web.dao;

import java.util.List;

import org.beatific.daram.web.common.DaoRepository;
import org.beatific.daram.web.vo.dao.DashBoardVo;
import org.beatific.daram.web.vo.dao.DesignPerDashBoardVo;
import org.beatific.daram.web.vo.dao.DesignVo;
import org.beatific.daram.web.vo.dao.MonitorGraphVo;

@DaoRepository
public interface DesignDao {

	public List<MonitorGraphVo> listMonitorGraphByDesign(MonitorGraphVo vo);
	public MonitorGraphVo selectMonitorGraphByDesign(MonitorGraphVo vo);
	public List<DesignVo> listDesign(DashBoardVo vo);
	public void insertDesignPerDashBoard(DesignPerDashBoardVo vo);
	public void deleteDesignPerDashBoard(DesignPerDashBoardVo vo);
	public List<DesignVo> listDesignByDashBoard(DashBoardVo vo);
}
