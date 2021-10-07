package pe.com.gob.diviac.business.division.application.configuration;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pe.com.gob.diviac.adapter.support.contract.auditv1.AuditRequestSupport;
import pe.com.gob.diviac.business.division.adapter.output.aspect.converter.AuditRequestSupportConverter;
import pe.com.gob.diviac.business.division.entity.Audit;

@Configuration
public class AspectConfiguration {

    /*
     * Aspect Converter Beans
     */

    @Bean
    public Function<Audit, AuditRequestSupport> auditRequestSupportConverter() {
        return AuditRequestSupportConverter.builder()
                .build();
    }
}
