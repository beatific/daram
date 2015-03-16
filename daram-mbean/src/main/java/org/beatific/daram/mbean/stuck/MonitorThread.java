package org.beatific.daram.mbean.stuck;

import java.util.Date;

public class MonitorThread {

	private Thread thread;
	private final long time;
	
	public MonitorThread(Thread thread) {
		this.thread = thread;
		this.time = new Date().getTime();
	}
	
	public long getTime() {
		return time;
	}

	public Thread getThread() {
		return thread;
	}
	public void setThread(Thread thread) {
		this.thread = thread;
	}
	
	public ThreadState getState() {
		
	    switch(thread.getState()) {
	    case RUNNABLE :
	    case TIMED_WAITING :
	    case BLOCKED : return ThreadState.RUNNING;
	    default : return ThreadState.WAITING;
	    }
	}
	
}
