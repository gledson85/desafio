package br.com.brasilprev.desafio.web.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.brasilprev.desafio.persistence.model.User;
import br.com.brasilprev.desafio.persistence.repository.UserRepository;

public class AutenticationByTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository repository;

    public AutenticationByTokenFilter(TokenService tokenService, UserRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String token = recoverToken(request);
        boolean valid = tokenService.isValidToken(token);
        if (valid) {
            authenticateClient(token);
        }

        filterChain.doFilter(request, response);
    }

    private void authenticateClient(String token) {
        final Long userId = tokenService.getUserId(token);
        final User user = repository.findById(userId).get();
        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recoverToken(HttpServletRequest request) {
        final String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) { // melhorar aqui!
            return null;
        }

        return token.substring(7, token.length());
    }

}
