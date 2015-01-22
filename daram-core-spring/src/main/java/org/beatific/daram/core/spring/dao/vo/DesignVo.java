package org.beatific.daram.core.spring.dao.vo;

public class DesignVo {

	private String designName;
	private String xTag;
	private String yTag;
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
	@Override
	public String toString() {
		return "DesignVo [designName=" + designName + ", xTag=" + xTag
				+ ", yTag=" + yTag + "]";
	}
	
}
