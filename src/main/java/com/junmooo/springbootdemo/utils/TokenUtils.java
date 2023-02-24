package com.junmooo.springbootdemo.utils;


import com.junmooo.springbootdemo.entity.token.OperToken;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import java.nio.charset.StandardCharsets;
import java.security.Key;

/**
 * Description:Token生成工具
 * <p>
 * 第一部分我们称它为头部(header),第二部分我们称其为载荷(payload, 类似于飞机上承载的物品)，第三部分是签证(signature).
 * <p>
 * Auth: Frank
 * <p>
 * Date: 2020-11-05
 * <p>
 * Time: 下午 5:05
 */

public class TokenUtils {
    public static String generateToken(OperToken userToken, int expire) throws JoseException {
        JwtClaims claims = new JwtClaims();
        claims.setSubject(userToken.getOperName());
        claims.setClaim("OPER_ID", userToken.getOperId());
        claims.setClaim("OPER_NAME", userToken.getOperName());
        claims.setClaim("OPER_EMAIL", userToken.getOperEmail());
        claims.setExpirationTimeMinutesInTheFuture(expire == 0 ? 60 * 24 : expire);

        Key key = new HmacKey("junmooo".getBytes(StandardCharsets.UTF_8));

        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
        jws.setKey(key);
        jws.setDoKeyValidation(false); // relaxes the key length requirement

        //签名
        return jws.getCompactSerialization();
    }

    /**
     * 解析token
     *
     * @param token token
     * @return 解析token
     * @throws Exception 异常
     */
    public static OperToken getInfoFromToken(String token) throws Exception {

        if (token == null) {
            return null;
        }

        Key key = new HmacKey("junmooo".getBytes(StandardCharsets.UTF_8));

        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime()
                .setAllowedClockSkewInSeconds(30)
                .setRequireSubject()
                .setVerificationKey(key)
                .setRelaxVerificationKeyValidation() // relaxes key length requirement
                .build();

        JwtClaims processedClaims = jwtConsumer.processToClaims(token);

        return new OperToken(
                processedClaims.getClaimValue("OPER_ID").toString(),
                processedClaims.getClaimValue("OPER_NAME").toString(),
                processedClaims.getClaimValue("OPER_EMAIL").toString()
        );
    }

    public static void main(String[] agars) throws Exception {
        OperToken userToken = new OperToken("1", "junmo", "超级管理员名称");
        String token = generateToken(userToken, 60);
        OperToken infoFromToken = getInfoFromToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYWEiLCJPUEVSX0lEIjoiNDkyNjZlYTQtZTFiNy00ZTNiLWI1ODAtNjkxOTM3NDliYjhlIiwiT1BFUl9OQU1FIjoiYWFhIiwiT1BFUl9FTUFJTCI6IjEyM0AxMTEuY29tIiwiZXhwIjoxNjc2OTg2MDQ1fQ.avZr4ii8IigY7pLS8MXqR_qboCt4H8XtwLAMo4rJyUo");
        System.out.println(infoFromToken);
    }


}
