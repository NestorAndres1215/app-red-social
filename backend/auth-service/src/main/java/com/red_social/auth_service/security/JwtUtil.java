package com.red_social.auth_service.security;

import com.red_social.auth_service.model.Login;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    @Value("${jwt.secret:${JWT_SECRET}}")
    private String SECRET_KEY;

    @Value("${jwt.expirationMs:${JWT_EXPIRATION}}")
    private long JWT_EXPIRATION_MS;


    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    // ===============================
    // 🔹 2️⃣ Generar token JWT
    // ===============================
    public String generateToken(Login login) {
        Map<String, Object> claims = new HashMap<>();

        String subject = (login.getUsername() != null && !login.getUsername().isBlank())
                ? login.getUsername()
                : (login.getEmail() != null && !login.getEmail().isBlank())
                ? login.getEmail()
                : login.getTelefono();


        return createToken(claims, subject);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        LocalDateTime now = LocalDateTime.now();
        Date issuedAt = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        Date expiration = Date.from(now.plusSeconds(JWT_EXPIRATION_MS / 1000).atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // <── Aquí se usa
                .compact();
    }

    // ===============================
    // 🔹 3️⃣ Extraer datos del token
    // ===============================
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ===============================
    // 🔹 4️⃣ Validar token
    // ===============================
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

}
