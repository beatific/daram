package org.beatific.daram.mbean.stuck.check;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.beatific.daram.mbean.stuck.MonitorThread;
import org.beatific.daram.mbean.stuck.ThreadState;
import org.beatific.ddirori.utils.TraceUtils;

public abstract class ThreadStateResolver {

	private Condition condition = new Condition();

	protected abstract boolean condition(ThreadState state);

	protected abstract void actionWhenCondition(List<MonitorThread> threads,
			MonitorThread thread, long time);

	public ThreadStateResolver when(boolean condition) {
		if (this.condition.isAllFalse()) {
			this.condition.condition(condition);
		}

		return this;
	}

	public ThreadStateResolver then(StateConverter converter) {
		if (this.condition.isCondition())
			converter.convert();
		return this;
	}

	public ThreadStateResolver other(StateConverter converter) {
		if (this.condition.isAllFalse())
			converter.convert();
		return this;
	}

	public ThreadStateResolver end() {
		this.condition.clear();
		return this;
	}

	public void resolve(List<MonitorThread> threads, MonitorThread thread) {
		if (condition(thread.getState())) {

			actionWhenCondition(threads, thread,
					new Date().getTime() - thread.getTime());
		}
	}
	
	protected void printStackTrace(MonitorThread mthread) {
		Thread thread = mthread.getThread();
		TraceUtils.printStackTrace(thread.getStackTrace());
	}

	private class Condition {

		private ThreadLocal<List<Boolean>> conditions = new ThreadLocal<List<Boolean>>() {
			public List<Boolean> initialValue() {
				return new ArrayList<Boolean>();
			}
		}; 

		public boolean isAllFalse() {
			return !conditions.get().contains(Boolean.TRUE);
		}

		public boolean isCondition() {
			return conditions.get().get(conditions.get().size() - 1);
		}

		public Condition condition(boolean condition) {
			conditions.get().add(new Boolean(condition));
			return this;
		}

		public void clear() {
			conditions.get().clear();
		}
		
	}

}
