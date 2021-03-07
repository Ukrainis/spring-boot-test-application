package com.example.demo.services;

import com.example.demo.auth.UserPrincipal;
import com.example.demo.entities.User;
import com.example.demo.exceptions.InvalidJwtTokenException;
import com.example.demo.interfaces.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
public class JWTTokenService implements TokenService {

    private final String JWT_SECRET = "OS=&[VPbu<EL(3qYo^7c]u+f:42'O<?$<9|&j*#;<2~h#[N6k'Xj[hsd&b1MqL$";

    @Override
    public String generateToken(User user) {
        Instant expirationTime = Instant.now().plus(1, ChronoUnit.HOURS);
        Date expirationDate = Date.from(expirationTime);
        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());

        String jwtToken = Jwts.builder()
                .claim("id", user.getId())
                .claim("sub", user.getUserName())
                .setExpiration(expirationDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return "Bearer " + jwtToken;
    }

    @Override
    public UserPrincipal parseToken(String jwtToken) {
        byte[] secretBytes = JWT_SECRET.getBytes();

        Jws<Claims> jwsClaims = null;
        try {
            String token = jwtToken.split(" ")[1];
            jwsClaims = Jwts.parserBuilder()
                    .setSigningKey(secretBytes)
                    .build()
                    .parseClaimsJws(token);
        } catch (Exception e) {
            log.info("Invalid jwtToken provided.");
            throw new InvalidJwtTokenException();
        }

        String username = jwsClaims.getBody()
                .getSubject();
        long userId = jwsClaims.getBody()
                .get("id", Integer.class);
        log.info(String.format("Token was provided for user: %s, with userId: %d", username, userId));

        return new UserPrincipal(userId, username);
    }
}
