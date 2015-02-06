package org.beatific.daram.web.vo.dao;

public class DesignVo {

	private String designName;
	private String xTag;
	private String yTag;
	private String denomination;
	private String selected;
	private boolean isSelected;
	
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
		this.isSelected = "true".equals(selected)? true : false;
	}
	public Boolean getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}
	public String getDesignName() {
		return designName;
	}
	public void setDesignName(String designName) {
		this.designName = designName;
	}
	public String getxTag() {
		return xTag;
	}
	public void setxTag(String xTag) {
		this.xTag = xTag;
	}
	public String getyTag() {
		return yTag;
	}
	public void setyTag(String yTag) {
		this.yTag = yTag;
	}
	public String getDenomination() {
		return denomination;
	}
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}
	
	
	@Override
	public String toString() {
		return "DesignVo [designName=" + designName + ", xTag=" + xTag
				+ ", yTag=" + yTag + ", denomination=" + denomination + "]";
	}
	
}
