package org.beatific.daram.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.beatific.daram.web.controller.converter.DesignConverter;
import org.beatific.daram.web.service.DesignService;
import org.beatific.daram.web.vo.dao.DesignVo;
import org.beatific.daram.web.vo.dao.MonitorGraphVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value="/monitor")
@Controller
public class DesignController {

	@Resource(name="designService")
	private DesignService service;
	
	@RequestMapping(value="/graph")
	@ResponseBody
    public ModelAndView listMonitorGraph(@RequestBody MonitorGraphVo vo) {
		
		ModelAndView mav = new ModelAndView("jsonView");
	
		List<DesignVo> designs = service.listDesign(new DesignVo());
		
		for(DesignVo design : designs) {
			
			vo.setDesignName(design.getDesignName());
			mav.addObject(design.getDesignName(), DesignConverter.convertGoogleLineCharts(service.listMonitorGraphByDesign(vo)));
			mav.addObject(design.getDesignName() + "xTag", design.getxTag());
			mav.addObject(design.getDesignName() + "yTag", design.getyTag());
			mav.addObject(design.getDesignName() + "Denomination", design.getDenomination());
		}
		
		return mav;
	}
}
