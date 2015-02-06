package org.beatific.daram.web.vo.dao;

import java.util.List;
import java.util.Map;

public class DesignPerDashBoardVo {

	private Long dashBoardId;
	private String designName;
	private Integer sort;
	private List<Map<String, String>> designs;
	
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Long getDashBoardId() {
		return dashBoardId;
	}
	public void setDashBoardId(Long dashBoardId) {
		this.dashBoardId = dashBoardId;
	}
	public String getDesignName() {
		return designName;
	}
	public void setDesignName(String designName) {
		this.designName = designName;
	}
	public List<Map<String, String>> getDesigns() {
		return designs;
	}
	public void setDesigns(List<Map<String, String>> designs) {
		this.designs = designs;
	}
}
