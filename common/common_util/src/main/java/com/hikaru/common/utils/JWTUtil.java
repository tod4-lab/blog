package com.hikaru.common.utils;

import io.jsonwebtoken.*;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JWTUtil {
    /**
     *  签发创建JWT
     *
     */
    public static String createJWT(String id, String subject, long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        SecretKey secretKey = generalKey();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject) // 主题
                .setIssuer(JWTConstant.JWT_USER) // 签发者
                .setIssuedAt(now) // 签发时间
                .signWith(signatureAlgorithm, secretKey); // 签名算法及秘钥
        if(ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            // 设置过期时间
            builder.setExpiration(expDate);
        }
        return builder.compact();
    }

    /**
     * 签发创建JWT
     * @param subject
     * @return
     */
    public static String createJWT(String subject) {
        return createJWT(subject, subject, JWTConstant.JWT_TTL);
    }


    /**
     * 解析JWT
     * @param jwtStr
     * @return
     */
    public static Claims parseJWT(String jwtStr) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwtStr)
                .getBody();
    }

    /**
     * JWT token验证
     * @param jwtStr
     * @return
     */
    public static CheckResult validateJwt(String jwtStr) {
        CheckResult checkResult = new CheckResult();
        Claims claims = null;
        try {
            claims = parseJWT(jwtStr);
            checkResult.setSuccess(true);
            checkResult.setClaims(claims);

        } catch (ExpiredJwtException e) {
            // token 过期
            checkResult.setErrorCode(JWTConstant.JWT_ERROR_CODE_EXPIRE);
            checkResult.setSuccess(false);
        } catch (SignatureException e) {
            // token 验证失败
            checkResult.setErrorCode(JWTConstant.JWT_ERROR_CODE_FAIL);
            checkResult.setSuccess(false);
        } catch (Exception e) {
            // 其他异常
            checkResult.setErrorCode(JWTConstant.JWT_ERROR_CODE_FAIL);
            checkResult.setSuccess(false);
        }
        return checkResult;
    }

    /**
     * 生成加密的key
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodeKey = Base64.decode(JWTConstant.JWT_SECRET);
        SecretKey key = new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
        return key;
    }


    public static void main(String[] args) throws InterruptedException {
        String token = createJWT("1", "1", 3000);
        CheckResult checkResult = validateJwt(token);
        System.out.println(checkResult.getErrorCode()); // 0

        Thread.sleep(3 * 1000);

        checkResult = validateJwt(token);
        System.out.println(checkResult.getErrorCode()); // 4001

    }
}
