package com.server.flow.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.server.flow.employee.entity.Employee;

import lombok.RequiredArgsConstructor;

/**
 * memo.
 * 시큐리티가 /api/login 요청이 오면 낚아채서 로그인을 진행시킨다
 * 로그인 진행이 완료돠면, 시큐리티 session을 생성한다. (Security ContextHolder)
 * 오브젝트 타입은 Authentication 타입 객체여야 한다.
 * Authentication 안에 User 정보가 있어야 한다.
 * User 오브젝트의 타입은 UserDetails 타입 객체여야 한다.
 *
 * Security Session => Authentication 객체만 들어가야 한다 => 해당 객체 안에는 User 정보가 있어야 하는데, 타입이 UserDetails여야 한다
 * -> Security Session => Authentication => UserDetails(Principals)
 */

@RequiredArgsConstructor
public class PrincipalDetails implements UserDetails {
	private final Employee employee;

	// 해당 User의 권한을 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Collection<GrantedAuthority> collection = new ArrayList<>();
		// collection.add(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
		// collection.add((GrantedAuthority)() -> "ROLE_EMPLOYEE");
		//return collection;
		return null;
	}

	@Override
	public String getPassword() {
		return employee.getPassword();
	}

	// todo. username 대신 employeeNumber를 사용하는데 Security 설정하는데 Username으로 되어야 하는데 어떻게 바꾸지
	@Override
	public String getUsername() {
		return employee.getEmployeeNumber();
	}
}
