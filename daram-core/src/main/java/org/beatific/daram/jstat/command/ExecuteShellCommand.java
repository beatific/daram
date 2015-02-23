package org.beatific.daram.jstat.command;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ExecuteShellCommand {
	 
	private final Log logger = LogFactory.getLog(getClass());
	
	public String[] executeCommand(String command) {
 
		logger.debug("Execute Command : " + command);  
		List<String> output = new ArrayList<String>();
 
		ProcessBuilder pb = new ProcessBuilder(command.split(" "));
		try {
		    Process p = pb.start();
			p.waitFor();
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));
 
                        String line = "";			
			while ((line = reader.readLine())!= null) {
				output.add(line);
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.debug("Execute Output : " + output);
 
		return output.toArray(new String[0]);
 
	}
 
}