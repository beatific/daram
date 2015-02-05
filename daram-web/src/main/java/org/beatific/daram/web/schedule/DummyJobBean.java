package org.beatific.daram.web.schedule;

import java.util.Date;

import org.apache.commons.lang.math.RandomUtils;
import org.beatific.daram.web.websocket.Message;
import org.beatific.daram.web.websocket.OutputMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class DummyJobBean implements ApplicationListener<BrokerAvailabilityEvent> {

	private final MessageSendingOperations<String> messagingTemplate;

	@Autowired
	public DummyJobBean(final MessageSendingOperations<String> messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	@Scheduled(fixedDelay = 1000)
	protected void sendDataUpdates() {

		Message message = new Message();
    	message.setId(RandomUtils.nextInt());
    	message.setMessage("server message");
    	
		OutputMessage output = new OutputMessage(message, new Date());
		this.messagingTemplate.convertAndSend("/topic/message", output);
	}


	@Override
	public void onApplicationEvent(BrokerAvailabilityEvent arg0) {
		
	}

}