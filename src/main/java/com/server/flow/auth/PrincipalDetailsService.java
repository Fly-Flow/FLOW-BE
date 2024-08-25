package com.server.flow.auth;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.server.flow.employee.entity.Employee;
import com.server.flow.employee.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

/**
 * 시큐리티 설정에서.loginPage("/api/login");
 * login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행된다
 */
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
	private final EmployeeRepository employeeRepository;

	/**
	 * 시큐리티 세션 = Authentication = UserDetails
	 * UserDetails가 반환돠면 Authentication 내부에 들어감
	 * 세션에는 Authentication이 내부에 들어감
	 */
	@Override
	public UserDetails loadUserByUsername(String employeeNumber) throws UsernameNotFoundException {
		Optional<Employee> employee = employeeRepository.findByEmployeeNumber(employeeNumber);

		if (employee.isPresent()) {
			return new PrincipalDetails(employee.get());
		}
		throw new UsernameNotFoundException(employeeNumber);
	}
}
