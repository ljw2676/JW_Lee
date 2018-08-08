package yogi.admin.report;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import yogi.config.CommandMap;

@Controller
public class AdminReportController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="adminReportService")
	private AdminReportService adminReportService;

	@RequestMapping(value="/admin/report/list")
	public ModelAndView memberList(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("adminReportList");
		List<Map<String, Object>> list = adminReportService.selectAdminReportList(commandMap.getMap());
		
		mv.addObject("list",list);
		
		return mv;
	}
}
