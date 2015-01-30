package org.beatific.daram.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.beatific.daram.web.dao.DesignDao;
import org.beatific.daram.web.service.DesignService;
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
	
    public List<DesignVo> listDesign(DesignVo vo) {
		
		return dao.listDesign(vo);
	}
}
