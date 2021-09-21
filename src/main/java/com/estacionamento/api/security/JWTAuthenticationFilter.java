package com.estacionamento.api.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.estacionamento.api.model.dto.LoginDTO;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	
	private JWTUtil jwtutil;
	 
	 public JWTAuthenticationFilter(AuthenticationManager authenticationManager,JWTUtil jwtutil) {
		setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
		this.authenticationManager = authenticationManager;
		this.jwtutil = jwtutil;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) 
			throws AuthenticationException{
		
		try {
			LoginDTO creds = new ObjectMapper() 
					.readValue(req.getInputStream(), LoginDTO.class);
			
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getCnpj(), creds.getSenha(), new ArrayList<>());
			
			System.out.println("JWTAuthenticationFilter: " + authToken);
			
			Authentication auth = null;
			System.out.println("loss");
			auth = authenticationManager.authenticate(authToken);
			System.out.println("victory");
			return auth;
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void successfulAuthentication(HttpServletRequest req, 
										 HttpServletResponse res,
										 FilterChain chain, 
										 Authentication auth) throws IOException, ServletException{
		System.out.println("Seucessful");
		String username = ((UserSS) auth.getPrincipal()).getUsername();
		String token = jwtutil.generateToken(username);
		res.addHeader("Authorization", "Bearer " + token);
			
	}
    private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
                throws IOException, ServletException {
        	System.out.println("fail: " + exception.getMessage());
            response.setStatus(401);
            response.setContentType("application/json"); 
            response.getWriter().append(json());
            
        }
        
        private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", "
                + "\"status\": 401, "
                + "\"error\": \"Não autorizado\", "
                + "\"message\": \"Email ou senha inválidos\", "
                + "\"path\": \"/login\"}";
        }
	}

}

