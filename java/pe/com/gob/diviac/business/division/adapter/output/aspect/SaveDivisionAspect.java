package pe.com.gob.diviac.business.division.adapter.output.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import pe.com.gob.diviac.adapter.support.contract.auditv1.AuditRequestSupport;
import pe.com.gob.diviac.business.division.adapter.output.http.common.client.AuditProducerClient;
import pe.com.gob.diviac.business.division.entity.Audit;
import pe.com.gob.diviac.business.division.entity.Division;
import pe.com.gob.diviac.business.division.util.JsonUtils;

import java.util.function.Function;

@Aspect
@Component
@RequiredArgsConstructor
public class SaveDivisionAspect {

    private final AuditProducerClient auditProducerClient;
    private final Function<Audit, AuditRequestSupport> auditRequestSupportConverter;

    private Audit audit;
    private Division divisionToSave;

    @Before("execution (* pe.com.gob.diviac.business.division.domain.interactor.DivisionInteractor.insert(..))")
    public void beforeSaveDivision(JoinPoint joinPoint) {
        this.divisionToSave = (Division) joinPoint.getArgs()[0];
        this.audit = (Audit) joinPoint.getArgs()[1];
    }

    @AfterReturning(pointcut = "execution (* pe.com.gob.diviac.business.division.domain.interactor.DivisionInteractor.insert(..))",
            returning = "divisionSaved")
    public void afterSaveDivision(Division divisionSaved) {
        this.prepareOkAuditMessage(this.audit, this.divisionToSave, divisionSaved);
        this.sendAuditMessage(this.audit);
    }

    @AfterThrowing(pointcut = "execution (* pe.com.gob.diviac.business.division.domain.interactor.DivisionInteractor.insert(..))")
    public void afterSaveDivision(JoinPoint joinPoint) {
        this.prepareErrorAuditMessage(this.audit, this.divisionToSave);
        this.sendAuditMessage(this.audit);
    }

    private void prepareOkAuditMessage(Audit audit, Division divisionToSave,
                                       Division divisionSaved) {
        audit.setHttpStatusCode(HttpStatus.OK.value());
        audit.setRecordBeforeChange(JsonUtils.toJson(divisionToSave));
        audit.setRecordAfterChange(JsonUtils.toJson(divisionSaved));
    }

    private void prepareErrorAuditMessage(Audit audit, Division divisionToSave) {
        audit.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        audit.setRecordBeforeChange(JsonUtils.toJson(divisionToSave));
    }

    private void sendAuditMessage(Audit audit) {
        AuditRequestSupport auditRequestSupport;

        auditRequestSupport = auditRequestSupportConverter.apply(audit);
        auditProducerClient.sendMessage(auditRequestSupport);
    }
}
