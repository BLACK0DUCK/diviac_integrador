package pe.com.gob.diviac.business.division.adapter.output.aspect.converter;

import java.util.Objects;
import java.util.function.Function;

import lombok.Builder;

import pe.com.gob.diviac.adapter.support.contract.auditv1.AuditRequestSupport;
import pe.com.gob.diviac.business.division.entity.Audit;
import pe.com.gob.diviac.business.division.util.StringUtils;

@Builder
public class AuditRequestSupportConverter implements Function<Audit, AuditRequestSupport> {

    @Override
    public AuditRequestSupport apply(Audit audit) {
        if (Objects.isNull(audit)) {
            return null;
        }

        AuditRequestSupport auditRequestSupport = new AuditRequestSupport();

        auditRequestSupport.setTransactionId(audit.getTransactionId());
        auditRequestSupport.setApplicationId(audit.getApplicationId());
        auditRequestSupport.setApplicationName(audit.getApplicationName());
        auditRequestSupport.setFunctionalActionCode(audit.getFunctionalActionCode());
        auditRequestSupport.setConsumerId(audit.getConsumerId());
        auditRequestSupport.setHttpStatus(this.getHttpStatus(audit.getHttpStatusCode()));
        auditRequestSupport.setRecordBeforeChange(audit.getRecordBeforeChange());
        auditRequestSupport.setRecordAfterChange(audit.getRecordAfterChange());

        return auditRequestSupport;
    }

    private AuditRequestSupport.HttpStatusEnum getHttpStatus(Integer httpStatusCode) {
        String httpStatus;

        if (Objects.isNull(httpStatusCode)) {
            return null;
        }

        httpStatus = StringUtils.HTTP_PREFIX.concat(StringUtils.UNDERSCORE_CHAR)
                .concat(String.valueOf(httpStatusCode));
        return AuditRequestSupport.HttpStatusEnum.fromValue(httpStatus);
    }
}
