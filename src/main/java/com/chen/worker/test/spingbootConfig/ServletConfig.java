package com.chen.worker.test.spingbootConfig;

import com.chen.worker.test.servlet.workServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenlizhi
 * @date 2018/7/30
 * **/
@Configuration
public class ServletConfig {
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new workServlet(), "/work/*");// ServletName默认值为首字母小写，即myServlet1

    }
}
