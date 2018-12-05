package controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import service.LoginService;


@Controller
public class Login {
	@Autowired
	LoginService loginservice;
	
	@RequestMapping("/loginvalidate")
	public String loginvalidate(@RequestParam("username") String username,@RequestParam("pic") String pic,@RequestParam("password") String pwd,HttpSession httpSession){
		String picode=(String) httpSession.getAttribute("rand");
		if(!picode.equalsIgnoreCase(pic))
			return "failcode";
		if(username==null)
			return "login";
		String realpwd=loginservice.getpwdbyname(username);
		if(realpwd!=null&&pwd.equals(realpwd))
		{
			long uid=loginservice.getUidbyname(username);
			httpSession.setAttribute("username", username);
			httpSession.setAttribute("uid", uid);
			return "chatroom";
		}else
			return "fail";
	}

	@RequestMapping("/loginvalidate2")
	public void loginvalidate2(ServletRequest req, ServletResponse res){

		try {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) res;
			request.getSession().setAttribute("testKey", "742981086@qq.com");
			request.getSession().setMaxInactiveInterval(10 * 1000);
			response.sendRedirect(request.getContextPath() + "/session");
		} catch (Exception e) {

		}
	}


	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession httpSession){
		httpSession.removeAttribute("username");
		httpSession.removeAttribute("uid");
		return "login";
	}
  }
