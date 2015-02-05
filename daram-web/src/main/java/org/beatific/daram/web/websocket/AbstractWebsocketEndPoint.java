package org.beatific.daram.web.websocket;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public abstract class AbstractWebsocketEndPoint extends TextWebSocketHandler implements Job {
	public final void execute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			BeanWrapper bw = PropertyAccessorFactory
					.forBeanPropertyAccess(this);
			MutablePropertyValues pvs = new MutablePropertyValues();
			pvs.addPropertyValues(context.getScheduler().getContext());
			pvs.addPropertyValues(context.getMergedJobDataMap());
			bw.setPropertyValues(pvs, true);
		} catch (SchedulerException ex) {
			throw new JobExecutionException(ex);
		}
		executeInternal(context);
	}

	protected abstract void executeInternal(
			JobExecutionContext paramJobExecutionContext)
			throws JobExecutionException;
}
