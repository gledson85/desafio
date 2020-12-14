package br.com.brasilprev.desafio.web.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.brasilprev.desafio.persistence.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    @Value("${desafio.jwt.expiration}")
    private String expiration;

    @Value("${desafio.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        final User logged = (User) authentication.getPrincipal();
        final Date today = new Date();
        final Date expirationData = new Date(today.getTime() + Long.parseLong(expiration));

        return Jwts.builder().setIssuer("API Desafio").setSubject(logged.getId().toString()).setIssuedAt(today).setExpiration(expirationData)
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUserId(String token) {
        final Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

}
