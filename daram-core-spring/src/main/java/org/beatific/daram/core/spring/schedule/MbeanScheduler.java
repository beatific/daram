package org.beatific.daram.core.spring.schedule;

import org.beatific.daram.mbean.MBeanManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class MbeanScheduler {

	@Scheduled(fixedDelay = 60000)
	public void run() {
		MBeanManager.reload();
	}
	
	@Scheduled(fixedDelay = 60000)
	public void recordJstat() {
		
		MBeanManager.extractJstat();
	}
	
//	@Scheduled(cron = "")
//	public void deleteData() {
//		
//		MBeanManager.extractJstat();
//	}

}
