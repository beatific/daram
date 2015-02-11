package org.beatific.daram.jstat.command;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ExecuteShellCommand {
	 
	public String[] executeCommand(String command) {
 
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
 
		return output.toArray(new String[0]);
 
	}
 
}