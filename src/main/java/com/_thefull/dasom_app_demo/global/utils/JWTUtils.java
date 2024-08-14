package com._thefull.dasom_app_demo.global.utils;


import com._thefull.dasom_app_demo.domain.store.domain.Store;
import com._thefull.dasom_app_demo.domain.store.repository.StoreRepository;
import com._thefull.dasom_app_demo.global.auth.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTUtils {

    private Key key;
    @Autowired
    public JWTUtils(@Value("${spring.jwt.secret}")String secretKey){
        byte[] decodedKey = Decoders.BASE64.decode(secretKey);
        key = Keys.hmacShaKeyFor(decodedKey);
    }

    public String getName(String token){
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .get("name",String.class);
    }

    public String getPhoneNum(String token){
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .get("phoneNum",String.class);
    }

    public String getStoreCode(String token){
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .get("storeCode",String.class);
    }

    public String getProfileImageUrl(String token){
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .get("profileImageUrl",String.class);
    }

    public Store getStore(String token){
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .get("store",Store.class);
    }

    public String createNewToken(CustomUserDetails dto, Long expiredMs){
        Claims claims= Jwts.claims();
        claims.put("name",dto.getUsername());
        claims.put("phoneNum", dto.getPhoneNum());
        claims.put("storeCode",dto.getStoreCode());
        claims.put("profileImageUrl", dto.getImgUrl());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiredMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isExpired(String token){
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

}
