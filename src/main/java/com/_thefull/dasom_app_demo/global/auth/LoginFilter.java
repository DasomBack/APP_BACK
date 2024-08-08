package com._thefull.dasom_app_demo.global.auth;


import com._thefull.dasom_app_demo.global.utils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private JWTUtils jwtUtils;
    private CustomAuthenticationFailureHandler authenticationFailureHandler;

    public LoginFilter(AuthenticationManager authenticationManager, CustomAuthenticationFailureHandler authenticationFailureHandler, JWTUtils jwtUtils){
        this.authenticationManager=authenticationManager;
        this.jwtUtils=jwtUtils;
        this.authenticationFailureHandler=authenticationFailureHandler;

        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            BufferedReader reader = request.getReader();
            StringBuilder stringBuilder = new StringBuilder();
            String line="";

            while ((line=reader.readLine()) !=null){
                stringBuilder.append(line);

            }
            String jsonString = stringBuilder.toString();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonResponse = (JSONObject) jsonParser.parse(jsonString);

            String phoneNum = (String) jsonResponse.get("phoneNum");
            String password = (String) jsonResponse.get("password");
            String storeCode = (String) jsonResponse.get("storeCode");

            String principalStr= phoneNum+"&"+storeCode;


            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principalStr, password, null);

            return authenticationManager.authenticate(authenticationToken);

        }catch (AuthenticationException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();

        String newToken = jwtUtils.createNewToken(principal, 1000 * 60 * 60 * 2l);
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Authorization","Bearer "+newToken);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        authenticationFailureHandler.onAuthenticationFailure(request,response,failed);
    }
}
