package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
public class UserController {

	//필드
	@Autowired
	private UserService userService;
	
	//생성자
	//메서드 gs
	//메서드 일반
	
	/* joinForm */
	@PostMapping ( value="/api/users")
	public JsonResult joinForm ( @RequestBody UserVo userVo ) {
		
		System.out.println("userController.joinform()");
		
		System.out.println(userVo);
		
		int count = userService.exeJoinUser(userVo);
		
		if ( count != 1 ) { 
			return JsonResult.fail("회원가입이 불가합니다");
			
		} else {  
			return JsonResult.success(count);
		}
		
	}
	
	
	/* login */
	@PostMapping ( value="/api/users/login" )
	public JsonResult loginform ( @RequestBody UserVo userVo, 
									HttpServletResponse response ) {
		 
		System.out.println("userController.login()");
		
		System.out.println(userVo);
		
		UserVo authUser = userService.exeLogin(userVo);
		
		System.out.println("authUser: " + authUser);
		
		if ( authUser != null ) { 
			
			//토큰을 만들고 "응답문서의 헤더"에 토큰을 붙여서 보낸다
			JwtUtil.createTokenAndSetHeader(response, ""+authUser.getNo());
			
			return JsonResult.success(authUser);
			
		} else {  
			
			return JsonResult.fail("회원가입이 불가합니다");
		}
		
		
		
		
		
		
		

		
	}
	
	
	
	
	
	
	
	
	
	
	/* join */
	@RequestMapping ( value="/user/joinUser", method= {RequestMethod.GET, RequestMethod.POST} )
	public String join( @ModelAttribute UserVo userVo  ) {
		
		System.out.println("userController.joinUser()");
		
		int count = userService.exeJoinUser(userVo);
		
		if(count == 1) {}
		return "/user/joinOk";
		
	}
	
	
	/* loginForm */
	@RequestMapping ( value="/user/loginform", method= {RequestMethod.GET, RequestMethod.POST} )
	public String loginForm() {
		
		System.out.println("userController.loginform()");
		
		return "/user/loginForm";
		
	}
	
	

	
	/* loginOut */
	@RequestMapping ( value="/user/logout", method= {RequestMethod.GET, RequestMethod.POST} )
	public String logout ( HttpSession session ) {
		
		System.out.println("userController.logout()");
		
		//session.removeAttribute("authUser"); //방의 데이터를 지움 , 특정데이터를 지움
		session.invalidate(); //방을 폭파 공간을 폭파, 번호표 새로 받음
		
		return "redirect:/main";
		
	}
	
	
	/* modifyForm */
	@RequestMapping ( value="/user/modifyform", method= {RequestMethod.GET, RequestMethod.POST} )
	public String modifyform ( Model model, HttpSession session ) {
		
		System.out.println("userController.modifyform()");
		
		System.out.println((UserVo)session.getAttribute("authUser")); 
		
		UserVo userVo = userService.exeGetUserOne( (UserVo)session.getAttribute("authUser") );
		
		System.out.println(userVo);
		
		model.addAttribute(userVo);
		
		
		return "/user/modifyForm";
	}


	/* modify */
	@RequestMapping ( value="/user/modify", method= {RequestMethod.GET, RequestMethod.POST} )
	public String modify ( @ModelAttribute UserVo userVo, HttpSession session ) {
		
		System.out.println("userController.modify()");
		
		UserVo authUser = userService.exeUpdateUser(userVo);
		
		session.setAttribute("authUser", authUser); 
		
		
		return "redirect:/main";
	}
	
	
	/* 아이디 체크 */
	@ResponseBody
	@RequestMapping ( value="/api/user/idcheck", method={RequestMethod.GET, RequestMethod.POST}  )
	public boolean idcheck ( @RequestParam(value="id") String id ) {
		
		System.out.println("userApiController.idcheck()");
		System.out.println(id);
		
		boolean can = userService.exeIdCheck(id);
		
		return can;
		
	}
	
	
	
	
	
	
	
}
