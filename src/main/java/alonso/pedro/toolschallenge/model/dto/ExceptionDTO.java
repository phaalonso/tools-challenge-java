package alonso.pedro.toolschallenge.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ExceptionDTO {
    private final Integer statusCode;
    private final String message;
}
