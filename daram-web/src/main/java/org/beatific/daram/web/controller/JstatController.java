package org.beatific.daram.web.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.beatific.daram.web.controller.converter.JstatConverter;
import org.beatific.daram.web.service.JstatService;
import org.beatific.daram.web.vo.dao.JstatVo;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value="/jstat")
@Controller
public class JstatController {

	@Resource(name="jstatService")
	private JstatService service;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/graph", method=RequestMethod.POST)
	@ResponseBody
    public ModelAndView listMonitorGraph(@RequestBody JstatVo vo) {
		
		ModelAndView mav = new ModelAndView("jsonView");
		
		List model = JstatConverter.convertGoogleLineCharts(service.listJstatGraphByServer(vo));
		
		mav.addObject("monitor", model);
		
		return mav;
	}
	
	@MessageMapping("/updateServer")
    public void updateServer(Map<String, String> server) {
		
		JstatVo vo = new JstatVo();
		vo.setServer(server.get("server"));
		service.updateServer(vo);
	}
	
	@RequestMapping(value="/server", method=RequestMethod.POST)
	@ResponseBody
    public ModelAndView listServer() {
		
		ModelAndView mav = new ModelAndView("jsonView");
	
		JstatVo vo = new JstatVo();
		
		List<Map> servers = service.listServer(vo);
		
		mav.addObject("servers", servers);
		
		return mav;
	}
}
