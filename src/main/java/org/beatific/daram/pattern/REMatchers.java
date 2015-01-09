package org.beatific.daram.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class REMatchers {

	private final static String REGEX_INNER_BRACES = "\\$\\{([^\\{\\}]*)\\}";
	private final static String REGEX_METHOD_ARGS = "([^\\{\\}]*)\\.([^\\{\\}]*)\\(([^\\{\\}]*)\\)";
	private final static String REGEX_NUMERIC_TYPE = "\\d";
	private final static String REGEX_DECIMAL_TYPE = "\\d+\\.\\d+";
	private final static String REGEX_LETTER_TYPE = "\\'(\\w*)\\'";
	private final static String REGEX_COMMA = "\\w+\\,\\w+";
	private final static Pattern PATTERN_INNER_BRACES = Pattern.compile(REGEX_INNER_BRACES);
	private final static Pattern PATTERN_METHOD_ARGS = Pattern.compile(REGEX_METHOD_ARGS);
	private final static Pattern PATTERN_NUMERIC_TYPE = Pattern.compile(REGEX_NUMERIC_TYPE);
	private final static Pattern PATTERN_LETTER_TYPE = Pattern.compile(REGEX_LETTER_TYPE);
	private final static Pattern PATTERN_DECIMAL_TYPE = Pattern.compile(REGEX_DECIMAL_TYPE);
	private final static Pattern PATTERN_COMMA = Pattern.compile(REGEX_COMMA);

	public static Matcher innerBracesMatcher(String str) {
		return PATTERN_INNER_BRACES.matcher(str);
	}
	
	public static Matcher methodMatcher(String str) {
		return PATTERN_METHOD_ARGS.matcher(str);
	}
	
	public static Matcher numericMatcher(String str) {
		return PATTERN_NUMERIC_TYPE.matcher(str);
	}
	
	public static Matcher letterMatcher(String str) {
		return PATTERN_LETTER_TYPE.matcher(str);
	}
	
	public static Matcher decimalMatcher(String str) {
		return PATTERN_DECIMAL_TYPE.matcher(str);
	}
	
	public static Matcher commaMatcher(String str) {
		return PATTERN_COMMA.matcher(str);
	}
}
