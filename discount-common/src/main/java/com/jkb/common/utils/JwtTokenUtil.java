package com.jkb.common.utils;

import cn.hutool.core.codec.Base64;
import com.jkb.common.base.Audience;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Slf4j
public class JwtTokenUtil{
    /**
     * 构建jwt
     * @param userId
     * @param username
     * @param role
     * @param audience
     * @return
     */
    public static String createJWT(long userId,String phone,String username, String role, Audience audience) {
        // 使用HS256加密算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(audience.getBase64Secret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //userId是重要信息，进行加密下
        String encryId = Base64.encode(String.valueOf(userId));

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                // 可以将基本不重要的对象信息放到claims
                .claim("role", role)
                .claim("phone", phone)
                .claim("userId", encryId)
                .setSubject(username)           // 代表这个JWT的主体，即它的所有人
                .setIssuer(audience.getClientId())              // 代表这个JWT的签发主体；
                .setIssuedAt(new Date())        // 是一个时间戳，代表这个JWT的签发时间；
                .setAudience(audience.getName())          // 代表这个JWT的接收对象；
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        int TTLMillis = audience.getExpiresSecond();
        if (TTLMillis >= 0) {
            long expMillis = nowMillis + TTLMillis;
            Date exp = new Date(expMillis * 1000);
            builder.setExpiration(exp)  // 是一个时间戳，代表这个JWT的过期时间；
                    .setNotBefore(now); // 是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的
        }

        //生成JWT
        return builder.compact();
    }

    /**
     * 解析jwt
     * @param jsonWebToken
     * @param base64Security
     * @return
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security) {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
    }
    /**
     * 从token中获取用户名
     * @param token
     * @param base64Security
     * @return
     */
    public static String getUsername(String token, String base64Security){
        return parseJWT(token, base64Security).getSubject();
    }

    /**
     * 从token中获取用户ID
     * @param token
     * @param base64Security
     * @return
     */
    public static long getUserId(String token, String base64Security){
        String userId = parseJWT(token, base64Security).get("userId", String.class);
        return Long.parseLong(Base64.decodeStr(userId));
    }

    /**
     * 从token中获取用户phone
     * @param token
     * @param base64Security
     * @return
     */
    public static String getUserPhone(String token, String base64Security){
        String userId = parseJWT(token, base64Security).get("phone", String.class);
        return Base64.decodeStr(userId);
    }

}

