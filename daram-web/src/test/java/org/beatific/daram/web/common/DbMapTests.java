package org.beatific.daram.web.common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DbMapTests {

	@Test
	public void testPut() {
		DbMap map = new DbMap();
		map.put("JSTAT_TIME", 1234565);
		assertEquals("{jstatTime=1234565}", map.toString());
	}
}
