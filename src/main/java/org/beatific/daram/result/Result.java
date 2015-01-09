package org.beatific.daram.result;

import java.util.List;

public class Result {

	private List<DesignResult> designs;

	public List<DesignResult> getDesigns() {
		return designs;
	}

	public void setDesigns(List<DesignResult> designs) {
		this.designs = designs;
	}

	@Override
	public String toString() {
		return "Result [designs=" + designs + "]";
	}
	
}
