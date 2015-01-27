package org.beatific.daram.core.spring.dao;

import org.beatific.daram.core.spring.dao.vo.DesignVo;
import org.beatific.daram.core.spring.dao.vo.MonitorDesignVo;
import org.beatific.daram.core.spring.dao.vo.MonitorGraphVo;
import org.beatific.daram.core.spring.dao.vo.MonitorVo;

@DaoRepository
public interface DesignDao {

	public void insertDesign(DesignVo vo);
	public Long selectMonitorPk();
	public void insertMonitor(MonitorVo vo);
	public void insertMonitorDesign(MonitorDesignVo vo);
	public void insertMonitorGraph(MonitorGraphVo vo);
	public DesignVo selectDesign(DesignVo designVo);
}
