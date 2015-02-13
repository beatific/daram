package org.beatific.daram.web.common;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DbMap<K extends String, V> extends HashMap<K, V> {

	private final static String REGEX_ORACLE_CASE = "([A-Z][A-Z0-9]*)";
	
	@SuppressWarnings("unchecked")
	@Override
	public V put(K key, V value) {
		
		Matcher matcher = Pattern.compile(REGEX_ORACLE_CASE).matcher((String)key);
		
		StringBuffer sb = new StringBuffer();
		
		if(matcher.find()) {
			sb.append(matcher.group(1).toLowerCase());
			while(matcher.find()) {
				sb.append(matcher.group(1).substring(0, 1).toUpperCase());
				sb.append(matcher.group(1).substring(1).toLowerCase());
			}
		}
		
		return super.put((K)sb.toString(), value);
	}

}
