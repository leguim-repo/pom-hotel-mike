package com.pomhotel.booking.ui.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        System.out.println("onAuthenticationFailure: "+e.getMessage());
        //TODO redirigir cuando las credenciales son incorrectas
        //e.printStackTrace();
        httpServletResponse.sendRedirect("/showMyLoginPage?badcredentials");
    }
}
