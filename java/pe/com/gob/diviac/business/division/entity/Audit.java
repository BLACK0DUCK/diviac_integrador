package pe.com.gob.diviac.business.division.entity;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Audit {

    private UUID transactionId;
    private String applicationId;
    private String applicationName;
    private String functionalActionCode;
    private String consumerId;
    private Integer httpStatusCode;
    private String recordBeforeChange;
    private String recordAfterChange;
}
