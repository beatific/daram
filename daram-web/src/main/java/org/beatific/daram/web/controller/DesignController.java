package org.beatific.daram.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.beatific.daram.web.controller.converter.DesignConverter;
import org.beatific.daram.web.service.DesignService;
import org.beatific.daram.web.vo.dao.DesignVo;
import org.beatific.daram.web.vo.dao.MonitorGraphVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value="/monitor")
@Controller
public class DesignController {

	@Resource(name="designService")
	private DesignService service;
	
	@RequestMapping(value="/graph", method=RequestMethod.POST)
	@ResponseBody
    public ModelAndView listMonitorGraph(@RequestBody MonitorGraphVo vo) {
		
		ModelAndView mav = new ModelAndView("jsonView");
	
		List<Map> list = new ArrayList<Map>();
		
		for(DesignVo design : vo.getDesigns()) {
			
			vo.setDesignName(design.getDesignName());
			list.add(DesignConverter.convertGoogleLineCharts(design, service.listMonitorGraphByDesign(vo)));
		}
		mav.addObject("monitor", list);
		
		return mav;
	}
	
	@RequestMapping(value="/design", method=RequestMethod.POST)
	@ResponseBody
    public ModelAndView listDesign() {
		
		ModelAndView mav = new ModelAndView("jsonView");
	
		List<DesignVo> designs = service.listDesign(new DesignVo());
		
		mav.addObject("designs", designs);
		
		return mav;
	}
}
