package com.youn.realworldapp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {    // JwtTokenProvider 컴포넌트를 이용해 실제 인증 작업을 진행

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        if(token != null && jwtTokenProvider.validateToken(token)) {    // 토큰이 유요한 경우
            Authentication authentication = jwtTokenProvider.getAuthentication(token);  // 인증용 객체를 반환 받음
            SecurityContextHolder.getContext().setAuthentication(authentication);   // Authentication 객체를 SecurityContext에 저장
        }
        chain.doFilter(request, response);
    }
}
