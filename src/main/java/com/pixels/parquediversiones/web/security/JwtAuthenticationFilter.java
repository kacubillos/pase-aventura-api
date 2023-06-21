package com.pixels.parquediversiones.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to validate the provided token, to establish authentication in the application context
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JWTUtil jwtGenerator;

    /**
     * Method to extract a token from the request
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        // If the token is not empty, it is separated from the prefix
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.replace("Bearer ", "");
        }

        return null;
    }

    /**
     * Method to validate the token, if it is valid the user is added to the application context
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        if(StringUtils.hasText(token) && jwtGenerator.validate(token)) {
            String username = jwtGenerator.getEmail(token);
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

            // Check the value of roles
            if(roles.contains("ADMIN") || roles.contains("EMP_JUEGO")) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                // Set other user authentication information, as IP or user agent
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Add authentication object to security context
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        // Makes the request to the next filter in the chain
        filterChain.doFilter(request, response);
    }
}
