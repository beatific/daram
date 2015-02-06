package org.beatific.daram.web.schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.beatific.daram.web.controller.converter.DesignConverter;
import org.beatific.daram.web.service.DesignService;
import org.beatific.daram.web.vo.dao.DashBoardVo;
import org.beatific.daram.web.vo.dao.DesignVo;
import org.beatific.daram.web.vo.dao.MonitorGraphVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RandomDataGenerator implements
    ApplicationListener<BrokerAvailabilityEvent> {

    private final MessageSendingOperations<String> messagingTemplate;

    @Resource(name="designService")
    private DesignService service;
    
    @Autowired
    public RandomDataGenerator(
        final MessageSendingOperations<String> messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    
    public void onApplicationEvent(final BrokerAvailabilityEvent event) {
    }

    @SuppressWarnings("rawtypes")
	@Scheduled(fixedDelay = 60000)
    public void sendDataUpdates() {
    	
    	DashBoardVo dashBoardVo = new DashBoardVo();
    	dashBoardVo.setDashBoardId(new Long(1));
    	
    	List<Map> list = new ArrayList<Map>();
    	
    	for(DesignVo design : service.listDesignByDashBoard(dashBoardVo)) {
	    	MonitorGraphVo vo = new MonitorGraphVo();
	    	vo.setDesignName(design.getDesignName());
	    	list.add(DesignConverter.convertGoogleLineChart(service.selectMonitorGraphByDesign(vo)));
    	}
    	
        this.messagingTemplate.convertAndSend(
            "/data", list);

    }
}