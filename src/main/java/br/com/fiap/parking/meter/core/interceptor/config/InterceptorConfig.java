package br.com.fiap.parking.meter.core.interceptor.config;

import br.com.fiap.parking.meter.core.interceptor.PaymentInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorConfig implements WebMvcConfigurer {

    private final PaymentInterceptor interceptor;

    public InterceptorConfig(PaymentInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }

}
