package org.beatific.daram.core.spring.dao;

import org.beatific.daram.core.spring.dao.vo.JstatVo;

@DaoRepository
public interface JstatDao {

	public void insertJstat(JstatVo vo);
}
