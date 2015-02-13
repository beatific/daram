package org.beatific.daram.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.beatific.daram.web.dao.JstatDao;
import org.beatific.daram.web.service.JstatService;
import org.beatific.daram.web.vo.dao.MonitorGraphVo;
import org.springframework.stereotype.Service;

@Service(value="jstatService")
public class JstatServiceImpl implements JstatService {

	@Resource(name="jstatDao")
	private JstatDao dao;
	
	@Override
	public List<Map> listJstatGraphByServer(MonitorGraphVo vo) {
		return dao.listJstatGraphByServer(vo);
	}

}
