package org.beatific.daram.web.dao;

import java.util.List;

import org.beatific.daram.web.common.DaoRepository;
import org.beatific.daram.web.vo.dao.DesignVo;
import org.beatific.daram.web.vo.dao.MonitorGraphVo;

@DaoRepository
public interface DesignDao {

	public List<MonitorGraphVo> listMonitorGraphByDesign(MonitorGraphVo vo);
	public List<DesignVo> listDesign(DesignVo vo);
}
