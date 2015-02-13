package org.beatific.daram.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.beatific.daram.web.controller.converter.JstatConverter;
import org.beatific.daram.web.service.JstatService;
import org.beatific.daram.web.vo.dao.MonitorGraphVo;
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
    public ModelAndView listMonitorGraph(@RequestBody MonitorGraphVo vo) {
		
		ModelAndView mav = new ModelAndView("jsonView");
	
		Map modelMap = JstatConverter.convertGoogleLineCharts(service.listJstatGraphByServer(vo));
		
		mav.addAllObjects(modelMap);
		
		return mav;
	}
}
