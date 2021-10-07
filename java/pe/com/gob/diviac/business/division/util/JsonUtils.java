package pe.com.gob.diviac.business.division.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

import pe.com.gob.diviac.business.division.util.constants.ErrorConstants;
import pe.com.gob.diviac.business.division.util.exception.DiviacStatusException;

@Slf4j
public class JsonUtils {

    public static String toJson(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new DiviacStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    ErrorConstants.ERROR_INTERNAL_SERVER_CODE,
                    ErrorConstants.ERROR_INTERNAL_SERVER_DESCRIPTION);
        }
    }
}
