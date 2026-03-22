package br.com.ueder.agro_labor_api.utils.filters;

import br.com.ueder.agro_labor_api.services.EmpresaService;
import br.com.ueder.agro_labor_api.utils.exceptions.RNException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class FilterTenantInterceptor implements HandlerInterceptor{

    public static final String PATH_API_EMPRESAS = "/apis/v1/empresas";
    public static final String ID_TENANT = "ID-Tenant";
    private final EmpresaService empresaService;

    public FilterTenantInterceptor(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        String contextPath = req.getRequestURI();
        if (contextPath.startsWith(PATH_API_EMPRESAS)){
           return true;
        } else {
            String tenantId = req.getHeader(ID_TENANT);
            if((tenantId == null) || (tenantId.isEmpty())){
                throw new RNException("O cabeçalho ID-Tenant é obrigatório");
            } else {
                empresaService.findById(Long.parseLong(tenantId));
            }
            req.setAttribute(ID_TENANT, tenantId);
            return true;
        }
    }

}
