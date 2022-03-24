package com.cos.blog.test;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//스프링이 com.cos.blog패키지 이하를 읽어 특정 어노테이션 클래스를 new해서 관리해줌
@RestController 
public class BlogControllerTest {

	// http://localhost:8000/test/hello
	@GetMapping("/test/hello")
	public String hello() {
		
		
		return "<h1>hello spring boot</h1>";
	}
}
