package org.beatific.daram.jstat;

import org.beatific.daram.jstat.command.ExecuteShellCommand;
import org.beatific.daram.jstat.parse.ResultParser;
import org.beatific.ddirori.context.ApplicationContextUtils;
import org.beatific.ddirori.repository.RepositoryStore;

public class Jstat {
	
	//private final RepositoryStore store = ApplicationContextUtils.getApplicationContext().getStore();

	public void execute(String server, String vmid) {
		
		System.out.println("Jstat execute : " + vmid);
		ExecuteShellCommand executor = new ExecuteShellCommand();
		StringBuffer command = new StringBuffer();
		command.append("jstat -gcutil ").append(vmid);
		System.out.println("command" + command);
		String[] results = executor.executeCommand(command.toString());
		
		ResultParser parser = new ResultParser();
		JstatResult result = parser.parse(results[1]);
		
		result.setServer(server);
		System.out.println("Result" + result);
		//store.save(result);
	}
	
}
