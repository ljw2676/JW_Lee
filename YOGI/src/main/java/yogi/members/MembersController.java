package yogi.members;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MembersController {
	
	@Resource(name="membersService")
	private MembersService membersService;
	
	@RequestMapping(value= {"/", "/first"}, method=RequestMethod.GET)
	 public String first(){
    	return "firstPage";
	}
	
	@RequestMapping(value= {"/", "/first"}, method=RequestMethod.POST)
	 public String login(@ModelAttribute("member") MembersModel member, HttpServletRequest request){
		MembersModel mm;
		mm = membersService.loginCheck(member, request);
		if(mm!=null) {
			HttpSession session = request.getSession();  
			/* 세션값 더 필요한 거 있으면 요기다 저장하세용~! */
			session.setAttribute("session_m_id", mm.getM_id());
			session.setAttribute("session_m_no", mm.getM_no());
			return "mainPage";
		}
		else
			return "firstPage";
	}
	
	@RequestMapping(value="/members/createForm", method=RequestMethod.GET)
	public String createForm(Model model) {	
		return "createForm";
	}

	@RequestMapping(value="/members/createForm", method=RequestMethod.POST)
	//public String join(@ModelAttribute("member") MembersModel member, MultipartFile file) throws Exception{
	public String join(@ModelAttribute("member") MembersModel member) throws Exception{
		System.out.println("컨트롤러");
		//System.out.println("파일이름 : "+ file.getName());
		//member.setM_profile(multipartFile.getOriginalFilename());
		//System.out.println("컨트롤러 : " + member.getM_profile());
		membersService.insertMember(member);//일반 회원가입	
		
		return "redirect:/first";
	}
	
	@ResponseBody
	@RequestMapping("/checkId")
	public Map<Object, Object> idcheck(HttpServletRequest request){
		System.out.println("컨트롤러");
		String userId = request.getParameter("userId");
		int count = 0;
		Map<Object, Object> map = new HashMap<Object, Object>();
		System.out.println("Controller : " + userId);
		count = membersService.checkId(userId);
		map.put("cnt", count);
		
		return map;
	}
	
	
	
}
