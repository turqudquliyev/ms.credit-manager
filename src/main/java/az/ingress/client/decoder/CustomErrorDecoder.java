package az.ingress.client.decoder;

import az.ingress.exception.CustomFeignException;
import com.fasterxml.jackson.databind.JsonNode;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import static az.ingress.client.decoder.JsonNodeFieldName.MESSAGE;
import static az.ingress.exception.ErrorMessage.CLIENT_ERROR;
import static az.ingress.util.MapperUtil.MAPPER_UTIL;

@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {

    public Exception decode(String methodKey, Response response) {
        var errorMessage = CLIENT_ERROR.getCode();
        var message = MESSAGE.getValue();
        var statusCode = response.status();

        try (var body = response.body().asInputStream()) {
            var jsonNode = MAPPER_UTIL.map(body, JsonNode.class);

            if (jsonNode.has(message))
                errorMessage = jsonNode.get(message).asText();

            log.error("ActionLog.decode.error from url {} with statusCode {} ", response.request().url(), statusCode);
            throw new CustomFeignException(errorMessage, statusCode);
        } catch (Exception e) {
            throw new CustomFeignException(errorMessage, statusCode);
        }
    }
}