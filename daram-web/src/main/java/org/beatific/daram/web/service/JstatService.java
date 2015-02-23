package org.beatific.daram.web.service;

import java.util.List;
import java.util.Map;

import org.beatific.daram.web.vo.dao.JstatVo;

public interface JstatService {

	public List<Map> listJstatGraphByServer(JstatVo vo);

	public Map selectJstatGraph(JstatVo vo);

	public List<Map> listServer(JstatVo vo);
	
	public void updateServer(JstatVo vo);
}
