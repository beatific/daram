package org.beatific.daram.jstat.parse;

import java.util.ArrayList;
import java.util.List;

import org.beatific.daram.jstat.JstatResult;

public class ResultParser {

	public JstatResult parse(String string) {
		
		String[] strings = splitResult(string);
		
		JstatResult result = new JstatResult();
		result.setS0(strings[0]);
		result.setS1(strings[1]);
		result.setE(strings[2]);
		result.setO(strings[3]);
		result.setP(strings[4]);
		result.setYgc(strings[5]);
		result.setYgct(strings[6]);
		result.setFgc(strings[7]);
		result.setFgct(strings[8]);
		result.setGct(strings[9]);
		
		return result;
		
		
	}
	
	private String[] splitResult(String result) {
		
		List<String> strs = new ArrayList<String>();
		
		String[] strings = result.split(" ");
		for(String string : strings) {
			if("".equals(string))continue;
			strs.add(string);
		}
		
		return strs.toArray(new String[0]); 
	}
}
