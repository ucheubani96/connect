package com.example.connect.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.connect.encryption.JWTService;
import com.example.connect.user.User;
import com.example.connect.user.UserRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserRepo userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");

        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {

            String token = tokenHeader.substring(7);
            try{

                String username = jwtService.getUsername(token);

                Optional<User> opUser = userRepo.findByUsernameIgnoreCase(username);

                if (opUser.isPresent()) {
                    User user = opUser.get();


                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            } catch (JWTDecodeException ex) {
                System.out.print("--------------------------");
                System.out.print(" ++++++ ");
                System.out.print("--------------------------");
            }

        }

        filterChain.doFilter(request, response );
    }
}
