package com.pixels.parquediversiones.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {
    /**
     * Secret key to sign the tokens
     */
    @Value("${security.jwt.secret}")
    private String key;

    /**
     * Expiration time in milliseconds
     */
    @Value("${security.jwt.ttlMillis}")
    private long ttlMillis;

    /**
     * Create a new token.
     *
     * @param email
     * @return
     */
    public String create(String email, String role) {

        // The JWT signature algorithm used to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //  sign JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);

        //  set the JWT Claims
        JwtBuilder builder = Jwts.builder().setIssuedAt(now).setSubject(email).addClaims(claims)
                                .signWith(signingKey, signatureAlgorithm);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    /**
     * Method to validate and read email from the JWT
     *
     * @param jwt
     * @return
     */
    public String getEmail(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as
        // expected)
        Claims claims = Jwts.parserBuilder().setSigningKey(DatatypeConverter.parseBase64Binary(key))
                .build().parseClaimsJws(jwt).getBody();

        return claims.getSubject();
    }

    /**
     * Method to validate the JWT
     *
     * @param jwt
     * @return
     */
    public boolean validate(String jwt) {
        try {
            Jwts.parserBuilder().setSigningKey(DatatypeConverter.parseBase64Binary(key))
                    .build().parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Jwt expired");
        }
    }
}
