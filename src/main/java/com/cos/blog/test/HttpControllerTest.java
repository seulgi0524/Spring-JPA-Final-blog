package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 사용자 요청->응답(html파일 응답)
// @Controller 이용하면됨

// 사용자 요청->응답 (데이터를 응답)

// http://localhost:8080/blog/http/lombok
@RestController
public class HttpControllerTest {

	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m= Member.builder().password("1234").username("dd").email("dddd").build();
		System.out.println(TAG+"getter:"+m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG+"getter:"+m.getUsername());
		
		return"lombok 테스트 완료";
	}
	
	private static final String TAG= "HttpControllerTest";
	
		// 인터넷 브라우저 요청은 무조건 get만 된다(post, put, delete 는 안됨)
		// 그래서 postman으로 테스트 해보는거임
		
		// http://localhost:8080/http/get (select)
		@GetMapping("/http/get")
		public String getTest(Member m) { //id=1&username=ssar&password=1234
	
			return "get요청"+m.getId()+","+m.getUsername()+","+m.getPassword();
			
		}
		// http://localhost:8080/http/post (insert)
		@PostMapping("/http/post")
		public String postTest(@RequestBody Member m ) {
			
			return "post요청"+m.getId()+","+m.getUsername()+","+m.getPassword();
			
		}
		// http://localhost:8080/http/put (update)
		@PutMapping("/http/put")
		public String putTest(@RequestBody Member m) {
			
			return "put요청"+m.getId()+","+m.getUsername()+","+m.getPassword();
			
			
		}
		// http://localhost:8080/http/get (delete)
		@DeleteMapping("/http/delete")
	public String deleteTest(@RequestBody Member m) {
	
			return "get요청"+m.getId()+","+m.getUsername()+","+m.getPassword();
			
	
}
}
