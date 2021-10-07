package pe.com.gob.diviac.business.division.adapter.output.http.common.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.com.gob.diviac.adapter.support.contract.auditv1.AuditRequestSupport;

@FeignClient(value = "support-audit-api", url = "${feign.client.config.support-audit-api.base-url}")
public interface AuditProducerClient {

    @RequestMapping(method = RequestMethod.POST, value = "/send", consumes = MediaType.APPLICATION_JSON_VALUE)
    void sendMessage(AuditRequestSupport auditRequestSupport);
}
