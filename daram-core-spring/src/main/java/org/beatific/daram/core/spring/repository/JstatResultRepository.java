package org.beatific.daram.core.spring.repository;

import org.beatific.daram.core.spring.dao.JstatDao;
import org.beatific.daram.core.spring.dao.vo.JstatVo;
import org.beatific.daram.jstat.JstatResult;
import org.beatific.ddirori.context.ApplicationContextUtils;
import org.beatific.ddirori.repository.OneStateRepository;
import org.beatific.ddirori.repository.Store;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Store
public class JstatResultRepository extends OneStateRepository<JstatResult> {

	private JstatDao dao = null;

	private JstatDao getDao() {
		if (dao == null) {
			WebApplicationContext wac = WebApplicationContextUtils
					.getRequiredWebApplicationContext(ApplicationContextUtils
							.getServletContext());
			dao = (JstatDao) wac.getBean("jstatDao");
		}
		return dao;
	}

	private JstatResult getJstatResult(Object object) {

		JstatResult jstat = null;

		Assert.notNull(object);
		if (object instanceof JstatResult)
			jstat = (JstatResult) object;
		else
			throw new RuntimeException("Type Cast Exception : source["
					+ object.getClass().getName() + "], destination["
					+ JstatResult.class.getName());

		return jstat;
	}

	@Override
	public void save(Object object) {

		JstatResult jstat = getJstatResult(object);
		
		JstatVo vo = new JstatVo();
		vo.setServer(jstat.getServer());
		vo.setE(Double.parseDouble(jstat.getE()));
		vo.setFgc(Double.parseDouble(jstat.getFgc()));
		vo.setFgct(Double.parseDouble(jstat.getFgct()));
		vo.setGct(Double.parseDouble(jstat.getGct()));
		vo.setO(Double.parseDouble(jstat.getO()));
		vo.setP(Double.parseDouble(jstat.getP()));
		vo.setS0(Double.parseDouble(jstat.getS0()));
		vo.setS1(Double.parseDouble(jstat.getS1()));
		vo.setYgc(Double.parseDouble(jstat.getYgc()));
		vo.setYgct(Double.parseDouble(jstat.getYgct()));
		
		if(getDao().selectServer(vo) == null)getDao().insertServer(vo);
		
		getDao().insertJstat(vo);
	}

	@Override
	public JstatResult load(Object object) {
		return null;
	}

	@Override
	public void change(Object object) {

	}

	@Override
	public void remove(Object object) {
	}

}
