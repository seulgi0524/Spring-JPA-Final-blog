package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void 회원가입(User user) {
		
		String rawPassword = user.getPassword(); //1234원본
		String encPassword = encoder.encode(rawPassword); //해쉬		
		user.setPassword(encPassword);
		
		 userRepository.save(user);
		user.setRole(RoleType.USER);
	}	
	

	@Transactional
	public void 회원수정(User user) {
		
		// 수정시에는 영속성 컨텍스트에 User오브젝트를 영속화시키고, 영속화된 User오브젝트를 수정
		//select를 해서 User오브젝트를 db로 부터 가져오는 이유는 영속화를 하기위해
		//영속화된 옵젝을 변경하면 자동으로 db에 업데이트문 날려줌
		
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		
	
		//회원 수정 함수 종료시 = 서비스 종료
	}	

	/*@Transactional(readOnly=true) //정합성
	public User 로그인(User user) {
		System.out.print("1번");	
		System.out.print(user);
		return  userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());	
	}*/
	
	
}
