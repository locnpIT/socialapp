package com.example.socialapp.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class jwtValidator extends OncePerRequestFilter {

	//jwtValidator là một lớp mở rộng từ OncePerRequestFilter của Spring, đảm bảo rằng mỗi yêu cầu HTTP đến server sẽ được kiểm tra JWT một lần duy nhất trong chuỗi các bộ lọc (filter chain).
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = request.getHeader(jwtConstant.JWT_HEADER);
		
		if(jwt!=null) {
			try {
				String email = JwtProvider.getEmailFromJwtToken(jwt);
				
				List<GrantedAuthority> authorities = new ArrayList<>();
				
				Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
				
			} catch (Exception e) {
				throw new BadCredentialsException("invalid token");
				
			}
		}
//		else {
//			throw new BadCredentialsException("Please provide a valid token");
//		}
		
		filterChain.doFilter(request, response);
		
		
	}
	

	

}
