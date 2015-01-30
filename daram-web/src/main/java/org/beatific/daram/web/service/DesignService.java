package org.beatific.daram.web.service;

import java.util.List;

import org.beatific.daram.web.vo.dao.DesignVo;
import org.beatific.daram.web.vo.dao.MonitorGraphVo;

public interface DesignService {

	public List<MonitorGraphVo> listMonitorGraphByDesign(MonitorGraphVo vo);
	
	public List<DesignVo> listDesign(DesignVo vo);
}
