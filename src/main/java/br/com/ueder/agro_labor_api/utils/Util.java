package br.com.ueder.agro_labor_api.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.MaskFormatter;
import java.net.URI;

public class Util {

    public static URI getURI(String path, Object value){
        return ServletUriComponentsBuilder.fromCurrentRequest()
                    .path(path)
                    .buildAndExpand(value)
                    .toUri();
    }

    public static String formatarCnpj(String cnpj) {
        try {
            MaskFormatter mf = new MaskFormatter("##.###.###/####-##");
            mf.setValueContainsLiteralCharacters(false);
            return mf.valueToString(cnpj);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao formatar CNPJ", e);
        }
    }

    public static Long getTenantId(HttpServletRequest request) {
        Object attribute = request.getAttribute("ID-Tenant");
        return  Long.valueOf(attribute.toString());
    }

}
