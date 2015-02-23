package org.beatific.daram.web.dao;

import java.util.List;
import java.util.Map;

import org.beatific.daram.web.common.DaoRepository;
import org.beatific.daram.web.vo.dao.JstatVo;

@DaoRepository
public interface JstatDao {

	public List<Map> listJstatGraphByServer(JstatVo vo);

	public Map selectJstatGraphByServer(JstatVo vo);
	
	public List<Map> listServer(JstatVo vo);
	
	public void updateServer(JstatVo vo);

}
