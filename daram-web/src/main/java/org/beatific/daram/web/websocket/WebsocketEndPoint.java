package org.beatific.daram.web.websocket;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class WebsocketEndPoint extends AbstractWebsocketEndPoint {

	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		TextMessage returnMessage = new TextMessage(message.getPayload()+" received at server");
		session.sendMessage(returnMessage);
	}

	@Override
	protected void executeInternal(JobExecutionContext paramJobExecutionContext)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		
	}
}