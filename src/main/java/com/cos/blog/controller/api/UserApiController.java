package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

//import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("회원가입 호출");
		 userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user) {
		 userService.회원수정(user);
		 // db에 값 변경은 되지만 세션값 변경x-> 로그아웃하고 다시 로그인해야 적용됨
		 // 그래서 우리가 직접 세션값 변경해줄거임
		 // 스프링시큐리티 개념필요함
		 Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		 
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	
}

/*
 * @PostMapping("/api/user/login") public ResponseDto<Integer>login(@RequestBody
 * User user, HttpSession session) { System.out.println("로그인 호출");
 * System.out.println(user); User principal = userService.로그인(user);
 * 
 * if(principal!=null) { session.setAttribute("principal", principal); } return
 * new ResponseDto<Integer>(HttpStatus.OK.value(),1); }
 */
