package pe.com.gob.diviac.business.division.adapter.input.web.converter.common.request;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

import lombok.Builder;
import lombok.Data;

import pe.com.gob.diviac.business.division.entity.Audit;

@Builder
public class AuditConverter implements Function<AuditConverter.Wrapper, Audit> {

    @Override
    public Audit apply(Wrapper wrapper) {
        if (Objects.isNull(wrapper)) {
            return null;
        }

        return Audit.builder()
                .transactionId(wrapper.getTransactionId())
                .applicationId(wrapper.applicationId)
                .applicationName(wrapper.applicationName)
                .consumerId(wrapper.consumerId)
                .functionalActionCode(wrapper.functionalActionCode)
                .build();
    }

    @Data
    @Builder
    public static class Wrapper {

        private UUID transactionId;
        private String applicationId;
        private String applicationName;
        private String consumerId;
        private String functionalActionCode;
    }
}
