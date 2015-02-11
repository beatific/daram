package org.beatific.daram.core.spring.schedule;

import org.beatific.daram.mbean.MBeanManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MbeanScheduler {

	@Scheduled(fixedDelay = 60000)
	public void run() {
		
		MBeanManager.reload();
	}
	
	@Scheduled(fixedDelay = 60000)
	public void recordJstat() {
		
		MBeanManager.extractJstat();
	}

}
