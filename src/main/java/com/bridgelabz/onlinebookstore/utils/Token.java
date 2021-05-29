package com.bridgelabz.onlinebookstore.utils;

import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.UUID;

public class Token {

        @Autowired
        FileProperties jwtProperties;

        public String generateLoginToken(UserDetailsModel userDetails) {

            long currentTime = System.currentTimeMillis();

            return Jwts.builder()
                    .setId(String.valueOf(userDetails.userId))
                    .setSubject(userDetails.fullName)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(currentTime + jwtProperties.getJwtExpirationMs()))
                    .signWith(SignatureAlgorithm.HS256, jwtProperties.getJwtSecret())
                    .compact();
        }

        public String generateVerificationToken(UserDetailsModel userDetails) {

            long currentTime = System.currentTimeMillis();
            System.out.println("generate token id:   " + userDetails.userId);
            return Jwts.builder()
                    .setId(String.valueOf(userDetails.userId))
                    .setSubject(userDetails.fullName)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(currentTime + jwtProperties.getVerificationMs()))
                    .signWith(SignatureAlgorithm.HS256, jwtProperties.getJwtSecret())
                    .compact();
        }

        public UUID decodeJWT(String jwt) throws JwtException {
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(jwtProperties.getJwtSecret()).parseClaimsJws(jwt).getBody();

                System.out.println("jwt id: " + claims.getId());
                return UUID.fromString(claims.getId());
            } catch (ExpiredJwtException e) {
                throw new JwtException("session time out");
            }
        }

}

