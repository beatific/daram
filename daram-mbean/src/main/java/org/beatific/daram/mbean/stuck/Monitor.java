package org.beatific.daram.mbean.stuck;

public interface Monitor {

	public int getHoggingThreadsCount();
	public int getStuckThreadsCount();
	public int getRunningThreadCount();
	public int getThreadCount();
	
}
