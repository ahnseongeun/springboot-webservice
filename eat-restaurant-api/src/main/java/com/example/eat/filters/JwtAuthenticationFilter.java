package com.example.eat.filters;

import com.example.eat.domain.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {


    private JwtUtil jwtUtil;


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        super (authenticationManager);
        this.jwtUtil=jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        Authentication authentication=getAuthentication (request);
        //TODO: JWT 검사
        if(authentication != null){
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
        }

        chain.doFilter (request,response);
    }

    private Authentication getAuthentication(HttpServletRequest request){
        String token=request.getHeader ("Authorization");
        if(token==null){
            return null;
        }
        //TODO: JwtUtil에서 claims 얻기
        Claims claims=jwtUtil.getClaims (token.substring ("Bearer ".length ()));
        Authentication authentication=
                new UsernamePasswordAuthenticationToken (claims,null);
        return authentication;
    }
}
