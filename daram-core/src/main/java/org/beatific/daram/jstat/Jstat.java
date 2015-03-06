package org.beatific.daram.jstat;

import org.beatific.daram.jstat.command.ExecuteShellCommand;
import org.beatific.daram.jstat.parse.ResultParser;
import org.beatific.ddirori.context.ApplicationContextUtils;
import org.beatific.ddirori.repository.RepositoryStore;

public class Jstat {
	
	private final RepositoryStore store = ApplicationContextUtils.getApplicationContext().getStore();

	public void execute(String server, String vmid) throws ArrayIndexOutOfBoundsException {
		
		if(vmid == null)return;
		ExecuteShellCommand executor = new ExecuteShellCommand();
		StringBuffer command = new StringBuffer();
		command.append("jstat -gcutil ").append(vmid);
		String[] results = executor.executeCommand(command.toString());
		
		ResultParser parser = new ResultParser();
		try {
			JstatResult result = parser.parse(results[1]);
			
			result.setServer(server);
			store.save(result);
		} catch(ArrayIndexOutOfBoundsException ex) {
			throw ex;
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}
