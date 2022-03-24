package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Getter;


//스프링 시큐리티가 로그인을 가로채 완료하면 UserDetails 타입의 오브젝트를
//고유 저장소에 저장해줌.

@Getter
public class PrincipalDetail implements UserDetails{
	
	public PrincipalDetail(User user) {
		this.user=user;
	}
	private User user; //콤포지션이라함


	@Override
	public String getPassword() {
	
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 리턴함. (true=만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정이 잠겨있지 않았는지 리턴함. (true=잠기지않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비번이 만료되지 않았는지 리턴함. (true=만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

   // 계정 활성화 인지 리턴함. (true=활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	// 계정 권한 리턴함.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {	
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()->{return "ROLE_"+user.getRole();});
		
		return collectors;
		
		/*collectors.add(new GrantedAuthority(){		
   	  @Override
			public String getAuthority() {
				return "ROLE_"+user.getRole(); //ROLE_USER 
			}
		});*/
	}

}
