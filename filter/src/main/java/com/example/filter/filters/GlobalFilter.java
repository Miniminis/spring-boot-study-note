package com.example.filter.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
@Component      //spring 에 의해서 관리될 수 있도록 추가
public class GlobalFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        //1. 전처리
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;       //ServletRequest <- HttpServletRequest
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;       //ServletResponse <- HttpServletResponse

//        String url = httpServletRequest.getRequestURI();
//        BufferedReader br = httpServletRequest.getReader();
//        br.lines().forEach(line -> {
//            log.info("url : {}, line : {}", url, line);
//        });


        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);       //reader 를 여러번 읽기 위해서 캐싱 클래스를 사용한다
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);      //reader 를 여러번 읽기 위해서 캐싱 클래스를 사용한다


        //2. 비즈니스 로직 실행
        chain.doFilter(httpServletRequest, httpServletResponse);

        //3. 후처리
        String url = httpServletRequest.getRequestURI();
        String reqContent = new String(httpServletRequest.getContentAsByteArray());

        log.info("[REQUEST]");
        log.info("url : {}", url);
        log.info("requestBody : {}", reqContent);

        int httpStatusCode = httpServletResponse.getStatus();
        String resContent = new String(httpServletResponse.getContentAsByteArray());
        httpServletResponse.copyBodyToResponse();       //이 매서드를 호출해줘야 한번 읽은 response 가 클라이언트에게도 복사되어 응답될 수 있다.

        log.info("[RESPONSE]");
        log.info("httpStatusCode : {}", httpStatusCode);
        httpServletResponse.getHeaderNames().forEach(headerName -> {
            log.info("headerName {} : {}", headerName, httpServletResponse.getHeader(headerName));
        });
        log.info("responseBody : {}", resContent);      //여기서 한번 읽으면 다시 커서가 맨 뒤로 들어가있기 때문에 메인 비즈니스 로직에서 읽을 수가 없다.
    }

}
