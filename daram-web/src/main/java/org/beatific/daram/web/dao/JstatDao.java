package org.beatific.daram.web.dao;

import java.util.List;
import java.util.Map;

import org.beatific.daram.web.common.DaoRepository;
import org.beatific.daram.web.vo.dao.MonitorGraphVo;

@DaoRepository
public interface JstatDao {

	public List<Map> listJstatGraphByServer(MonitorGraphVo vo);

}
