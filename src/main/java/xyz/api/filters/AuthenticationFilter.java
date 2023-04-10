package xyz.api.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import xyz.api.entities.AuthEntity;
import xyz.api.repositories.UserRepository;
import xyz.api.services.TokenService;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService service;

    @Autowired
    private UserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
       
        var token = request.getHeader("Authorization");

        if(token != null){
            
            token = token.replace("Bearer ", "");
            var id = this.service.verify(token);

            var user = this.repository.findById(Long.valueOf(id)).get();

            var auth = new AuthEntity(user.getId(), user.getEmail(), user.getPassword());

            SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(auth, null, auth.getAuthorities())
            );

            System.out.println(id);

        }

        filterChain.doFilter(request, response);
    }
}
    