package com.example.demo.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {

        log.info("ACCESS DENIED HANDLER CALLED");
        log.debug(
                "AUTH: " +
                        org.springframework.security.core.context.SecurityContextHolder
                                .getContext()
                                .getAuthentication()
        );

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write("""
                {
                    "code": "ACCESS_DENIED",
                    "message": "Forbidden",
                    "path": "%s"
                }
                """.formatted(request.getRequestURI()));
    }
}