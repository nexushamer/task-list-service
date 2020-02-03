package com.zagalabs.tasklist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zagalabs.tasklist.models.Message;
import com.zagalabs.tasklist.utils.JWTGenerator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.zagalabs.tasklist.utils.ConstantMessages.UNAUTHORIZED_EXPIRED_TOKEN;
import static com.zagalabs.tasklist.utils.ConstantMessages.UNAUTHORIZED_REQUEST;

@Component
public class RequestFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestFilter.class);
    private final JWTGenerator jwtGenerator;

    @Value("${auth.validate}")
    private String authValidate;

    @Autowired
    public RequestFilter(JWTGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String servletPath = httpServletRequest.getServletPath();

        if(!Boolean.valueOf(authValidate) || servletPath.equals("/auth")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {

            final String requestTokenHeader = httpServletRequest.getHeader("Authorization");
            LOGGER.debug(requestTokenHeader);

            final Message message = new Message();

            if (StringUtils.isBlank(requestTokenHeader)) {
                message.setDescription(UNAUTHORIZED_REQUEST);
                httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                httpServletResponse.getWriter().write(convertObjectToJson(message));
            } else {
                final String token = requestTokenHeader.substring(7);
                boolean isATokenInValid;

                try {
                    isATokenInValid = jwtGenerator.isTokenExpired(token);
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                    isATokenInValid = true;
                }

                if (!isATokenInValid) {
                    filterChain.doFilter(httpServletRequest, httpServletResponse);
                } else {
                    message.setDescription(UNAUTHORIZED_EXPIRED_TOKEN);
                    httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                    httpServletResponse.getWriter().write(convertObjectToJson(message));
                }
            }
        }
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
