/*
 * package com.tsys.poc.userservice.config;
 *
 * import java.io.IOException;
 *
 * import javax.servlet.FilterChain; import javax.servlet.ServletException;
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 *
 * import org.springframework.stereotype.Component; import
 * org.springframework.util.StringUtils; import
 * org.springframework.web.filter.OncePerRequestFilter;
 *
 * import com.tsys.poc.userservice.exception.AuthException;
 *
 * import lombok.AllArgsConstructor;
 *
 * @Component
 *
 * @AllArgsConstructor public class RequestFilter extends OncePerRequestFilter{
 *
 * private JWTValidator validator;
 *
 * @Override protected void doFilterInternal(HttpServletRequest request,
 * HttpServletResponse response, FilterChain filterChain) throws
 * ServletException, IOException{ String jwtToken =getJwtFromRequest(request);
 *
 * try { boolean result =validator.validateToken(jwtToken);
 * request.setAttribute("Authenticated", result); filterChain.doFilter(request,
 * response); } catch (Exception e) { throw new AuthException("Invalid Token");
 * }
 *
 * }
 *
 * private String getJwtFromRequest(HttpServletRequest request) { String
 * bearerToken =request.getHeader("Authorization");
 *
 * if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
 * return bearerToken.substring(7); }
 *
 * return bearerToken; }
 *
 * }
 */