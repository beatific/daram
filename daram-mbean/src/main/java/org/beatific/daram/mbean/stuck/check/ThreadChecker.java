package org.beatific.daram.mbean.stuck.check;

import java.util.ArrayList;
import java.util.List;

import org.beatific.daram.mbean.stuck.MonitorThread;

public class ThreadChecker {

	public List<ThreadStateResolver> resolvers = new ArrayList<ThreadStateResolver>();
	
	public ThreadChecker register(ThreadStateResolver resolver) {
		resolvers.add(resolver);
		return this;
	}
	
	public void check(List<MonitorThread> threads, MonitorThread thread) {
		
		for(ThreadStateResolver resolver : resolvers) {
			resolver.resolve(threads, thread);
		}
	}
}
