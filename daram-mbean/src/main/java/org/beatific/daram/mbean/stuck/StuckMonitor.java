package org.beatific.daram.mbean.stuck;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.beatific.daram.mbean.core.MBean;
import org.beatific.daram.mbean.stuck.check.StateConverter;
import org.beatific.daram.mbean.stuck.check.ThreadChecker;
import org.beatific.daram.mbean.stuck.check.ThreadStateResolver;
import org.beatific.ddirori.xml.FileReader;

@MBean(objectName = "org.beatific.daram:type=ThreadStuck")
public class StuckMonitor implements Monitor {

	private static final String STUCK_FILE_PATH = "stuck.properties";
	private ThreadHolder holder;

	public StuckMonitor() {
		
		Properties props = new Properties();
		
		long hoggingTime = 60000;
		long stuckTime = 600000;
		
		try {
			props.load(FileReader.getInputStream(STUCK_FILE_PATH));
			hoggingTime = Long.parseLong(props.getProperty("hoggingTime"));
			stuckTime = Long.parseLong(props.getProperty("stuckTime"));
		} catch (Exception e) {}
		
		holder = new ThreadHolder(hoggingTime, stuckTime);
		
	}

	public void register(Thread thread) {
		holder.registerThread(thread);
	}

	public int getHoggingThreadsCount() {
		return holder.getHoggingCount();
	}

	public int getStuckThreadsCount() {
		return holder.getStuckCount();
	}

	public int getRunningThreadCount() {
		return holder.getRunningCount();
	}
	
	public int getThreadCount() {
		return holder.getRunningCount() + holder.getHoggingCount() + holder.getStuckCount();
	}

	private class ThreadHolder {

		private List<MonitorThread> stucks = new ArrayList<MonitorThread>();
		private List<MonitorThread> hoggings = new ArrayList<MonitorThread>();
		private List<MonitorThread> runnings = new ArrayList<MonitorThread>();
		private ThreadChecker checker;

		public ThreadHolder(final long hoggingTime, final long stuckTime) {
			
			checker = new ThreadChecker().register(new ThreadStateResolver() {

				@Override
				protected boolean condition(ThreadState state) {
					return state.equals(ThreadState.RUNNING);
				}

				@Override
				protected void actionWhenCondition(final List<MonitorThread> threads,
						final MonitorThread thread, long time) {
					
					when(time > stuckTime)
				   .then(new StateConverter() {

						@Override
						public void convert() {
							stucks.add(thread);
							threads.remove(thread);
						}
						
					})
					
				   .when(time > hoggingTime)
				   .then(new StateConverter() {

						@Override
						public void convert() {
							hoggings.add(thread);
							threads.remove(thread);
						}
						
					})
					
				   .end();
					
				}
				
			}).register(new ThreadStateResolver() {

				@Override
				protected boolean condition(ThreadState state) {
					return state.equals(ThreadState.WAITING);
				}

				@Override
				protected void actionWhenCondition(final List<MonitorThread> threads,
						final MonitorThread thread, long time) {
					
					threads.remove(thread);
					
				}
				
			});
		}

		public synchronized int getRunningCount() {

			check();
			return runnings.size();
		}

		public synchronized int getStuckCount() {
			
			check();
			return stucks.size();
		}

		public synchronized int getHoggingCount() {
			
			check();
			return hoggings.size();
		}

		public synchronized void registerThread(Thread thread) {
			runnings.add(new MonitorThread(thread));
		}

		private void check(List<MonitorThread> threads) {
			
			for (MonitorThread thread : threads) {
				
				checker.check(threads, thread);
			}
		}
		
		private void check() {

			check(stucks);
			check(hoggings);
			check(runnings);
		}
	}
}
