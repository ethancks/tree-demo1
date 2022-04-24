package com.lemon.tree.util;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.Instant;

@Component
public class LoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        Instant start = Instant.now();

        filterChain.doFilter(requestWrapper, responseWrapper);

        String requestBody = parse(requestWrapper.getContentAsByteArray(), requestWrapper.getCharacterEncoding());
        String responseBody = parse(responseWrapper.getContentAsByteArray(), responseWrapper.getCharacterEncoding());

        Tracer.info("Handled request in {0}ms\nMETHOD: {1}\nREQUEST_URI: {2}\nREQUEST_BODY: {3}\n\nRESPONSE_BODY: {4}\n",
                Duration.between(start, Instant.now()).toMillis(),
                request.getMethod(),
                request.getRequestURL(),
                requestBody,
                responseBody);

        responseWrapper.copyBodyToResponse();
    }

    private String parse(byte[] contents, String characterEncoding) {
        try {
            return new String(contents, characterEncoding);
        } catch (UnsupportedEncodingException e) {
            Tracer.warning("Failed to parse bytes to string.\n{0}", e);
        }
        return "";
    }
}
