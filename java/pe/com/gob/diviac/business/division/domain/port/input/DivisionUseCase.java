package pe.com.gob.diviac.business.division.domain.port.input;

import java.util.UUID;

import pe.com.gob.diviac.business.division.entity.Audit;
import pe.com.gob.diviac.business.division.entity.Division;
import pe.com.gob.diviac.business.division.entity.request.DivisionsListRequest;
import pe.com.gob.diviac.business.division.entity.response.DivisionsListResponse;

public interface DivisionUseCase {

    Division insert(Division divisionToSave, Audit audit);

    Division findById(UUID divisionId);

    DivisionsListResponse findAllByCodeAndName(DivisionsListRequest divisionsListRequest);

    Division update(Division divisionToUpdate);

    void delete(UUID divisionId);

}
