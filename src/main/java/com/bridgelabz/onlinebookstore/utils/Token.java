package com.bridgelabz.onlinebookstore.utils;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class Token {

//        @Autowired
//        FileProperties jwtProperties;
//
//        public String generateLoginToken(UserDetails userDetails) {
//
//            long currentTime = System.currentTimeMillis();
//
//            return Jwts.builder()
//                    .setId(String.valueOf(userDetails.id))
//                    .setSubject(userDetails.fullName)
//                    .setIssuedAt(new Date())
//                    .setExpiration(new Date(currentTime + jwtProperties.getJwtExpirationMs()))
//                    .signWith(SignatureAlgorithm.HS256, jwtProperties.getJwtSecret())
//                    .compact();
//        }
//
//        public String generateVerificationToken(UserDetails userDetails) {
//
//            long currentTime = System.currentTimeMillis();
//            System.out.println("generate token id:   " + userDetails.id);
//            return Jwts.builder()
//                    .setId(String.valueOf(userDetails.id))
//                    .setSubject(userDetails.fullName)
//                    .setIssuedAt(new Date())
//                    .setExpiration(new Date(currentTime + jwtProperties.getVerificationMs()))
//                    .signWith(SignatureAlgorithm.HS256, jwtProperties.getJwtSecret())
//                    .compact();
//        }
//
//        public int decodeJWT(String jwt) throws JwtException {
//            try {
//                Claims claims = Jwts.parser()
//                        .setSigningKey(jwtProperties.getJwtSecret()).parseClaimsJws(jwt).getBody();
//
//                System.out.println("jwt id: " + claims.getId());
//                return Integer.parseInt(claims.getId());
//            } catch (ExpiredJwtException e) {
//                throw new JwtException("session time out");
//            }
//        }

}
