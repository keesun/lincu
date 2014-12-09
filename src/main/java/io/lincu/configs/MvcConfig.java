package io.lincu.configs;

import io.lincu.interceptors.CreateAdminAccountInteceptor;
import io.lincu.interceptors.DefaultCategoriesInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * @author Keeun Baik
 */
@Configuration
public class MvcConfig extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {

    @Autowired
    private CreateAdminAccountInteceptor createAdminAccountInteceptor;

    @Autowired
    private DefaultCategoriesInterceptor defaultCategoriesInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/vendor/**")
                .addResourceLocations("classpath:/vendor/")
                .setCachePeriod(Integer.MAX_VALUE);

        registry.addResourceHandler("/styles/**")
                .addResourceLocations("classpath:/styles/")
                .setCachePeriod(0);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(createAdminAccountInteceptor)
                .addPathPatterns("/**");

        registry.addInterceptor(defaultCategoriesInterceptor)
                .addPathPatterns("/**");
    }
}
