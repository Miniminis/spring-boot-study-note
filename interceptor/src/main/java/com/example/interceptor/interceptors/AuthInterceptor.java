package com.example.interceptor.interceptors;

import com.example.interceptor.annotations.Auth;
import com.example.interceptor.exceptions.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String url = request.getRequestURI();
        String queryString = request.getQueryString();

        URI uri = UriComponentsBuilder.fromUriString(url).query(queryString).build().toUri();

        log.info("url : {}", url);
        log.info("queryString : {}", queryString);
        log.info("uri : {}", uri);

        boolean checkAuth = checkAuthentication(handler, Auth.class);
        if (checkAuth) {
            return true;
        }

        boolean checkUriAuth = checkUriAuth(queryString);
        if (checkUriAuth) {
            return true;
        }

        throw new AuthException();
//        return false;
    }

    private boolean checkUriAuth(String queryString) {
        if (queryString.equals("name=steve")) {
            return true;
        }
        return false;
    }

    private boolean checkAuthentication(Object handler, Class authClass) {

        //resource (js, html, css)
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        //annotation
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (null != handlerMethod.getMethodAnnotation(authClass) ||
                null != handlerMethod.getBeanType().getAnnotation(authClass)) {
            return true;
        }

        return false;
    }
}
