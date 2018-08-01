package com.liuzihu.blog.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.liuzihu.blog.user.domain.User;
import com.liuzihu.blog.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jerry
 * @version 1.0
 * @Description token 工具类：负责token的生成、解析、校验。
 * @Date Create by 2018-05-31 15:47
 * @Modified by
 */
@Component
public class TokenUtils {

    /** 秘钥 **/
    private final String SECRET = "Jerry";
    /** 加密算法 **/
    private final Algorithm algorithm = Algorithm.HMAC256(SECRET);

    public TokenUtils() throws UnsupportedEncodingException {

    }

    public String genernationToken(Authentication authentication,User user) {
        //设置token有效时间
        String authorities = authentication.getAuthorities()
                .stream().map(authority -> ((GrantedAuthority) authority)
                        .getAuthority()).collect(Collectors.joining(","));
        //token有效时间30分钟
        long VALIDATE_MINUTE = 30;
        Date expiration = Date.from(ZonedDateTime.now().plusMinutes(VALIDATE_MINUTE).toInstant());
        String token = JWT.create().withExpiresAt(expiration)
                .withSubject(authentication.getName())
                .withClaim("authorities", authorities)
                .withClaim("updateDate", user.getUpdateDate())
                .withIssuedAt(new Date())
                .withKeyId(SECRET)
                .sign(algorithm);
        return token;
    }
    public boolean  verifyToken(String token) {
        //判断token是否存在
        if (token == null) {
            return false;
        }
        DecodedJWT decode = JWT.decode(token);
        //如果密码在token生成以后修改,该token就是无效token
        try {
            if (decode.getIssuedAt().before(decode.getClaim("updateDate").asDate())) {
                return false;
            }
            JWT.require(algorithm).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token){
        DecodedJWT decode = JWT.decode(token);
        String authorityString = decode.getClaim("authorities").asString();
        Collection<? extends GrantedAuthority> authorities = Collections.emptyList();
        if(!StringUtils.isEmpty(authorityString)){
            authorities = Arrays.asList(authorityString.split(","))
                    .stream()
                    .map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());
        }
        org.springframework.security.core.userdetails.User principal = new org.springframework.security.core.userdetails.User(decode.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }
    public static String getUserNameByToken(String token) {
        if (token == null) {
            return null;
        }
        return JWT.decode(token).getSubject();
    }
}
