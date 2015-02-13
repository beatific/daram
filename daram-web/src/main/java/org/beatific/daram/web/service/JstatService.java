package org.beatific.daram.web.service;

import java.util.List;
import java.util.Map;

import org.beatific.daram.web.vo.dao.MonitorGraphVo;

public interface JstatService {

	public List<Map> listJstatGraphByServer(MonitorGraphVo vo);

}
