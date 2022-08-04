package jp.cloudace.backend.todoapp.config;

import com.google.cloud.spring.logging.TraceIdLoggingWebMvcInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final TraceIdLoggingWebMvcInterceptor traceIdLoggingWebMvcInterceptor;

    public WebConfig(TraceIdLoggingWebMvcInterceptor traceIdLoggingWebMvcInterceptor) {
        this.traceIdLoggingWebMvcInterceptor = traceIdLoggingWebMvcInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(traceIdLoggingWebMvcInterceptor);
    }
}
