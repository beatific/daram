package org.beatific.daram.jstat.command;

import org.junit.Test;
import static org.junit.Assert.*;

public class ExecuteShellCommandTest {

	@Test
	public void testExecuteCommand() {
		
		ExecuteShellCommand execute = new ExecuteShellCommand();
		String[] vmids = execute.executeCommand("jps");
		String vmid = null;
		
		if("Jps".equals(vmids[0].split(" ")[1]))vmid = vmids[0].split(" ")[1];
		else vmid = vmids[0].split(" ")[0];
		String[] result = execute.executeCommand("jstat -gcutil " + vmid);
		
		assertTrue(result[0].equals("  S0     S1     E      O      P     YGC     YGCT    FGC    FGCT     GCT   "));
		
	}
	
}
