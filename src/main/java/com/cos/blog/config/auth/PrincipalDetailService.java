package com.cos.blog.config.auth;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	
	// 스프링이 로그인 요청을 가로챌떄 username, password 2개를 가로채는데
	// password는 알아서 처리해줌 (틀려도 알아서 해줌)
	// username이 db에 잇는지 확인해주면됨 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
			User principal = userRepository.findByUsername(username)
					.orElseThrow(()->
					{
						return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.");		
					});
		return new PrincipalDetail(principal); // 시큐리티 세션에 유저 정보가 저장됨
	}
}
