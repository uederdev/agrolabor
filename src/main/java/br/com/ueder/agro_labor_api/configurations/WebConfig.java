package br.com.ueder.agro_labor_api.configurations;

import br.com.ueder.agro_labor_api.utils.filters.FilterTenantInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final FilterTenantInterceptor filterTenantInterceptor;

    public WebConfig(FilterTenantInterceptor filterTenantInterceptor) {
        this.filterTenantInterceptor = filterTenantInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(filterTenantInterceptor)
        .addPathPatterns("/apis/**");
    }
}
