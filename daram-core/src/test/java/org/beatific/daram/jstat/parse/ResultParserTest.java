package org.beatific.daram.jstat.parse;

import static org.junit.Assert.assertEquals;

import org.beatific.daram.jstat.JstatResult;
import org.junit.Test;

public class ResultParserTest {

	@Test
	public void testParse() {
		ResultParser parser = new ResultParser();
		JstatResult result = parser.parse("  0.00   0.00  98.76   0.00   6.08      0    0.000     0    0.000    0.000");
		result.setServer("server");
		assertEquals("JstatResult [server=server, s0=0.00, s1=0.00, e=98.76, o=0.00, p=6.08, ygc=0, ygct=0.000, fgc=0, fgct=0.000, gct=0.000]", result.toString());
		
	}
}
