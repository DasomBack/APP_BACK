package com._thefull.dasom_app_demo.global.auth;

import com._thefull.dasom_app_demo.domain.store.domain.Store;
import com._thefull.dasom_app_demo.domain.store.repository.StoreRepository;
import com._thefull.dasom_app_demo.domain.user.domain.User;
import com._thefull.dasom_app_demo.domain.user.repository.UserRepository;
import com._thefull.dasom_app_demo.global.exception.AppException;
import com._thefull.dasom_app_demo.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    public UserDetails loadUserByUsername(String principalStr) throws UsernameNotFoundException {

        String phoneNum = principalStr.split("&")[0];
        String storeCode = principalStr.split("&")[1];

        User user = userRepository.findByPhoneNum(phoneNum)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_USER, "사용자를 찾을 수 없습니다. 다시 회원가입해주십시오."));

        Store store = storeRepository.findByCode(storeCode)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_STORE, "매장을 찾을 수 없습니다."));

        LoginUser loginUser = LoginUser.from(user, store);
        if (user!=null && store.getUser().getUserId()==user.getUserId()){
            return new CustomUserDetails(loginUser);
        }
        throw new UsernameNotFoundException("사용자를 찾을 수 없습니다. 다시 회원가입해주십시오.");
    }
}
