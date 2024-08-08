package com._thefull.dasom_app_demo.global.auth;

import com._thefull.dasom_app_demo.domain.store.domain.Store;
import com._thefull.dasom_app_demo.domain.store.repository.StoreRepository;
import com._thefull.dasom_app_demo.domain.user.domain.User;
import com._thefull.dasom_app_demo.domain.user.repository.UserRepository;
import com._thefull.dasom_app_demo.global.exception.AppException;
import com._thefull.dasom_app_demo.global.exception.ErrorCode;
import com._thefull.dasom_app_demo.global.utils.JWTUtils;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private JWTUtils jwtUtils;
    private UserRepository userRepository;
    private StoreRepository storeRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token="";
        try {
            String authorization = request.getHeader("Authorization");

            if (authorization==null || !authorization.startsWith("Bearer ")){
                throw new JwtException("Http Header에 토큰이 존재하지 않습니다.");
            }

            token = authorization.split(" ")[1];

            if (jwtUtils.isExpired(token)){
                throw new JwtException("토큰이 존재하나, 만료되었습니다. 토큰을 재발급 받아야 서비스를 이용할 수 있습니다.");
            }

            String email = jwtUtils.getEmail(token);
            String storeCode = jwtUtils.getStoreCode(token);
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_USER, "사용자를 찾을 수 없습니다"));

            Store store = storeRepository.findByCode(storeCode)
                    .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_STORE, "매장을 찾을 수 없습니다."));

            LoginUser loginUser = LoginUser.from(user, store);

            CustomUserDetails principal = new CustomUserDetails(loginUser);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }catch (JwtException e){
            logger.error(e.getMessage());
        }finally {
            filterChain.doFilter(request,response);
        }


    }
}
