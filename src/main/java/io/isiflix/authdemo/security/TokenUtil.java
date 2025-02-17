package io.isiflix.authdemo.security;

import io.isiflix.authdemo.dto.LoginDTO;
import io.isiflix.authdemo.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Collections;
import java.util.Date;

public class TokenUtil {

    public static final String EMISSOR = "IsiFLIX";
    public static final long EXPIRATION = 60 * 60 * 1000;
    public static final String SECRET_KEY = "0123456789012345678901234567890123456789";

    public static IsiToken encode(User dadosLogin) {
        try {
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
            String jwtToken = Jwts.builder()
                    .subject(dadosLogin.getLogin())
                    .issuer(EMISSOR)
                    .claim("ROLE", "ROLE_"+dadosLogin.getRole())
                    .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                    .signWith(key)
                    .compact();
            return new IsiToken(jwtToken);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Authentication decode(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            if (token != null) {
                token = token.replace("Bearer ", "");
                SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
                JwtParser parser = Jwts.parser().verifyWith(secretKey).build();
                Claims claims = (Claims)parser.parse(token).getPayload();

                String subject = claims.getSubject();
                String issuer = claims.getIssuer();
                Date   exp = claims.getExpiration();
                String role = claims.get("ROLE").toString();

                if (issuer.equals(EMISSOR) && subject.length() > 0 && exp.after(new Date(System.currentTimeMillis()))) {
                    Authentication auth =
                            new UsernamePasswordAuthenticationToken(subject, null, Collections.singletonList(new SimpleGrantedAuthority(role)) );
                    return auth;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}