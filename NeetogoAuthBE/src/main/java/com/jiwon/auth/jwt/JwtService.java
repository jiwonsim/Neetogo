package com.jiwon.auth.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class JwtService  {

    @Value("${jwt.secret}")
    private String secretKey;

    public String makeJwt(HttpServletRequest res) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + 1000 * 60 * 1);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Map<String, Object> headerMap = new HashMap<String, Object>();

        headerMap.put("typ","JWT");
        headerMap.put("alg","HS256");

        Map<String, Object> map= new HashMap<String, Object>();

        String name = res.getParameter("name");
        String email = res.getParameter("email");

        map.put("name", name);
        map.put("email", email);


//        JwtBuilder builder = Jwts.builder().setHeader(headerMap)
//                .setExpiration(expireTime)
//                .claim("name", name)
//                .claim("email", email)
//                .setSubject(name)
//                .signWith(signatureAlgorithm, signingKey);
//        return builder.compact();
        try {

            String jwt = Jwts.builder()
                    .setSubject("users/TzMUocMF4p")
                    .setExpiration(expireTime)
                    .claim("name", "Robert Token Man")
                    .claim("scope", "self groups/admins")
                    .signWith(signatureAlgorithm, signingKey)
                    .compact();

            return jwt;
        }
//        catch (UnsupportedEncodingException uee) {
//            log.error("Unsupported Encdoing Exception : " + uee.getMessage());
//        }

        catch (Exception e) {
            log.error("Exception : " + e.getMessage());
        }

        return null;
    }

    public void validateToken(String token) {
        String jwt = token;
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                    .parseClaimsJws(jwt);
            String scope = claimsJws.getBody().get("scope").toString();
            log.info("scope : " + scope);
        }
        catch (ExpiredJwtException eje) {
            log.info("Expired JWT : " + eje.getMessage());
        }
        catch (JwtException je) {
            log.info("JWT Runtime Exception : " + je.getMessage());
        }
    }

//    public boolean checkJwt(String jwt) throws Exception {
//        try {
//            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
//                    .parseClaimsJws(jwt).getBody(); // 정상 수행된다면 해당 토큰은 정상토큰
//
//            log.info("expireTime :" + claims.getExpiration());
//            log.info("claims: " + claims);
////            log.info("name :" + claims.get("name"));
////            log.info("Email :" + claims.get("email"));
//
//            return true;
//        } catch (ExpiredJwtException exception) {
//            log.info("토큰 만료");
//            return false;
//        } catch (JwtException exception) {
//            log.info("토큰 변조");
//            return false;
//        }
//    }
}