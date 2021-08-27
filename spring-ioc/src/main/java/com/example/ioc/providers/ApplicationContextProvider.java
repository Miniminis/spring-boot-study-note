package com.example.ioc.providers;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    /**
     * spring context 에 접근하기 위한 코드
     * 실행순서는 다음과 같이 된다.
     * 1. 스프링 어플리케이션 실행
     * 2. application context 주입되고
     * 3. 해당 context 가 Application context static 변수에 담겨서
     * 4. 이제 프로젝트 전역에서 그냥 가져다가 쓰기만 하면 된다.
     * */
}
