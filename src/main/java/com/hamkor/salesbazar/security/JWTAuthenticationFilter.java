package com.hamkor.salesbazar.security;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private JWTGenerator jwtGenerator;

    private CustomUserDetailsService customUserDetailsService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);
        if (jwtGenerator.validateToken(token)) {
            assert token != null;
            String username = jwtGenerator.getUsernameFromToken(token);
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        } else {
            return null;
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/api/auth/**", request.getServletPath())
                || new AntPathMatcher().match("/resources/**", request.getServletPath())
                || new AntPathMatcher().match("/index.html", request.getServletPath())
                || new AntPathMatcher().match("/", request.getServletPath())
                || new AntPathMatcher().match("/public/**", request.getServletPath())
                || new AntPathMatcher().match("/images/**", request.getServletPath())
                || new AntPathMatcher().match("/home", request.getServletPath())
                || new AntPathMatcher().match("/login", request.getServletPath())
                || new AntPathMatcher().match("/*.js", request.getServletPath())
                || new AntPathMatcher().match("/*.js.map", request.getServletPath())
                || new AntPathMatcher().match("/swagger-resources/**", request.getServletPath())
                || new AntPathMatcher().match("/swagger-ui.html", request.getServletPath())
                || new AntPathMatcher().match("/v2/api-docs", request.getServletPath())
                || new AntPathMatcher().match("/webjars/**", request.getServletPath())
                || new AntPathMatcher().match("/swagger-ui/**", request.getServletPath());
    }
}
